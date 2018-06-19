package com.example.android.githubrepolist;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.githubrepolist.database.DatabaseAdapter;
import com.example.android.githubrepolist.database.DatabaseTableHelper;
import com.example.android.githubrepolist.utilities.NetworkUtils;

import org.json.JSONArray;

import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RepoAdapter mRepoAdapter;
    private TextView mErrorMessageDisplay,tvMessage;
    private ProgressBar mLoadingIndicator;
    private int page = 1;
    private String user;
    private ArrayList<RepoData> lastItemsList =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_repo);

        mErrorMessageDisplay = (TextView) findViewById(R.id.tv_error_message_display);
        tvMessage = (TextView) findViewById(R.id.tv_message);
        tvMessage.setVisibility(View.VISIBLE);


        final LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setHasFixedSize(true);

        mRepoAdapter = new RepoAdapter(getApplicationContext());

        mRecyclerView.setAdapter(mRepoAdapter);
        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);


        RecyclerView.OnScrollListener mScrollListener = new RecyclerView.OnScrollListener() {
            final LinearLayoutManager mLayoutManager = layoutManager;
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int visibleItemCount = mLayoutManager.getChildCount();
                int totalItemCount = mLayoutManager.getItemCount();
                int pastVisibleItems = mLayoutManager.findFirstVisibleItemPosition();
                if (pastVisibleItems + visibleItemCount >= totalItemCount) {
                    int i = 2 + 1;

                    loadRepoData(user);
                }
            }
        };

        mRecyclerView.addOnScrollListener(mScrollListener);


        FloatingActionButton fb =(FloatingActionButton)findViewById(R.id.fab);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(lastItemsList.size()>0){
                    DatabaseAdapter adapter = new DatabaseAdapter(getApplicationContext());

                    DatabaseTableHelper table = adapter.getTable();

                   for(int i=0;i<lastItemsList.size();i++){
                       RepoData data = lastItemsList.get(i);
                       table.insertRecord(data);

                       /*data.setOwner(lastItemsList.get(i).getOwner());
                       data.setName(lastItemsList.get(i).getName());
                       data.setAvatar(lastItemsList.get(i).getAvatar());
                       data.setLink(lastItemsList.get(i).getLink());
                       data.setSize(lastItemsList.get(i).getSize());
                       data.setWatcher(lastItemsList.get(i).getWatcher());
                       data.setOpenIssue(lastItemsList.get(i).getOpenIssue());
*/







                   }

                    Toast.makeText(getApplicationContext(),"Saved sucessfully",Toast.LENGTH_LONG).show();;





                }
                else{
                    Toast.makeText(getApplicationContext(),"No data to save",Toast.LENGTH_LONG).show();;

                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        // show menu only when home fragment is selected
        // if/* (navItemIndex == 0) {
        /*    getMenuInflater().inflate(R.menu.main, menu);
        }

        // when fragment is notifications, load the menu created for notifications
        if (navItemIndex == 3) {*/
        getMenuInflater().inflate(R.menu.menu_main, menu);

        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mRecyclerView.setVisibility(View.VISIBLE);
                mErrorMessageDisplay.setVisibility(View.INVISIBLE);

                loadRepoData(query);
                user=query;
                lastItemsList.clear();
                mRepoAdapter.notifyDataSetChanged();
                page=1;
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
               // mAdapter.filter(newText.toString());
                return false;
            }
        });
        //}
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection

        switch (item.getItemId()) {


            case R.id.action_saved:
                Intent intent = new Intent(getApplicationContext(),OwnerActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

                break;



            default:
                return super.onOptionsItemSelected(item);
        }
        return  true;
    }


    /**
     * Calls showRepoDataView that makes the view visible.
     * Calls execute on the async task.
     */
    private void loadRepoData(String user) {
        new FetchRepoTask(user).execute();
    }

    /**
     * If there's an error, show error message.
     */
    private void showErrorMessage() {
        mRecyclerView.setVisibility(View.INVISIBLE);
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
    }

    public class FetchRepoTask extends AsyncTask<String, Void, ArrayList<RepoData>> {
         String user;
        public FetchRepoTask(String user){
           this.user=user;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingIndicator.setVisibility(View.VISIBLE);
            tvMessage.setVisibility(View.INVISIBLE);

        }

        @Override
        protected ArrayList<RepoData> doInBackground(String... params) {
            URL repoRequestUrl = NetworkUtils.buildUrl(user,page);

            try {
                String jsonResponse = NetworkUtils
                        .getResponseFromHttpUrl(repoRequestUrl);

                JSONArray rootObject = new JSONArray(jsonResponse);
                //since = rootObject.getJSONObject(rootObject.length() - 1).getInt("id");

               // if (lastItemsList == null) {
                    for(int i = 0; i < rootObject.length(); i++) {
                        RepoData data = new RepoData();
                        data.setName(rootObject.getJSONObject(i).getString("name"));
                        data.setLink(rootObject.getJSONObject(i).getString("html_url"));
                        data.setSize(rootObject.getJSONObject(i).getString("size"));
                        data.setWatcher(rootObject.getJSONObject(i).getString("watchers_count"));
                        data.setOpenIssue(rootObject.getJSONObject(i).getString("open_issues_count"));
                        data.setDescription(rootObject.getJSONObject(i).getString("description"));
                        data.setAvatar(rootObject.getJSONObject(i).getJSONObject("owner").getString("avatar_url"));
                        data.setOwner(rootObject.getJSONObject(i).getJSONObject("owner").getString("login"));


                        lastItemsList.add(data);





                    }

                   // lastItemsList = parsedData;
                /*else {
                    parsedData = new String[rootObject.length() + lastItemsList.length];
                    for(int i = 0; i < lastItemsList.length; i++) {
                        parsedData[i] = lastItemsList[i];
                    }

                    for(int i = 0; i < rootObject.length(); i++) {
                        parsedData[i+lastItemsList.length] = rootObject.getJSONObject(i).getString("name");
                    }

                }*/
                page++;
                return lastItemsList;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<RepoData> repoData) {
            mLoadingIndicator.setVisibility(View.INVISIBLE);
            if (repoData != null) {
                mRepoAdapter.setRepoData(repoData);

            } else {
                showErrorMessage();
            }
        }
    }
}
package com.example.android.githubrepolist;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.githubrepolist.database.DatabaseAdapter;
import com.example.android.githubrepolist.database.DatabaseTableHelper;

import java.util.ArrayList;
import java.util.HashSet;

public class OwnerActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private OwnerAdapter mOwnerAdapter;
    private TextView mErrorMessageDisplay;
    private ProgressBar mLoadingIndicator;
    private int page = 1;
    private String user;
    private ArrayList<OwnerData> lastItemsList =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_repo);
        FloatingActionButton fab =(FloatingActionButton)findViewById(R.id.fab);
        fab.setVisibility(View.GONE);

        mErrorMessageDisplay = (TextView) findViewById(R.id.tv_error_message_display);

        final LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setHasFixedSize(true);
        DatabaseAdapter adapter = new DatabaseAdapter(getApplicationContext());
        DatabaseTableHelper table = adapter.getTable();
         HashSet<String> data= table.getOwnerDataRecord(getApplicationContext());

        mOwnerAdapter = new OwnerAdapter(getApplicationContext(),data);

        mRecyclerView.setAdapter(mOwnerAdapter);

/*
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
*/

      //  mRecyclerView.addOnScrollListener(mScrollListener);


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
        /*getMenuInflater().inflate(R.menu.menu_main, menu);

        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mRecyclerView.setVisibility(View.VISIBLE);
                mErrorMessageDisplay.setVisibility(View.INVISIBLE);

               // loadRepoData(query);
                user=query;
                lastItemsList.clear();
                mOwnerAdapter.notifyDataSetChanged();
                page=1;
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
               // mAdapter.filter(newText.toString());
                return false;
            }
        });
        *///}
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection

        switch (item.getItemId()) {


            case android.R.id.home:
                finish();
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

    /**
     * If there's an error, show error message.
     */
    private void showErrorMessage() {
        mRecyclerView.setVisibility(View.INVISIBLE);
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
    }

}
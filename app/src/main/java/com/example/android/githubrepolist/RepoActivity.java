package com.example.android.githubrepolist;

import android.content.Intent;
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

public class RepoActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RepoAdapter mRepoAdapter;
    private TextView mErrorMessageDisplay;
    private ProgressBar mLoadingIndicator;
    private int page = 1;
    private String user;
    private ArrayList<RepoData> lastItemsList =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        Intent intent = getIntent();

        if(intent!=null){

            String owner= intent.getStringExtra("owner");

            DatabaseAdapter adapter = new DatabaseAdapter(getApplicationContext());
            DatabaseTableHelper table = adapter.getTable();

            lastItemsList= table.getAllRepoDataRecord(getApplicationContext(),owner);





        }


        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_repo);

        mErrorMessageDisplay = (TextView) findViewById(R.id.tv_error_message_display);
        FloatingActionButton fab =(FloatingActionButton)findViewById(R.id.fab);
        fab.setVisibility(View.GONE);

        final LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setHasFixedSize(true);

        mRepoAdapter = new RepoAdapter(getApplicationContext(),lastItemsList);

        mRecyclerView.setAdapter(mRepoAdapter);

        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);







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
        //}
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
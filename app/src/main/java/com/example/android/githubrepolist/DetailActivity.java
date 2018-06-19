package com.example.android.githubrepolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

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

        setContentView(R.layout.activity_detail);

        ImageView img= (ImageView) findViewById(R.id.img);
        TextView title =(TextView)findViewById(R.id.project_title) ;
        TextView desc =(TextView)findViewById(R.id.project_des) ;




/*
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
*/

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        Intent intent = getIntent();
       if(intent!=null){

           String name= intent.getStringExtra("name");
           String avatar= intent.getStringExtra("avatar");
           String des= intent.getStringExtra("des");

           title.setText(name);
           desc.setText(des);
           Picasso.get().load(avatar) .placeholder(R.mipmap.ic_launcher)

                   .into(img);



       }

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
       // getMenuInflater().inflate(R.menu.menu_main, menu);

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

}
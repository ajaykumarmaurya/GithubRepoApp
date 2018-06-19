package com.example.android.githubrepolist;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.RepoAdapterViewHolder> {

    private ArrayList<RepoData> mRepoData;

    Context context;

    public RepoAdapter(Context context) {
        this.context=context;

    }

    public RepoAdapter(Context context,ArrayList<RepoData> data) {
        this.context=context;
        this.mRepoData=data;
    }

    /**
     * ViewHolder class for repos
     */
    public class RepoAdapterViewHolder extends RecyclerView.ViewHolder {

        public final TextView mRepoTextView,link,size,watcher,issue;
        public final View recView;

        public RepoAdapterViewHolder(View view) {
            super(view);
            recView=view;
            mRepoTextView = (TextView) view.findViewById(R.id.tv_repo_data);
            link = (TextView) view.findViewById(R.id.tv_link);
            size = (TextView) view.findViewById(R.id.tv_size);
            watcher = (TextView) view.findViewById(R.id.tv_watchers_count);
            issue = (TextView) view.findViewById(R.id.tv_open_issue);




        }
    }

    @Override
    public RepoAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.repo_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        return new RepoAdapterViewHolder(view);
    }

    /**
     * Displays the data at the specified position.
     *
     * @param repoAdapterViewHolder The ViewHolder to be updated
     * @param pos                  The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(RepoAdapterViewHolder repoAdapterViewHolder, final int pos) {
        String weatherForThisDay = mRepoData.get(pos).getName();
        repoAdapterViewHolder.mRepoTextView.setText(weatherForThisDay);

        repoAdapterViewHolder.link.setText("Link");
        repoAdapterViewHolder.size.setText(Math.round((Double.parseDouble(mRepoData.get(pos).getSize())/1024.0) * 100.0) / 100.0 +"MB");
        repoAdapterViewHolder.watcher.setText(mRepoData.get(pos).getWatcher());
        repoAdapterViewHolder.issue.setText(mRepoData.get(pos).getOpenIssue());


        repoAdapterViewHolder.link.setOnClickListener(new View.OnClickListener() {
                                                          @Override
                                                          public void onClick(View view) {
                                                              Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mRepoData.get(pos).getLink()));
                                                              intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                              context.startActivity(intent);

                                                          }
                                                      }
                                                      );
        repoAdapterViewHolder.recView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,DetailActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("name",mRepoData.get(pos).getName());
                intent.putExtra("avatar",mRepoData.get(pos).getAvatar());
                intent.putExtra("des",mRepoData.get(pos).getDescription());



                context.startActivity(intent);


            }
        });







    }

    /**
     * Computes the number of items to show.
     * @return The number of items to show
     */
    @Override
    public int getItemCount() {
        if (null == mRepoData) return 0;
        return mRepoData.size();
    }

    /**
     * Sets the repo data to a RepoAdapter if there is one
     * @param repoData The new repo data to be displayed.
     */
    public void setRepoData(ArrayList<RepoData> repoData) {
        mRepoData = repoData;
        notifyDataSetChanged();
    }
}
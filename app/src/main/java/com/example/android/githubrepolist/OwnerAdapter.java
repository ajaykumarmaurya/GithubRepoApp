package com.example.android.githubrepolist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.githubrepolist.database.DatabaseAdapter;
import com.example.android.githubrepolist.database.DatabaseTableHelper;
import com.squareup.picasso.Picasso;

import java.util.HashSet;

public class OwnerAdapter extends RecyclerView.Adapter<OwnerAdapter.OwnerAdapterViewHolder> {

    private String [] mRepoData;
    private DatabaseTableHelper table;

    Context context;

    public OwnerAdapter(Context context,HashSet<String>data) {
        this.context=context;
        this.mRepoData = data.toArray(new String[data.size()]);
        DatabaseAdapter adapter = new DatabaseAdapter(context);

        table =adapter.getTable();
        ;
    }

    /**
     * ViewHolder class for repos
     */
    public class OwnerAdapterViewHolder extends RecyclerView.ViewHolder {

        public final TextView mRepoTextView;
        public final View recView;
        private  final ImageView image;

        public OwnerAdapterViewHolder(View view) {
            super(view);
            recView=view;
            mRepoTextView = (TextView) view.findViewById(R.id.tv_repo_data);
            image =(ImageView)view.findViewById(R.id.img_owner);




        }
    }

    @Override
    public OwnerAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.owner_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        return new OwnerAdapterViewHolder(view);
    }

    /**
     * Displays the data at the specified position.
     *
     * @param ownerAdapterViewHolder The ViewHolder to be updated
     * @param pos                  The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(OwnerAdapterViewHolder ownerAdapterViewHolder, final int pos) {
        String weatherForThisDay = mRepoData[pos];
        RepoData data= table.getDataRecordByOwner(weatherForThisDay,context);
        if(data!=null){
            Picasso.get().load(data.getAvatar()) .placeholder(R.mipmap.ic_launcher)

                    .into(ownerAdapterViewHolder.image);

        }

        ownerAdapterViewHolder.mRepoTextView.setText(weatherForThisDay);

                ownerAdapterViewHolder.recView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,RepoActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("owner",mRepoData[pos]);



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
        return mRepoData.length;
    }

    /**
     * Sets the repo data to a RepoAdapter if there is one
     * @param repoData The new repo data to be displayed.
     */
    public void setRepoData(String[] repoData) {
        mRepoData = repoData;
        notifyDataSetChanged();
    }

}
package com.towenqi.qianlv.memory;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by qianlv on 2015/12/19.
 */
public class GridAdapter extends RecyclerView.Adapter<GridAdapter.MyViewHolder>  {
    ArrayList<PageData> mDatas;
    Activity mActivity;
    public GridAdapter(Activity activity,ArrayList<PageData> data){
        mActivity = activity;
        mDatas = data;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item_layout
                ,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable(DetailActivity.TAG,mDatas.get(position));
                intent.putExtras(bundle);
                Pair<View, String> p1 = Pair.create((View)holder.mImageView, "cover");
                Pair<View, String> p2 = Pair.create((View)holder.mTitle, "title");
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(mActivity,p1);
                context.startActivity(intent,options.toBundle());

            }
        });
        holder.mTitle.setText(mDatas.get(position).mTitleStrId);
        Glide.with(holder.mImageView.getContext())
                .load(mDatas.get(position).mImgResId)
                .fitCenter()
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public final ImageView mImageView;
        public final View mView;
        public final TextView mTitle;
        public MyViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            mTitle = (TextView) itemView.findViewById(R.id.card_title);
            mImageView = (ImageView)itemView.findViewById(R.id.icon_img);
        }
    }

    public static class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        private int space;
        public SpacesItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view,
                                   RecyclerView parent, RecyclerView.State state) {
            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;
            if(parent.getChildLayoutPosition(view) == 0 || parent.getChildLayoutPosition(view) == 1)
                outRect.top = space;
        }
    }
}



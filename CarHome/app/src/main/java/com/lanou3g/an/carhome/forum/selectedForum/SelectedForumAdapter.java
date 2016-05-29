package com.lanou3g.an.carhome.forum.selectedForum;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou3g.an.carhome.R;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by anfeng on 16/5/19.
 */
public class SelectedForumAdapter extends RecyclerView.Adapter<SelectedForumAdapter.MyViewHolder> {

    private Context context;
    private SelectedForumBean selectedForumBean;
    private OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public SelectedForumAdapter(Context context) {
        this.context = context;
    }

    public void setSelectedForumBean(SelectedForumBean selectedForumBean) {
        this.selectedForumBean = selectedForumBean;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder myViewHolder;
        View view = LayoutInflater.from(context).inflate(R.layout.item_selected_forum, parent, false);
        myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Log.d("SelectedForumAdapter", "selectedForumBean:" + selectedForumBean);
        getIntentImage(holder.selectedIv, selectedForumBean.getResult().getList().get(position).getSmallpic());
        holder.titleTv.setText(selectedForumBean.getResult().getList().get(position).getTitle());
        holder.headingTv.setText(selectedForumBean.getResult().getList().get(position).getBbsname());
        holder.numberTv.setText(selectedForumBean.getResult().getList().get(position).getReplycounts() + "回");

        if (onClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = selectedForumBean.getResult().getList().get(position).getTopicid();
                    onClickListener.onClick(id, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return selectedForumBean != null ? selectedForumBean.getResult().getList().size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView selectedIv;
        private TextView titleTv, headingTv, numberTv;

        public MyViewHolder(View itemView) {
            super(itemView);
            selectedIv = (ImageView) itemView.findViewById(R.id.item_selected_forum_iv);
            titleTv = (TextView) itemView.findViewById(R.id.item_selected_forum_title);
            headingTv = (TextView) itemView.findViewById(R.id.item_selected_forum_time);
            numberTv = (TextView) itemView.findViewById(R.id.item_selected_forum_number);
        }
    }

    private void getIntentImage(ImageView view, String url) {
        Picasso.with(context).load(url).placeholder(R.mipmap.fild).error(R.mipmap.fild).into(view);
    }

    interface OnClickListener {
        void onClick(int id, int position);
    }
}

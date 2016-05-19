package com.lanou3g.an.carhome.articleNestingFragment.journalism;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou3g.an.carhome.BuildConfig;
import com.lanou3g.an.carhome.R;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by anfeng on 16/5/14.
 */
public class JournalismAdapter extends RecyclerView.Adapter<JournalismAdapter.MyViewHolder> {

    private Context context;
    private JournalismBean journalismBean;
    private OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public JournalismAdapter(Context context) {
        this.context = context;
    }

    public void setJournalismBean(JournalismBean journalismBean) {
        this.journalismBean = journalismBean;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder myViewHolder;
        View myView = LayoutInflater.from(context).inflate(R.layout.item_journalism, null);
        myViewHolder = new MyViewHolder(myView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        getIntentData(holder.journalismIv,journalismBean.getResult().getNewslist().get(position).getSmallpic());
        holder.journalismTitle.setText(journalismBean.getResult().getNewslist().get(position).getTitle());
        holder.journalismTime.setText(journalismBean.getResult().getNewslist().get(position).getTime());
        holder.journalismNumber.setText(journalismBean.getResult().getNewslist().get(position).getReplycount() + "评论");

        if (onClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = journalismBean.getResult().getNewslist().get(position).getId();
                    onClickListener.onClick(id);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return journalismBean != null ? journalismBean.getResult().getNewslist().size() : 0;
    }



    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView journalismIv;
        private TextView journalismTitle,journalismTime,journalismNumber;
        public MyViewHolder(View itemView) {
            super(itemView);
            journalismIv = (ImageView) itemView.findViewById(R.id.item_journalism_iv);
            journalismTitle = (TextView) itemView.findViewById(R.id.item_journalism_title);
            journalismTime = (TextView) itemView.findViewById(R.id.item_journalism_time);
            journalismNumber = (TextView) itemView.findViewById(R.id.item_journalism_number);
        }
    }

    public void getIntentData(ImageView view,String url) {
        Picasso.with(context).load(url).placeholder(R.mipmap.fild).error(R.mipmap.fild).into(view);
    }

    interface OnClickListener{
        void onClick(int id);
    }
}

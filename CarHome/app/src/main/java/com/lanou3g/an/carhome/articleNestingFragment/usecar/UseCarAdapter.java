package com.lanou3g.an.carhome.articleNestingFragment.usecar;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou3g.an.carhome.R;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by anfeng on 16/5/14.
 */
public class UseCarAdapter extends BaseAdapter {

    private Context context;
    private UseCarBean useCarBean;


    public UseCarAdapter(Context context) {
        this.context = context;
    }

    public void setUseCarBean(UseCarBean useCarBean) {
        this.useCarBean = useCarBean;
        notifyDataSetChanged();
    }

    @Override
    public int getViewTypeCount() {
        return 11;
    }

    @Override
    public int getItemViewType(int position) {
        return useCarBean.getResult().getNewslist().get(position).getMediatype();
    }

    @Override
    public int getCount() {
        return useCarBean != null ? useCarBean.getResult().getNewslist().size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        OneViewHolder oneViewHolder = null;
        TwoViewHolder twoViewHolder = null;
        int type = useCarBean.getResult().getNewslist().get(position).getMediatype();

        if (convertView == null) {
            switch (type) {
                case 0:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_usecar, parent, false);
                    oneViewHolder = new OneViewHolder(convertView);
                    convertView.setTag(oneViewHolder);
                    break;
                case 10:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_uaecar_three, parent, false);
                    twoViewHolder = new TwoViewHolder(convertView);
                    convertView.setTag(twoViewHolder);
                    break;
            }
        } else {
            switch (type) {
                case 0:
                    oneViewHolder = (OneViewHolder) convertView.getTag();
                    break;
                case 10:
                    twoViewHolder = (TwoViewHolder) convertView.getTag();
                    break;
            }
        }
        switch (type) {
            case 0:
                //设置数据
                getIntentData(oneViewHolder.usecarIv, useCarBean.getResult().getNewslist().get(position).getSmallpic());
                oneViewHolder.usecarTitle.setText(useCarBean.getResult().getNewslist().get(position).getTitle());
                oneViewHolder.usecarTime.setText(useCarBean.getResult().getNewslist().get(position).getTime());
                oneViewHolder.usecarNumber.setText(useCarBean.getResult().getNewslist().get(position).getReplycount() + "评论");
                break;
            case 10:
                //设置数据
                String intentUrl = useCarBean.getResult().getNewslist().get(position).getIndexdetail();
                String[] s = intentUrl.split("㊣");
                getIntentData(twoViewHolder.useCarThreeOneIV, s[0]);
                getIntentData(twoViewHolder.useCarThreeTwoIV, s[1]);
                getIntentData(twoViewHolder.useCarThreeThreeIV, s[2]);
                twoViewHolder.useCarThreeTitle.setText(useCarBean.getResult().getNewslist().get(position).getTitle());
                twoViewHolder.useCarThreeTime.setText(useCarBean.getResult().getNewslist().get(position).getTime());
                twoViewHolder.useCarThreeNumber.setText(useCarBean.getResult().getNewslist().get(position).getReplycount() + "评论");
                break;
        }

        return convertView;
    }

    class OneViewHolder extends RecyclerView.ViewHolder {
        private ImageView usecarIv;
        private TextView usecarTitle, usecarTime, usecarNumber;

        public OneViewHolder(View itemView) {
            super(itemView);
            usecarIv = (ImageView) itemView.findViewById(R.id.item_usecar_iv);
            usecarTitle = (TextView) itemView.findViewById(R.id.item_usecar_title);
            usecarTime = (TextView) itemView.findViewById(R.id.item_usecar_time);
            usecarNumber = (TextView) itemView.findViewById(R.id.item_usecar_number);
        }
    }

    class TwoViewHolder extends RecyclerView.ViewHolder {
        private TextView useCarThreeTitle, useCarThreeTime, useCarThreeNumber;
        private ImageView useCarThreeOneIV, useCarThreeTwoIV, useCarThreeThreeIV;

        public TwoViewHolder(View itemView) {
            super(itemView);
            useCarThreeTitle = (TextView) itemView.findViewById(R.id.item_usecar_three_title);
            useCarThreeTime = (TextView) itemView.findViewById(R.id.item_usecar_three_time);
            useCarThreeNumber = (TextView) itemView.findViewById(R.id.item_usecar_three_number);
            useCarThreeOneIV = (ImageView) itemView.findViewById(R.id.item_usecar_three_one_iv);
            useCarThreeTwoIV = (ImageView) itemView.findViewById(R.id.item_usecar_three_one_two);
            useCarThreeThreeIV = (ImageView) itemView.findViewById(R.id.item_usecar_three_one_three);
        }
    }

    public void getIntentData(ImageView view, String url) {
        Picasso.with(context).load(url).placeholder(R.mipmap.fild).error(R.mipmap.fild).into(view);
    }


}

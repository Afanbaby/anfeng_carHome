package com.lanou3g.an.carhome.my.myCollection;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou3g.an.carhome.Collection;
import com.lanou3g.an.carhome.R;

import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by anfeng on 16/5/27.
 */
public class MyCollectionAdapter extends BaseAdapter {

    private Context context;
    private List<Collection> collectionList;

    public MyCollectionAdapter(Context context) {
        this.context = context;
    }

    public void setCollectionList(List<Collection> collectionList) {
        this.collectionList = collectionList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return collectionList != null ? collectionList.size() : 0;
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
        MyCollectionHilder myCollectionHilder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_my, parent, false);
            myCollectionHilder = new MyCollectionHilder(convertView);
            convertView.setTag(myCollectionHilder);
        } else {
            myCollectionHilder = (MyCollectionHilder) convertView.getTag();
        }
        //设置数据
        Picasso.with(context).load(collectionList.get(position).getImageUrl()).placeholder(R.mipmap.fild).error(R.mipmap.fild).into(myCollectionHilder.myIv);
        myCollectionHilder.myTv.setText(collectionList.get(position).getTitle());
        return convertView;
    }

    class MyCollectionHilder {
        private ImageView myIv;
        private TextView myTv;

        MyCollectionHilder(View itemView) {
            myIv = (ImageView) itemView.findViewById(R.id.item_my_iv);
            myTv = (TextView) itemView.findViewById(R.id.item_my_title);
        }
    }

}

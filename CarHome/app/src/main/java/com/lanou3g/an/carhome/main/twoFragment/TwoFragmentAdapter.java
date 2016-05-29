package com.lanou3g.an.carhome.main.twoFragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou3g.an.carhome.R;

import java.util.List;

/**
 * Created by anfeng on 16/5/25.
 */
public class TwoFragmentAdapter extends BaseAdapter {

    private Context context;
    private List<String> list;

    public TwoFragmentAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
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
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_main_draws, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //设置数据
        String data = list.get(position);
        holder.typeName.setText(data);
        return convertView;

    }

    class ViewHolder {
        public TextView typeName;
        public ImageView typeImage;

        public ViewHolder(View itemView) {
            typeName = (TextView) itemView.findViewById(R.id.item_main_draws_tv);
            typeImage = (ImageView) itemView.findViewById(R.id.item_main_draws_iv);
        }
    }
}
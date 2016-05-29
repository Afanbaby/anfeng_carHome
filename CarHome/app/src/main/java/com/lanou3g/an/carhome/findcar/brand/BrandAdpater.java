package com.lanou3g.an.carhome.findcar.brand;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou3g.an.carhome.R;

/**
 * Created by anfeng on 16/5/19.
 */
public class BrandAdpater extends BaseExpandableListAdapter {
    private Context context;
    private BrandBean brandBean;
    private String[] fathers;

    public BrandAdpater(Context context) {
        this.context = context;
    }

    public void setBrandBean(BrandBean brandBean) {
        this.brandBean = brandBean;
        notifyDataSetChanged();
    }

    @Override
    public int getGroupCount() {
        return brandBean != null ? brandBean.getResult().getBrandlist().size() : 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return brandBean.getResult().getBrandlist().get(groupPosition).getList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return brandBean.getResult().getBrandlist().get(groupPosition).getLetter();
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return brandBean.getResult().getBrandlist().get(groupPosition).getList().get(childPosition).getName();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        MyOneViewHolder myOneViewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_brand_one, parent, false);
            myOneViewHolder = new MyOneViewHolder(convertView);
            convertView.setTag(myOneViewHolder);
        } else {
            myOneViewHolder = (MyOneViewHolder) convertView.getTag();
        }
        //设置数据
        myOneViewHolder.nametv.setText(brandBean.getResult().getBrandlist().get(groupPosition).getLetter());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        MyTwoViewHolder myTwoViewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_brand_two, parent, false);
            myTwoViewHolder = new MyTwoViewHolder(convertView);
            convertView.setTag(myTwoViewHolder);
        } else {
            myTwoViewHolder = (MyTwoViewHolder) convertView.getTag();
        }
        //设置数据
        myTwoViewHolder.typeNameTv.setText(brandBean.getResult().getBrandlist().get(groupPosition).getList().get(childPosition).getName());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class MyOneViewHolder {
        private TextView nametv;

        MyOneViewHolder(View itemView) {
            nametv = (TextView) itemView.findViewById(R.id.item_brand_one_tv);
        }
    }

    class MyTwoViewHolder {
        private TextView typeNameTv;

        MyTwoViewHolder(View itemView) {
            typeNameTv = (TextView) itemView.findViewById(R.id.item_brand_two_tv);
        }
    }

    public int getPosition(int sectionIndex) {
        for (int i = 0; i < getGroupCount(); i++) {
            String sortStr = brandBean.getResult().getBrandlist().get(i).getLetter();
            char firstCar = sortStr.toUpperCase().charAt(0);
            if (firstCar == sectionIndex) {
                return i;
            }
        }
        return -1;
    }
}

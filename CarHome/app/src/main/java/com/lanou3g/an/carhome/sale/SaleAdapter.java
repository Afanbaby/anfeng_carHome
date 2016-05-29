package com.lanou3g.an.carhome.sale;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou3g.an.carhome.R;
import com.lanou3g.an.carhome.sale.saleBean.SaleFragmentBean;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by anfeng on 16/5/21.
 */
public class SaleAdapter extends BaseAdapter {

    private Context context;
    private SaleFragmentBean saleBean;

    public SaleAdapter(Context context) {
        this.context = context;
    }

    public void setSaleBean(SaleFragmentBean saleBean) {
        this.saleBean = saleBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return saleBean != null ? saleBean.getResult().getGoodslist().getList().size() : 0;
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
        MyViewHolder myViewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_sale_seven, parent, false);
            myViewHolder = new MyViewHolder(convertView);
            convertView.setTag(myViewHolder);
        } else {
            myViewHolder = (MyViewHolder) convertView.getTag();
        }
        //设置数据
        Picasso.with(context).load(saleBean.getResult().getGoodslist().getList().get(position).getLogo()).placeholder(R.mipmap.fild).error(R.mipmap.fild).into(myViewHolder.commodityIv);
        myViewHolder.commodityTitle.setText(saleBean.getResult().getGoodslist().getList().get(position).getTitle());
        myViewHolder.commoditySmallTitle.setText(saleBean.getResult().getGoodslist().getList().get(position).getShorttitle());
        myViewHolder.commodityPresentPrice.setText(saleBean.getResult().getGoodslist().getList().get(position).getPrice());
        myViewHolder.commodityPrimaryPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        myViewHolder.commodityPrimaryPrice.setText(saleBean.getResult().getGoodslist().getList().get(position).getFctprice());
        return convertView;
    }

    class MyViewHolder {
        private ImageView commodityIv;
        private TextView commodityTitle, commoditySmallTitle, commodityPresentPrice, commodityPrimaryPrice;

        MyViewHolder(View itemView) {
            commodityIv = (ImageView) itemView.findViewById(R.id.item_sale_seven_iv);
            commodityTitle = (TextView) itemView.findViewById(R.id.item_sale_seven_title_one);
            commoditySmallTitle = (TextView) itemView.findViewById(R.id.item_sale_seven_title_two);
            commodityPresentPrice = (TextView) itemView.findViewById(R.id.item_sale_seven_price_one);
            commodityPrimaryPrice = (TextView) itemView.findViewById(R.id.item_sale_seven_price_two);
        }
    }
}

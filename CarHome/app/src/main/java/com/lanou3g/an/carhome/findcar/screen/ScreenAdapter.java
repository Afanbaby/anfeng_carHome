package com.lanou3g.an.carhome.findcar.screen;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou3g.an.carhome.R;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by anfeng on 16/5/20.
 */
public class ScreenAdapter extends RecyclerView.Adapter<ScreenAdapter.MyViewHolder> {

    private ScreenBean screenBean;
    private Context context;

    public ScreenAdapter(Context context) {
        this.context = context;
    }

    public void setScreenBean(ScreenBean screenBean) {
        this.screenBean = screenBean;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder myViewHolder = null;
        View view = LayoutInflater.from(context).inflate(R.layout.item_screen, parent, false);
        myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (screenBean.getResult().getSeries().get(position).getCornericon().equals("1")) {
            holder.screenTwoIv.setImageResource(R.mipmap.first);
        } else if (screenBean.getResult().getSeries().get(position).getCornericon().equals("2")) {
            holder.screenTwoIv.setImageResource(R.mipmap.two);
        } else if (screenBean.getResult().getSeries().get(position).getCornericon().equals("3")) {
            holder.screenTwoIv.setImageResource(R.mipmap.three);
        } else {
            holder.screenTwoIv.setImageResource(R.mipmap.white);
        }
        Picasso.with(context).load(screenBean.getResult().getSeries().get(position).getThumburl()).into(holder.screenIv);
        holder.screenTitleTv.setText(screenBean.getResult().getSeries().get(position).getSeriesname());
        holder.screenPriceTv.setText(screenBean.getResult().getSeries().get(position).getPricerange());
    }

    @Override
    public int getItemCount() {
        return screenBean != null ? screenBean.getResult().getSeries().size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView screenIv, screenTwoIv;
        private TextView screenTitleTv, screenPriceTv;

        public MyViewHolder(View itemView) {
            super(itemView);
            screenIv = (ImageView) itemView.findViewById(R.id.item_screen_iv);
            screenTitleTv = (TextView) itemView.findViewById(R.id.item_screen_title);
            screenPriceTv = (TextView) itemView.findViewById(R.id.item_screen_price);
            screenTwoIv = (ImageView) itemView.findViewById(R.id.item_screen_head_iv);
        }
    }
}

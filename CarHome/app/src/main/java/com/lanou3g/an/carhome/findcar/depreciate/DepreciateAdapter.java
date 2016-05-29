package com.lanou3g.an.carhome.findcar.depreciate;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou3g.an.carhome.R;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by anfeng on 16/5/21.
 */
public class DepreciateAdapter extends RecyclerView.Adapter<DepreciateAdapter.MyViewHolder> {

    private Context context;
    private DepreciateBean depreciateBean;

    public DepreciateAdapter(Context context) {
        this.context = context;
    }

    public void setDepreciateBean(DepreciateBean depreciateBean) {
        this.depreciateBean = depreciateBean;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder myViewHolder = null;
        View view = LayoutInflater.from(context).inflate(R.layout.item_depreciate, parent, false);
        myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Picasso.with(context).load(depreciateBean.getResult().getCarlist().get(position).getSpecpic()).placeholder(R.mipmap.fild).error(R.mipmap.fild).into(holder.deprcicateIv);
        holder.titleTv.setText(depreciateBean.getResult().getCarlist().get(position).getSpecname());
        holder.originalPriceTV.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        holder.originalPriceTV.setText(depreciateBean.getResult().getCarlist().get(position).getFctprice() + "万");
        float originalPrice = Float.valueOf(depreciateBean.getResult().getCarlist().get(position).getFctprice());
        float originalPrice1 = Float.valueOf(depreciateBean.getResult().getCarlist().get(position).getDealerprice());
        float a = originalPrice - originalPrice1;
        String price = String.valueOf(a);
        holder.presentPriceTv.setText(price.substring(0, 3) + "万");
        holder.thePriceTv.setText("降" + depreciateBean.getResult().getCarlist().get(position).getDealerprice() + "万");
        holder.localtionTv.setText(depreciateBean.getResult().getCarlist().get(position).getDealer().getCity());
        holder.companyTv.setText(depreciateBean.getResult().getCarlist().get(position).getDealer().getName());
        holder.distanceTv.setText(depreciateBean.getResult().getCarlist().get(position).getOrdercount() + "km");
        holder.sellTv.setText(depreciateBean.getResult().getCarlist().get(position).getOrderrange());

        holder.buyTv.setText("购车计算");
        holder.callTv.setText("立即拨打24h");
        holder.askTv.setText(depreciateBean.getResult().getCarlist().get(position).getDealer().getOrderrange());

    }

    @Override
    public int getItemCount() {
        return depreciateBean != null ? depreciateBean.getResult().getCarlist().size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView deprcicateIv;
        private TextView titleTv, presentPriceTv, originalPriceTV, thePriceTv,
                localtionTv, companyTv, distanceTv, sellTv,
                buyTv, callTv, askTv;

        public MyViewHolder(View itemView) {
            super(itemView);
            deprcicateIv = (ImageView) itemView.findViewById(R.id.item_price_iv);
            titleTv = (TextView) itemView.findViewById(R.id.item_price_title_tv);
            presentPriceTv = (TextView) itemView.findViewById(R.id.item_price_price_one);
            originalPriceTV = (TextView) itemView.findViewById(R.id.item_price_price_two);
            thePriceTv = (TextView) itemView.findViewById(R.id.item_price_price_three);

            localtionTv = (TextView) itemView.findViewById(R.id.item_deprecite_localtion);
            companyTv = (TextView) itemView.findViewById(R.id.item_deprecite_company);
            distanceTv = (TextView) itemView.findViewById(R.id.item_deprecite_distance);
            sellTv = (TextView) itemView.findViewById(R.id.item_deprecite_sell);

            buyTv = (TextView) itemView.findViewById(R.id.item_deprecite_buy);
            callTv = (TextView) itemView.findViewById(R.id.item_deprecite_call);
            askTv = (TextView) itemView.findViewById(R.id.item_deprecite_ask);

        }
    }
}

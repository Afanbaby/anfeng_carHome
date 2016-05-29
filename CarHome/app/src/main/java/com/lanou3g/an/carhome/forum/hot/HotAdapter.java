package com.lanou3g.an.carhome.forum.hot;

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
public class HotAdapter extends RecyclerView.Adapter<HotAdapter.MyViewHolder> {

    private Context context;
    private HotBean hotBean;
    private OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public HotAdapter(Context context) {
        this.context = context;
    }

    public void setHotBean(HotBean hotBean) {
        this.hotBean = hotBean;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder myViewHolder = null;
        View view = LayoutInflater.from(context).inflate(R.layout.item_hot, parent, false);
        myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        if (hotBean.getResult().getList().get(position).getIspictopic() == 1) {
            holder.hotIv.setImageResource(R.mipmap.have_photo);
        } else {
            holder.hotIv.setImageResource(R.mipmap.white);
        }
        holder.titleTv.setText(hotBean.getResult().getList().get(position).getTitle());
        holder.typeNameTv.setText(hotBean.getResult().getList().get(position).getBbsname());
        String t = hotBean.getResult().getList().get(position).getPostdate();
        String time = t.substring(5, 16);
        holder.timeTv.setText(time);
        holder.numberTv.setText(hotBean.getResult().getList().get(position).getReplycounts() + "回帖");

        if (onClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = hotBean.getResult().getList().get(position).getTopicid();
                    onClickListener.onClick(id, position);
                }
            });
        }
    }

    @Override

    public int getItemCount() {
        return hotBean != null ? hotBean.getResult().getList().size() : 0;
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView hotIv;
        private TextView titleTv, typeNameTv, timeTv, numberTv;

        public MyViewHolder(View itemView) {
            super(itemView);
            hotIv = (ImageView) itemView.findViewById(R.id.item_hot_iv);
            titleTv = (TextView) itemView.findViewById(R.id.item_hot_title);
            typeNameTv = (TextView) itemView.findViewById(R.id.item_hot_type_name);
            timeTv = (TextView) itemView.findViewById(R.id.item_hot_time);
            numberTv = (TextView) itemView.findViewById(R.id.item_hot_number);
        }
    }

    interface OnClickListener {
        void onClick(int id, int position);
    }
}

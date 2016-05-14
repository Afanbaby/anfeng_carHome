package com.lanou3g.an.carhome.articleNestingFragment.bulletin;

import android.content.Context;
import android.media.Image;
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
 * Created by anfeng on 16/5/12.
 */
public class BulletinAdapter extends RecyclerView.Adapter<BulletinAdapter.MyBulletinView> {

    private Context context;
    private BulletinBean bulletinBean;
    private LayoutInflater layoutInflater;
    private OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public BulletinAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public void setBulletinBean(BulletinBean bulletinBean) {
        this.bulletinBean = bulletinBean;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return bulletinBean.getResult().getList().get(position).getState();
    }

    @Override
    public MyBulletinView onCreateViewHolder(ViewGroup parent, int viewType) {
        MyBulletinView myBulletinView = null;
        switch (viewType) {
            case 2:
                View bulletinView = layoutInflater.inflate(R.layout.item_bulletin, null);
                myBulletinView = new MyBulletinView(bulletinView);
                break;
        }
        return myBulletinView;
    }

    @Override
    public void onBindViewHolder(MyBulletinView holder, final int position) {
        int type = getItemViewType(position);
        switch (type) {
            case 2:
                getIntentData(holder.bulletinIv, bulletinBean.getResult().getList().get(position).getBgimage());
                holder.bulletinBull.setText(bulletinBean.getResult().getList().get(position).getTypename());
                //是否播报结束
                //holder.bulletinIfEnd.setText(bulletinBean.getResult().getList().get(position).getTypename());
                holder.bulletinTitle.setText(bulletinBean.getResult().getList().get(position).getTitle());
                holder.bulletinNumber.setText(bulletinBean.getResult().getList().get(position).getReviewcount() + "浏览");
                holder.bulletinTime.setText(bulletinBean.getResult().getList().get(position).getCreatetime());
                break;
        }
        if (onClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = bulletinBean.getResult().getList().get(position).getId();
                    onClickListener.onClick(id);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return bulletinBean != null ? bulletinBean.getResult().getList().size() : 0;
    }

    class MyBulletinView extends RecyclerView.ViewHolder {
        private TextView bulletinBull, bulletinIfEnd, bulletinTitle, bulletinNumber, bulletinTime;
        private ImageView bulletinIv;

        public MyBulletinView(View itemView) {
            super(itemView);
            bulletinBull = (TextView) itemView.findViewById(R.id.item_bulletin_bull);
            bulletinIfEnd = (TextView) itemView.findViewById(R.id.item_bulletin_if_end);
            bulletinTitle = (TextView) itemView.findViewById(R.id.item_bulletin_title);
            bulletinNumber = (TextView) itemView.findViewById(R.id.item_bulletin_number);
            bulletinTime = (TextView) itemView.findViewById(R.id.item_bulletin_time);
            bulletinIv = (ImageView) itemView.findViewById(R.id.item_bulletin_iv);
        }
    }

    public void getIntentData(ImageView view, String url) {
        Picasso.with(context).load(url).placeholder(R.mipmap.fild).error(R.mipmap.fild).into(view);
    }

    interface OnClickListener {
        void onClick(int id);
    }
}

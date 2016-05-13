package com.lanou3g.an.carhome.articleNestingFragment.newest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.lanou3g.an.carhome.BuildConfig;
import com.lanou3g.an.carhome.R;
import com.lanou3g.an.carhome.articleNestingFragment.newest.newestDetail.NewestDetailAvtivity;

import java.util.ArrayList;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by anfeng on 16/5/10.
 * 最新的adpater
 */
public class NewestAdapter extends RecyclerView.Adapter {
    private static final int SEETHREE = 3;
    private static final int SEESIX = 6;
    private static final int SEETWO = 2;
    private Context context;
    private LayoutInflater inflater;
    public NewestBean newestBean;
    private OnClickListener onClickListener;


    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setNewestBean(NewestBean newestBean) {
        this.newestBean = newestBean;
        notifyDataSetChanged();
    }

    public NewestAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        return newestBean.getResult().getNewslist().get(position).getMediatype();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case 1:
                View oneView = inflater.inflate(R.layout.item_newest_one_layouts, null);
                viewHolder = new SeeOneViewHolder(oneView);
                break;
            case 2:
                View twoView = inflater.inflate(R.layout.item_newest_two_layouts, null);
                viewHolder = new SeeTwoViewHolder(twoView);
                break;
            case SEETHREE:
                View threeView = inflater.inflate(R.layout.item_newest_three_layouts, null);
                viewHolder = new SeeThreeViewHolder(threeView);
                break;
            case 5:
                View fiveView = inflater.inflate(R.layout.item_newest_five_layouts, null);
                viewHolder = new SeeFiveViewholder(fiveView);
                break;
            case 6:
                View sixView = inflater.inflate(R.layout.item_newest_six_layouts, null);
                viewHolder = new SeeSixViewHolder(sixView);
                break;

        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case 1:
                SeeOneViewHolder seeOneViewHolder = (SeeOneViewHolder) holder;
                getIntentData(newestBean.getResult().getNewslist().get(position).getSmallpic(), seeOneViewHolder.seeOneIv);
                seeOneViewHolder.seeOneTitle.setText(newestBean.getResult().getNewslist().get(position).getTitle());
                seeOneViewHolder.seeOneTime.setText(newestBean.getResult().getNewslist().get(position).getTime());
                seeOneViewHolder.seeOneNumber.setText(newestBean.getResult().getNewslist().get(position).getReplycount() + "评论");
                break;
            case 2:
                SeeTwoViewHolder seeTwoViewHolder = (SeeTwoViewHolder) holder;
                getIntentData(newestBean.getResult().getNewslist().get(position).getSmallpic(), seeTwoViewHolder.seeTwoIv);
                seeTwoViewHolder.seeTwotitleTv.setText(newestBean.getResult().getNewslist().get(position).getTitle());
                //根据id需要获取到中偏下的标签
                //seeTwoViewHolder.seeTwotypeName.setText(newestBean.getResult().getNewslist().get(position).getJumppage());
                seeTwoViewHolder.seeTwotimeTv.setText(newestBean.getResult().getNewslist().get(position).getTime());
                seeTwoViewHolder.seeTwofrequencyTv.setText(newestBean.getResult().getNewslist().get(position).getReplycount() + "评论");
                break;
            case SEETHREE:
                SeeThreeViewHolder seeThreeViewHolder = (SeeThreeViewHolder) holder;
                getIntentData(newestBean.getResult().getNewslist().get(position).getSmallpic(), seeThreeViewHolder.seeThreeIv);
                seeThreeViewHolder.seeThreetitleTv.setText(newestBean.getResult().getNewslist().get(position).getTitle());
                seeThreeViewHolder.seeThreetimeTv.setText(newestBean.getResult().getNewslist().get(position).getTime());
                seeThreeViewHolder.seeThreefrequencyTv.setText(newestBean.getResult().getNewslist().get(position).getReplycount() + "播放");
                break;
            case 5:
                SeeFiveViewholder seeFiveViewholder = (SeeFiveViewholder) holder;
                getIntentData(newestBean.getResult().getNewslist().get(position).getSmallpic(), seeFiveViewholder.seeFiveIv);
                seeFiveViewholder.seeFiveTitle.setText(newestBean.getResult().getNewslist().get(position).getTitle());
                seeFiveViewholder.seeFiveTime.setText(newestBean.getResult().getNewslist().get(position).getTime());
                seeFiveViewholder.seeFiveNumber.setText(newestBean.getResult().getNewslist().get(position).getReplycount() + "回帖");
                break;
            case 6:
                SeeSixViewHolder seeSixViewHolder = (SeeSixViewHolder) holder;
                //因为网络的图片的地址需要进行拆分
                String url = newestBean.getResult().getNewslist().get(position).getIndexdetail();
                String[] s1 = url.split("㊣");
                String[] s2 = s1[2].split(",");
                String imageOneUrl = s2[0];
                String imageTwoUrl = s2[1];
                String imageThreeUrl = s2[2];
                getIntentData(imageOneUrl, seeSixViewHolder.seeSixOneIv);
                getIntentData(imageTwoUrl, seeSixViewHolder.seeSixTwoIv);
                getIntentData(imageThreeUrl, seeSixViewHolder.seeSixThreeIv);
                seeSixViewHolder.seeSixTitle.setText(newestBean.getResult().getNewslist().get(position).getTitle());
                seeSixViewHolder.seeSixTime.setText(newestBean.getResult().getNewslist().get(position).getTime());
                seeSixViewHolder.seeSixNumber.setText(newestBean.getResult().getNewslist().get(position).getReplycount() + "图片");
                break;
        }

        if (onClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = newestBean.getResult().getNewslist().get(position).getId();
                    onClickListener.onClick(id);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return newestBean != null ? newestBean.getResult().getNewslist().size() : 0;
    }


    //一个布局(一张图片的,type = 3)
    class SeeThreeViewHolder extends RecyclerView.ViewHolder {
        private ImageView seeThreeIv;
        private TextView seeThreetitleTv, seeThreetimeTv, seeThreefrequencyTv;

        public SeeThreeViewHolder(View itemView) {
            super(itemView);
            seeThreeIv = (ImageView) itemView.findViewById(R.id.item_newest_three_iv);
            seeThreetitleTv = (TextView) itemView.findViewById(R.id.item_newest_three_title);
            seeThreetimeTv = (TextView) itemView.findViewById(R.id.item_newest_three_time);
            seeThreefrequencyTv = (TextView) itemView.findViewById(R.id.item_newest_three_frequency);
        }
    }

    class SeeTwoViewHolder extends RecyclerView.ViewHolder {
        private ImageView seeTwoIv;
        private TextView seeTwotitleTv, seeTwotimeTv, seeTwofrequencyTv, seeTwotypeName;

        public SeeTwoViewHolder(View itemView) {
            super(itemView);
            seeTwoIv = (ImageView) itemView.findViewById(R.id.item_newest_two_iv);
            seeTwotitleTv = (TextView) itemView.findViewById(R.id.item_newest_two_title);
            seeTwotimeTv = (TextView) itemView.findViewById(R.id.item_newest_two_time);
            seeTwotypeName = (TextView) itemView.findViewById(R.id.item_newest_two_typename);
            seeTwofrequencyTv = (TextView) itemView.findViewById(R.id.item_newest_two_number);
        }
    }

    class SeeSixViewHolder extends RecyclerView.ViewHolder {
        private TextView seeSixTitle, seeSixTime, seeSixNumber;
        private ImageView seeSixOneIv, seeSixTwoIv, seeSixThreeIv;

        public SeeSixViewHolder(View itemView) {
            super(itemView);
            seeSixTitle = (TextView) itemView.findViewById(R.id.item_six_layouts_title);
            seeSixTime = (TextView) itemView.findViewById(R.id.item_six_layouts_time);
            seeSixNumber = (TextView) itemView.findViewById(R.id.item_six_layouts_number);
            seeSixOneIv = (ImageView) itemView.findViewById(R.id.item_six_layouts_iv_one);
            seeSixTwoIv = (ImageView) itemView.findViewById(R.id.item_six_layouts_iv_two);
            seeSixThreeIv = (ImageView) itemView.findViewById(R.id.item_six_layouts_iv_three);
        }
    }

    class SeeOneViewHolder extends RecyclerView.ViewHolder {
        private TextView seeOneTitle, seeOneTime, seeOneNumber;
        private ImageView seeOneIv;

        public SeeOneViewHolder(View itemView) {
            super(itemView);
            seeOneIv = (ImageView) itemView.findViewById(R.id.item_newest_one_iv);
            seeOneTitle = (TextView) itemView.findViewById(R.id.item_newest_one_title);
            seeOneTime = (TextView) itemView.findViewById(R.id.item_newest_one_time);
            seeOneNumber = (TextView) itemView.findViewById(R.id.item_newest_one_number);
        }
    }

    class SeeFiveViewholder extends RecyclerView.ViewHolder {
        private TextView seeFiveTitle, seeFiveTime, seeFiveNumber;
        private ImageView seeFiveIv;

        public SeeFiveViewholder(View itemView) {
            super(itemView);
            seeFiveTitle = (TextView) itemView.findViewById(R.id.item_newest_five_title);
            seeFiveTime = (TextView) itemView.findViewById(R.id.item_newest_five_time);
            seeFiveNumber = (TextView) itemView.findViewById(R.id.item_newest_five_number);
            seeFiveIv = (ImageView) itemView.findViewById(R.id.item_newest_five_iv);
        }
    }


    //获取网络图片
   /* public void getIntentData(String url, ImageView view) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        ImageLoader imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
            @Override
            public Bitmap getBitmap(String url) {
                return null;
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
            }
        });
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(view, R.mipmap.fild, R.mipmap.fild);
        imageLoader.get(url, listener);
    }*/

    //通过毕加索来获取网络数据
    public void getIntentData(String url, ImageView view) {
        Picasso.with(context).load(url).placeholder(R.mipmap.fild).error(R.mipmap.fild).into(view);
    }

    //点击事件的接口
    interface OnClickListener {
        void onClick(int id);
    }
}

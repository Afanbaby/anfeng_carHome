package com.lanou3g.an.carhome.articleNestingFragment.newest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
public class NewestAdapter extends BaseAdapter {
    private static final int SEETHREE = 3;
    private static final int SEESIX = 6;
    private static final int SEETWO = 2;
    private Context context;
    private LayoutInflater inflater;
    public NewestBean newestBean;

    public void setNewestBean(NewestBean newestBean) {
        this.newestBean = newestBean;
        notifyDataSetChanged();
    }

    public NewestAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return newestBean != null ? newestBean.getResult().getNewslist().size() : 0;
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
    public int getViewTypeCount() {
        return 6;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SeeThreeViewHolder seeThreeViewHolder = null;
        SeeTwoViewHolder seeTwoViewHolder = null;
        SeeSixViewHolder seeSixViewHolder = null;
        SeeOneViewHolder seeOneViewHolder = null;
        SeeFiveViewholder seeFiveViewholder = null;

        int type = newestBean.getResult().getNewslist().get(position).getMediatype();
        if (type == 3) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_newest_three_layouts, parent, false);
                seeThreeViewHolder = new SeeThreeViewHolder(convertView);
                convertView.setTag(seeThreeViewHolder);
            } else {
                seeThreeViewHolder = (SeeThreeViewHolder) convertView.getTag();
            }
            //设置数据
            getIntentData(newestBean.getResult().getNewslist().get(position).getSmallpic(), seeThreeViewHolder.seeThreeIv);
            seeThreeViewHolder.seeThreetitleTv.setText(newestBean.getResult().getNewslist().get(position).getTitle());
            seeThreeViewHolder.seeThreetimeTv.setText(newestBean.getResult().getNewslist().get(position).getTime());
            seeThreeViewHolder.seeThreefrequencyTv.setText(newestBean.getResult().getNewslist().get(position).getReplycount() + "播放");


        } else if (type == 2) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_newest_two_layouts, parent, false);
                seeTwoViewHolder = new SeeTwoViewHolder(convertView);
                convertView.setTag(seeTwoViewHolder);
            } else {
                seeTwoViewHolder = (SeeTwoViewHolder) convertView.getTag();
            }
            //设置数据
            getIntentData(newestBean.getResult().getNewslist().get(position).getSmallpic(), seeTwoViewHolder.seeTwoIv);
            seeTwoViewHolder.seeTwotitleTv.setText(newestBean.getResult().getNewslist().get(position).getTitle());
            //根据id需要获取到中偏下的标签
            //seeTwoViewHolder.seeTwotypeName.setText(newestBean.getResult().getNewslist().get(position).getJumppage());
            seeTwoViewHolder.seeTwotimeTv.setText(newestBean.getResult().getNewslist().get(position).getTime());
            seeTwoViewHolder.seeTwofrequencyTv.setText(newestBean.getResult().getNewslist().get(position).getReplycount() + "评论");

        } else if (type == 6) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_newest_six_layouts, parent, false);
                seeSixViewHolder = new SeeSixViewHolder(convertView);
                convertView.setTag(seeSixViewHolder);
            } else {
                seeSixViewHolder = (SeeSixViewHolder) convertView.getTag();
            }
            //设置数据
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

        } else if (type == 1) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_newest_one_layouts, parent, false);
                seeOneViewHolder = new SeeOneViewHolder(convertView);
                convertView.setTag(seeOneViewHolder);
            } else {
                seeOneViewHolder = (SeeOneViewHolder) convertView.getTag();
            }
            //设置数据
            getIntentData(newestBean.getResult().getNewslist().get(position).getSmallpic(), seeOneViewHolder.seeOneIv);
            seeOneViewHolder.seeOneTitle.setText(newestBean.getResult().getNewslist().get(position).getTitle());
            seeOneViewHolder.seeOneTime.setText(newestBean.getResult().getNewslist().get(position).getTime());
            seeOneViewHolder.seeOneNumber.setText(newestBean.getResult().getNewslist().get(position).getReplycount() + "评论");

        } else if (type == 5) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_newest_five_layouts, parent, false);
                seeFiveViewholder = new SeeFiveViewholder(convertView);
                convertView.setTag(seeFiveViewholder);
            } else {
                seeFiveViewholder = (SeeFiveViewholder) convertView.getTag();
            }
            //设置数据
            getIntentData(newestBean.getResult().getNewslist().get(position).getSmallpic(), seeFiveViewholder.seeFiveIv);
            seeFiveViewholder.seeFiveTitle.setText(newestBean.getResult().getNewslist().get(position).getTitle());
            seeFiveViewholder.seeFiveTime.setText(newestBean.getResult().getNewslist().get(position).getTime());
            seeFiveViewholder.seeFiveNumber.setText(newestBean.getResult().getNewslist().get(position).getReplycount() + "回帖");
        }
        return convertView;
    }

    //一个布局(一张图片的,type = 3)
    class SeeThreeViewHolder {
        private ImageView seeThreeIv;
        private TextView seeThreetitleTv, seeThreetimeTv, seeThreefrequencyTv;

        public SeeThreeViewHolder(View itemView) {
            seeThreeIv = (ImageView) itemView.findViewById(R.id.item_newest_three_iv);
            seeThreetitleTv = (TextView) itemView.findViewById(R.id.item_newest_three_title);
            seeThreetimeTv = (TextView) itemView.findViewById(R.id.item_newest_three_time);
            seeThreefrequencyTv = (TextView) itemView.findViewById(R.id.item_newest_three_frequency);
        }
    }

    class SeeTwoViewHolder {
        private ImageView seeTwoIv;
        private TextView seeTwotitleTv, seeTwotimeTv, seeTwofrequencyTv, seeTwotypeName;

        public SeeTwoViewHolder(View itemView) {
            seeTwoIv = (ImageView) itemView.findViewById(R.id.item_newest_two_iv);
            seeTwotitleTv = (TextView) itemView.findViewById(R.id.item_newest_two_title);
            seeTwotimeTv = (TextView) itemView.findViewById(R.id.item_newest_two_time);
            seeTwotypeName = (TextView) itemView.findViewById(R.id.item_newest_two_typename);
            seeTwofrequencyTv = (TextView) itemView.findViewById(R.id.item_newest_two_number);
        }
    }

    class SeeSixViewHolder {
        private TextView seeSixTitle, seeSixTime, seeSixNumber;
        private ImageView seeSixOneIv, seeSixTwoIv, seeSixThreeIv;

        public SeeSixViewHolder(View itemView) {
            seeSixTitle = (TextView) itemView.findViewById(R.id.item_six_layouts_title);
            seeSixTime = (TextView) itemView.findViewById(R.id.item_six_layouts_time);
            seeSixNumber = (TextView) itemView.findViewById(R.id.item_six_layouts_number);
            seeSixOneIv = (ImageView) itemView.findViewById(R.id.item_six_layouts_iv_one);
            seeSixTwoIv = (ImageView) itemView.findViewById(R.id.item_six_layouts_iv_two);
            seeSixThreeIv = (ImageView) itemView.findViewById(R.id.item_six_layouts_iv_three);
        }
    }

    class SeeOneViewHolder {
        private TextView seeOneTitle, seeOneTime, seeOneNumber;
        private ImageView seeOneIv;

        public SeeOneViewHolder(View itemView) {
            seeOneIv = (ImageView) itemView.findViewById(R.id.item_newest_one_iv);
            seeOneTitle = (TextView) itemView.findViewById(R.id.item_newest_one_title);
            seeOneTime = (TextView) itemView.findViewById(R.id.item_newest_one_time);
            seeOneNumber = (TextView) itemView.findViewById(R.id.item_newest_one_number);
        }
    }

    class SeeFiveViewholder {
        private TextView seeFiveTitle, seeFiveTime, seeFiveNumber;
        private ImageView seeFiveIv;

        public SeeFiveViewholder(View itemView) {
            seeFiveTitle = (TextView) itemView.findViewById(R.id.item_newest_five_title);
            seeFiveTime = (TextView) itemView.findViewById(R.id.item_newest_five_time);
            seeFiveNumber = (TextView) itemView.findViewById(R.id.item_newest_five_number);
            seeFiveIv = (ImageView) itemView.findViewById(R.id.item_newest_five_iv);
        }
    }

    /**************/
    @Override
    public int getItemViewType(int position) {
        return newestBean.getResult().getNewslist().get(position).getMediatype();
    }




  /*  //获取网络图片
    public void getIntentData(String url, ImageView view) {
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

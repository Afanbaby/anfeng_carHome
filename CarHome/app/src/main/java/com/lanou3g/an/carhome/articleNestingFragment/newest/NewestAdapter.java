package com.lanou3g.an.carhome.articleNestingFragment.newest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou3g.an.carhome.R;

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
        return 7;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        SeeSixViewHolder seeSixViewHolder = null;
        CurrencyViewholder currencyViewholder = null;

        int type = newestBean.getResult().getNewslist().get(position).getMediatype();
        if (type == 6) {
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
        } else {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_newest_five_layouts, parent, false);
                currencyViewholder = new CurrencyViewholder(convertView);
                convertView.setTag(currencyViewholder);
            } else {
                currencyViewholder = (CurrencyViewholder) convertView.getTag();
            }
            //设置数据
            getIntentData(newestBean.getResult().getNewslist().get(position).getSmallpic(), currencyViewholder.currencyFiveIv);
            currencyViewholder.currencyTitle.setText(newestBean.getResult().getNewslist().get(position).getTitle());
            currencyViewholder.currencyTime.setText(newestBean.getResult().getNewslist().get(position).getTime());
            if (type == 3) {
                currencyViewholder.currencyNumber.setText(newestBean.getResult().getNewslist().get(position).getReplycount() + "播放");
            } else if (type == 1) {
                currencyViewholder.currencyNumber.setText(newestBean.getResult().getNewslist().get(position).getReplycount() + "评论");
            } else if (type == 5) {
                currencyViewholder.currencyNumber.setText(newestBean.getResult().getNewslist().get(position).getReplycount() + "回帖");
            } else if (type == 2) {
                currencyViewholder.currencyNumber.setText(newestBean.getResult().getNewslist().get(position).getReplycount() + "评论");
            } else {
                return null;
            }
        }
        return convertView;
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


    class CurrencyViewholder {
        private TextView currencyTitle, currencyTime, currencyNumber;
        private ImageView currencyFiveIv;

        public CurrencyViewholder(View itemView) {
            currencyTitle = (TextView) itemView.findViewById(R.id.item_newest_five_title);
            currencyTime = (TextView) itemView.findViewById(R.id.item_newest_five_time);
            currencyNumber = (TextView) itemView.findViewById(R.id.item_newest_five_number);
            currencyFiveIv = (ImageView) itemView.findViewById(R.id.item_newest_five_iv);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return newestBean.getResult().getNewslist().get(position).getMediatype();
    }

    //通过毕加索来获取网络数据
    public void getIntentData(String url, ImageView view) {
        Picasso.with(context).load(url).placeholder(R.mipmap.fild).error(R.mipmap.fild).into(view);
    }

    //点击事件的接口
    interface OnClickListener {
        void onClick(int id);
    }
}

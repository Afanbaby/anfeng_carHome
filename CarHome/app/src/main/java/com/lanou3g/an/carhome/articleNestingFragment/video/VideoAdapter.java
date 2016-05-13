package com.lanou3g.an.carhome.articleNestingFragment.video;

import android.content.Context;
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
public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.MyVideoView> {
    private Context context;
    private VideoBean videoBean;
    private LayoutInflater inflater;

    public VideoAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setVideoBean(VideoBean videoBean) {
        this.videoBean = videoBean;
        notifyDataSetChanged();
    }


    @Override
    public MyVideoView onCreateViewHolder(ViewGroup parent, int viewType) {
        MyVideoView viewHolder;
        View videoView = inflater.inflate(R.layout.fragment_video, null);
        viewHolder = new MyVideoView(videoView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyVideoView holder, int position) {

        getIntentData(holder.videoIv, videoBean.getResult().getList().get(position).getSmallimg());
        holder.videoTitle.setText(videoBean.getResult().getList().get(position).getTitle());
        holder.videotime.setText(videoBean.getResult().getList().get(position).getUpdatetime());
        holder.videoNumber.setText(videoBean.getResult().getList().get(position).getPlaycount());
    }

    @Override
    public int getItemCount() {
        return videoBean != null ? videoBean.getResult().getList().size() : 0;
    }

    class MyVideoView extends RecyclerView.ViewHolder {
        private ImageView videoIv;
        private TextView videoTitle, videotime, videoNumber;

        public MyVideoView(View itemView) {
            super(itemView);
            videoIv = (ImageView) itemView.findViewById(R.id.item_video_iv);
            videoTitle = (TextView) itemView.findViewById(R.id.item_video_title);
            videotime = (TextView) itemView.findViewById(R.id.item_video_time);
            videoNumber = (TextView) itemView.findViewById(R.id.item_video_number);
        }
    }

    public void getIntentData(ImageView view, String url) {
//        Picasso.with(context).load(url).placeholder(R.mipmap.fild).error(R.mipmap.fild).into(view);
    }
}

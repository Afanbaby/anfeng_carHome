package com.lanou3g.an.carhome.articleNestingFragment.video;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.lanou3g.an.carhome.BuildConfig;
import com.lanou3g.an.carhome.R;
import com.lanou3g.an.carhome.articleNestingFragment.video.videoDetail.VideoDatailActivity;
import com.lanou3g.an.carhome.beas.BaseFragment;
import com.lanou3g.an.carhome.utils.DividerItemDecoration;

/**
 * Created by anfeng on 16/5/9.
 * 推荐中的视频
 */
public class VideoFragment extends BaseFragment implements VideoAdapter.OnClickListenter {
    private RecyclerView recyclerView;
    private VideoAdapter videoAdapter;

    @Override
    public int setLayout() {
        return R.layout.fragment_video;
    }

    @Override
    protected void initView() {
        recyclerView = bindView(R.id.fragment_video_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST));
        videoAdapter = new VideoAdapter(context);
    }

    @Override
    protected void initData() {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest("http://app.api.autohome.com.cn/autov5.0.0/news/videolist-pm2-vt0-s20-lastid0.json",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Gson gson = new Gson();
                        VideoBean videoBean = gson.fromJson(response, VideoBean.class);

                        videoAdapter.setVideoBean(videoBean);
                        recyclerView.setAdapter(videoAdapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
        videoAdapter.setOnClickListenter(this);
    }

    @Override
    public void onClick(int id) {
        Intent intent = new Intent();
        intent.putExtra("id", id);
        intent.setClass(context, VideoDatailActivity.class);
        startActivity(intent);
    }
}

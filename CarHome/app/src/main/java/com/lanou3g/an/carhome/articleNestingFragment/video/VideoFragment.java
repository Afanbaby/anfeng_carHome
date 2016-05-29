package com.lanou3g.an.carhome.articleNestingFragment.video;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.lanou3g.an.carhome.Collection;
import com.lanou3g.an.carhome.R;
import com.lanou3g.an.carhome.article.WebViewActivity;
import com.lanou3g.an.carhome.beas.BaseFragment;
import com.lanou3g.an.carhome.utils.DividerItemDecoration;
import com.lanou3g.an.carhome.utils.VolleySinge;

/**
 * Created by anfeng on 16/5/9.
 * 推荐中的视频
 */
public class VideoFragment extends BaseFragment implements VideoAdapter.OnClickListenter, View.OnClickListener {
    private RecyclerView recyclerView;
    private VideoAdapter videoAdapter;
    private LinearLayout linearLayoutAll;
    private static final String CLOSE_DRAWER = "com.lanou3g.an.carhome.CLOSEBROADCAST";
    private GetNameBroadcast getNameBroadcast;
    private TextView allTv;
    private VideoBean videoBean;

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
        linearLayoutAll = bindView(R.id.fragment_video_all_linearlayout);
        linearLayoutAll.setOnClickListener(this);
        allTv = bindView(R.id.fragment_video_all);
        //动态注册广播
        getNameBroadcast = new GetNameBroadcast();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.lanou3g.an.carhome.TYPENAME");
        context.registerReceiver(getNameBroadcast, filter);
    }

    @Override
    protected void initData() {

        VolleySinge.addRequest("http://app.api.autohome.com.cn/autov5.0.0/news/videolist-pm2-vt0-s20-lastid0.json",
                VideoBean.class,
                new Response.Listener<VideoBean>() {
                    @Override
                    public void onResponse(VideoBean response) {
                        videoBean = response;
                        videoAdapter.setVideoBean(response);
                        recyclerView.setAdapter(videoAdapter);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        videoAdapter.setOnClickListenter(this);
    }

    @Override
    public void onClick(int id, int positon) {
        Intent intent = new Intent();
        String url = "http://v.autohome.com.cn/v_4_" + id + ".html";
        intent.putExtra("url", url);
        Collection collection = new Collection();
        collection.setId((long) id);
        collection.setUrl(url);
        collection.setImageUrl(videoBean.getResult().getList().get(positon).getSmallimg());
        collection.setTitle(videoBean.getResult().getList().get(positon).getTitle());
        intent.putExtra("Collection", collection);
        intent.setClass(context, WebViewActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_video_all_linearlayout:
                Intent intent = new Intent(CLOSE_DRAWER);
                intent.putExtra("type", 3);
                context.sendBroadcast(intent);
                break;
        }
    }

    //当接收到广播的时候
    class GetNameBroadcast extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String name = intent.getStringExtra("name");
            int nameType = intent.getIntExtra("nameType", 0);
            if (nameType == 3) {
                allTv.setText(name);
            }

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        context.unregisterReceiver(getNameBroadcast);
    }
}

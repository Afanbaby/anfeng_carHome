package com.lanou3g.an.carhome.articleNestingFragment.video.videoDetail;

import android.content.Intent;
import android.webkit.WebView;

import com.lanou3g.an.carhome.R;
import com.lanou3g.an.carhome.beas.BaseActivity;

/**
 * Created by anfeng on 16/5/14.
 */
public class VideoDatailActivity extends BaseActivity {
    private WebView webView;

    @Override
    protected int getLayout() {
        return R.layout.activity_video_detail;
    }

    @Override
    protected void initView() {
        webView = bindView(R.id.activity_video_web_view);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        String url = "http://v.autohome.com.cn/v_4_" + id + ".html";
        webView.loadUrl(url);
    }
}

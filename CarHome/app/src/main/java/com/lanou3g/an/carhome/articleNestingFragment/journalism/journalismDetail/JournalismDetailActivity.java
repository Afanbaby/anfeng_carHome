package com.lanou3g.an.carhome.articleNestingFragment.journalism.journalismDetail;

import android.content.Intent;
import android.webkit.WebView;

import com.lanou3g.an.carhome.R;
import com.lanou3g.an.carhome.beas.BaseActivity;

/**
 * Created by anfeng on 16/5/14.
 */
public class JournalismDetailActivity extends BaseActivity {
    private WebView webView;

    @Override
    protected int getLayout() {
        return R.layout.activity_journalism_detail;
    }

    @Override
    protected void initView() {
        webView = bindView(R.id.activity_journalism_web_view);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        String url = "http://cont.app.autohome.com.cn/autov4.2.5/content/News/newscontent-a2-pm1-v4.2.5-n" + id + "-lz0-sp0-nt0-sa1-p0-c1-fs0-cw320.html";
        webView.loadUrl(url);
    }
}

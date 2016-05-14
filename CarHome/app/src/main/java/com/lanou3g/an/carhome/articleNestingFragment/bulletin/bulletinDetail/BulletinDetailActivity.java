package com.lanou3g.an.carhome.articleNestingFragment.bulletin.bulletinDetail;

import android.content.Intent;
import android.webkit.WebView;

import com.lanou3g.an.carhome.R;
import com.lanou3g.an.carhome.beas.BaseActivity;

/**
 * Created by anfeng on 16/5/13.
 */
public class BulletinDetailActivity extends BaseActivity {
    private WebView webView;

    @Override
    protected int getLayout() {
        return R.layout.activity_bulletin_detail;
    }

    @Override
    protected void initView() {
        webView = bindView(R.id.activity_bulletin_web_view);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        String url = "http://cont.app.autohome.com.cn/autov5.0.0/content/News/fastnewscontent-n" + id + "-lastid0-o1.json";
        webView.loadUrl(url);
    }
}

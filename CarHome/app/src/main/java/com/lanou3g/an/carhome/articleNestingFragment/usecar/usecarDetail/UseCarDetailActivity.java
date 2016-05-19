package com.lanou3g.an.carhome.articleNestingFragment.usecar.usecarDetail;

import android.content.Intent;
import android.webkit.WebView;

import com.lanou3g.an.carhome.R;
import com.lanou3g.an.carhome.beas.BaseActivity;

/**
 * Created by anfeng on 16/5/14.
 */
public class UseCarDetailActivity extends BaseActivity {
    private WebView webView;
    private String imageUrl;

    @Override
    protected int getLayout() {
        return R.layout.activity_usecar_detail;
    }

    @Override
    protected void initView() {
        webView = bindView(R.id.activity_usecar_web_view);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        int type = intent.getIntExtra("type", 0);
        if (type == 0) {
            imageUrl = "http://cont.app.autohome.com.cn/autov5.0.0/content/news/newscontent-n" + id + "-t0.json";
        } else {
            imageUrl = "http://app.api.autohome.com.cn/autov5.0.0/news/newsdetailpicarticle-pm2-nid" + id + ".json";
        }
        webView.loadUrl(imageUrl);
    }
}

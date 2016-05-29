package com.lanou3g.an.carhome.sale;

import android.content.Intent;
import android.webkit.WebView;

import com.lanou3g.an.carhome.R;
import com.lanou3g.an.carhome.beas.BaseActivity;

import java.net.URISyntaxException;

/**
 * Created by anfeng on 16/5/22.
 */
public class SaleWebViewActivity extends BaseActivity {

    private WebView webView;

    @Override
    protected int getLayout() {
        return R.layout.activity_sale_web_view;
    }

    @Override
    protected void initView() {
        webView = bindView(R.id.sale_web_view);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        //String url = intent.getStringExtra("sale_url");
       // webView.loadUrl(url);
    }
}

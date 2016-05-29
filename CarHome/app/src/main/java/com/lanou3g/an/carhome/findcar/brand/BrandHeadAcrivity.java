package com.lanou3g.an.carhome.findcar.brand;

import android.util.Log;
import android.webkit.WebView;

import com.lanou3g.an.carhome.R;
import com.lanou3g.an.carhome.beas.BaseActivity;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * Created by anfeng on 16/5/20.
 */
public class BrandHeadAcrivity extends BaseActivity {
    private WebView webView;

    @Override
    protected int getLayout() {
        return R.layout.activity_brand_head_detail;
    }

    @Override
    protected void initView() {
        webView = (WebView) findViewById(R.id.activity_brand_head_web_view);
        //注册事件
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initData() {

    }

    //需要一个方法来接收数据
    @Subscribe(threadMode = ThreadMode.MainThread)
    public void getData(String url){
        Log.d("aa",url);
        webView.loadUrl(url);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //取消注册
        EventBus.getDefault().unregister(this);
    }
}

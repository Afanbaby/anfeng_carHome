package com.lanou3g.an.carhome.articleNestingFragment.usecar.usecarDetail;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.lanou3g.an.carhome.R;
import com.lanou3g.an.carhome.beas.BaseActivity;
import com.lanou3g.an.carhome.utils.VolleySinge;

/**
 * Created by anfeng on 16/5/14.
 */
public class UseCarDetailActivity extends BaseActivity implements View.OnClickListener {
    private ViewPager viewPager;
    private String imageUrl;
    private UseCarDetailAdapter useCarDetailAdapter;
    private TextView bodyTv, titleTv, numberTv, numTv;
    private UseCarDetailBean useCarDetailBean;
    private LinearLayout backLayout, linearTop, linearBottom;
    private ViewHideBroadcast viewHideBroadcast;
    private int i = 0;

    @Override
    protected int getLayout() {
        return R.layout.activity_usecar_detail;
    }

    @Override
    protected void initView() {
        viewPager = bindView(R.id.activity_usecar_vp);
        useCarDetailAdapter = new UseCarDetailAdapter(this);
        bodyTv = bindView(R.id.activity_usecar_body);
        titleTv = bindView(R.id.activity_usecar_title);
        numberTv = bindView(R.id.activity_usecar_number);
        numTv = bindView(R.id.activity_usecar_num);
        backLayout = bindView(R.id.activity_usecar_layout);
        backLayout.setOnClickListener(this);
        linearTop = bindView(R.id.activity_usecar_detail_linear1);
        linearBottom = bindView(R.id.activity_usecar_detail_linear2);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        imageUrl = "http://app.api.autohome.com.cn/autov5.0.0/news/newsdetailpicarticle-pm2-nid" + id + ".json";
        VolleySinge.addRequest(imageUrl, UseCarDetailBean.class,
                new Response.Listener<UseCarDetailBean>() {
                    @Override
                    public void onResponse(UseCarDetailBean response) {
                        bodyTv.setText(response.getResult().getImages().get(0).getDescription());
                        titleTv.setText(response.getResult().getTitle());
                        numberTv.setText(response.getResult().getReplycount() + "");
                        useCarDetailBean = response;
                        useCarDetailAdapter.setUseCarDetailBean(response);
                        viewPager.setAdapter(useCarDetailAdapter);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        //ViewPager的滑动监听
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bodyTv.setText(useCarDetailBean.getResult().getImages().get(position).getDescription());
                titleTv.setText(useCarDetailBean.getResult().getTitle());
                numberTv.setText(useCarDetailBean.getResult().getReplycount() + "");
                numTv.setText(position + 1 + "/" + useCarDetailBean.getResult().getImages().size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //注册广播(当点击图片时,需要隐藏文字)
        viewHideBroadcast = new ViewHideBroadcast();
        IntentFilter inflater = new IntentFilter();
        inflater.addAction("com.lanou3g.an.carhome.VIEWHIDE");
        registerReceiver(viewHideBroadcast, inflater);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_usecar_layout:
                finish();
                break;
        }
    }

    //当接收到图片的点击事件后,需要做的事情
    class ViewHideBroadcast extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            i = i + 1;
            if (i % 2 != 0) {
                showTranslateAnim(0, 0, 0, 1);
                showTranslateAnim1(0, 0, 0, -1);
                linearTop.setVisibility(View.GONE);
            } else {
                showTranslateAnim(0, 0, 1, 0);
                showTranslateAnim1(0, 0, -1, 0);
                linearTop.setVisibility(View.VISIBLE);
            }
        }
    }

    //平移动画
    private void showTranslateAnim(int startX, int endX, int startY, int endY) {
        TranslateAnimation translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, startX, Animation.RELATIVE_TO_PARENT, endX
                , Animation.RELATIVE_TO_SELF, startY, Animation.RELATIVE_TO_PARENT, endY
        );
        translateAnimation.setFillAfter(true);
        translateAnimation.setDuration(2000);
        linearBottom.startAnimation(translateAnimation);
    }


    //平移动画

    private void showTranslateAnim1(int startX, int endX, int startY, int endY) {
        TranslateAnimation translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, startX, Animation.RELATIVE_TO_PARENT, endX
                , Animation.RELATIVE_TO_SELF, startY, Animation.RELATIVE_TO_PARENT, endY
        );
        translateAnimation.setFillAfter(true);
        translateAnimation.setDuration(1000);
        linearTop.startAnimation(translateAnimation);
    }
}

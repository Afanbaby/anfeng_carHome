package com.lanou3g.an.carhome.articleNestingFragment.bulletin.bulletinDetail;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.lanou3g.an.carhome.R;
import com.lanou3g.an.carhome.beas.BaseActivity;
import com.lanou3g.an.carhome.utils.VolleySinge;

/**
 * Created by anfeng on 16/5/13.
 */
public class BulletinDetailActivity extends BaseActivity {
    private ListView listView;
    private BulletinDetailAdapter bulletinDetailAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_bulletin_detail;
    }

    @Override
    protected void initView() {
        listView = bindView(R.id.activity_bulletin_detail_lv);
        bulletinDetailAdapter = new BulletinDetailAdapter(this);
        View headView = LayoutInflater.from(this).inflate(R.layout.item_bulletin_detail_head, null);
        listView.addHeaderView(headView);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        String url = "http://cont.app.autohome.com.cn/autov5.0.0/content/News/fastnewscontent-n" + id + "-lastid0-o1.json";
        VolleySinge.addRequest(url, BulletinDetailBean.class,
                new Response.Listener<BulletinDetailBean>() {
                    @Override
                    public void onResponse(BulletinDetailBean response) {
                        bulletinDetailAdapter.setBulletinDetailBean(response);
                        listView.setAdapter(bulletinDetailAdapter);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
    }

}

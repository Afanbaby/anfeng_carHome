package com.lanou3g.an.carhome.articleNestingFragment.bulletin.bulletinDetail;

import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.lanou3g.an.carhome.R;
import com.lanou3g.an.carhome.beas.BaseActivity;
import com.lanou3g.an.carhome.utils.VolleySinge;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by anfeng on 16/5/13.
 */
public class BulletinDetailActivity extends BaseActivity {
    private ListView listView;
    private BulletinDetailAdapter bulletinDetailAdapter;
    private ImageView backIv, headIv;
    private TextView typeName, title, body, name, number, time;

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
        backIv = bindView(R.id.activity_bulletin_detail_iv);
        headIv = bindView(R.id.activity_bulletin_detail_headIv);
        typeName = bindView(R.id.activity_bulletin_detail_typename);
        title = bindView(R.id.activity_bulletin_detail_title);
        body = bindView(R.id.activity_bulletin_detail_prople_body);
        name = bindView(R.id.activity_bulletin_detail_prople_name);
        number = bindView(R.id.activity_bulletin_detail_number);
        time = bindView(R.id.activity_bulletin_detail_prople_time);
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
                        getIntentData(backIv, response.getResult().getNewsdata().getImg());
                        getIntentData(headIv, response.getResult().getNewsdata().getHeadimg());
                        typeName.setText(response.getResult().getNewsdata().getNewstypeanme());
                        title.setText(response.getResult().getNewsdata().getTitle());
                        body.setText(response.getResult().getNewsdata().getSummary());
                        name.setText(response.getResult().getNewsdata().getNewsauthor());
                        number.setText(response.getResult().getNewsdata().getReviewcount() + "评论");
                        time.setText(response.getResult().getNewsdata().getCreatetime());


                        listView.setAdapter(bulletinDetailAdapter);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
    }

    private void getIntentData(ImageView view, String url) {
        Picasso.with(this).load(url).error(R.mipmap.fild).placeholder(R.mipmap.fild).into(view);
    }
}

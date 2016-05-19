package com.lanou3g.an.carhome.articleNestingFragment.bulletin;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.lanou3g.an.carhome.BuildConfig;
import com.lanou3g.an.carhome.R;
import com.lanou3g.an.carhome.articleNestingFragment.bulletin.bulletinDetail.BulletinDetailActivity;
import com.lanou3g.an.carhome.beas.BaseFragment;
import com.lanou3g.an.carhome.main.MainActivity;
import com.lanou3g.an.carhome.utils.DividerItemDecoration;
import com.lanou3g.an.carhome.utils.SwipeRefreshLoadingLayout;
import com.lanou3g.an.carhome.utils.VolleySinge;

/**
 * Created by anfeng on 16/5/9.
 * 推荐中的快报
 */
public class BulletinFragment extends BaseFragment implements BulletinAdapter.OnClickListener, View.OnClickListener, SwipeRefreshLoadingLayout.OnLoadListener, SwipeRefreshLoadingLayout.OnRefreshListener {
    private RecyclerView recyclerView;
    private BulletinAdapter bulletinAdapter;
    private LinearLayout layoutBrand, layoutCategory;
    private static final String CLOSE_DRAWER = "com.lanou3g.an.carhome.CLOSEBROADCAST";
    private SwipeRefreshLoadingLayout srff;
    private GetNameBroadcast getNameBroadcast;
    private TextView allBrand, allGrade;

    @Override
    public int setLayout() {
        return R.layout.fragment_bulletin;
    }

    @Override
    protected void initView() {
        recyclerView = bindView(R.id.fragment_bulletin_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        bulletinAdapter = new BulletinAdapter(context);
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST));
        layoutBrand = bindView(R.id.fragment_bulletin_brand);
        layoutCategory = bindView(R.id.fragment_bulletin_category);
        layoutBrand.setOnClickListener(this);
        layoutCategory.setOnClickListener(this);
        srff = bindView(R.id.srff);
        allBrand = bindView(R.id.fragment_bulletin_all_brand);
        allGrade = bindView(R.id.fragment_bulletin_all_grade);

        //动态注册广播
        getNameBroadcast = new GetNameBroadcast();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.lanou3g.an.carhome.TYPENAME");
        context.registerReceiver(getNameBroadcast, filter);
    }

    @Override
    protected void initData() {

        VolleySinge.addRequest("http://app.api.autohome.com.cn/autov5.0.0/news/fastnewslist-pm2-b0-l0-s20-lastid0.json"
                , new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        BulletinBean bulletinBean = gson.fromJson(response, BulletinBean.class);
                        bulletinAdapter.setBulletinBean(bulletinBean);
                        recyclerView.setAdapter(bulletinAdapter);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        bulletinAdapter.setOnClickListener(this);
        srff.setOnLoadListener(this);
        srff.setOnRefreshListener(this);
    }

    @Override
    public void onClick(int id) {
        if (BuildConfig.DEBUG) Log.d("BulletinFragment", "**");
        Intent intent = new Intent();
        intent.putExtra("id", id);
        intent.setClass(context, BulletinDetailActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_bulletin_brand:
                Intent intent = new Intent(CLOSE_DRAWER);
                intent.putExtra("type", 1);
                context.sendBroadcast(intent);
                break;
            case R.id.fragment_bulletin_category:
                Intent intent1 = new Intent(CLOSE_DRAWER);
                intent1.putExtra("type", 2);
                context.sendBroadcast(intent1);
                break;
        }
    }


    @Override
    public void onLoad() {
        Toast.makeText(context, "上拉加载", Toast.LENGTH_SHORT).show();
        srff.setRefreshing(false);
        VolleySinge.addRequest("http://app.api.autohome.com.cn/autov5.0.0/news/fastnewslist-pm2-b0-l0-s20-lastid0.json", BulletinBean.class, new Response.Listener<BulletinBean>() {
            @Override
            public void onResponse(BulletinBean response) {

                Log.d("BulletinFragment", "response:" + response);
                bulletinAdapter.setBulletinBean(response);
                recyclerView.setAdapter(bulletinAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }

    @Override
    public void onRefresh() {
        Toast.makeText(context, "下拉刷新", Toast.LENGTH_SHORT).show();
        srff.setRefreshing(false);
        VolleySinge.addRequest("http://app.api.autohome.com.cn/autov5.0.0/news/fastnewslist-pm2-b0-l0-s20-lastid0.json", BulletinBean.class, new Response.Listener<BulletinBean>() {
            @Override
            public void onResponse(BulletinBean response) {
                bulletinAdapter.setBulletinBean(response);
                recyclerView.setAdapter(bulletinAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }

    //当接收到广播的时候
    class GetNameBroadcast extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String name = intent.getStringExtra("name");
            int nameType = intent.getIntExtra("nameType", 0);
            if (nameType == 1) {
                allBrand.setText(name);
                VolleySinge.addRequest("http://app.api.autohome.com.cn/autov5.0.0/news/fastnewslist-pm2-b0-l0-s20-lastid0.json",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Gson gson = new Gson();
                                BulletinBean bulletinBean = gson.fromJson(response, BulletinBean.class);
                                Log.d("GetNameBroadcast", "还不会做呢");

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        });
            } else {
                allGrade.setText(name);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        context.unregisterReceiver(getNameBroadcast);
    }
}

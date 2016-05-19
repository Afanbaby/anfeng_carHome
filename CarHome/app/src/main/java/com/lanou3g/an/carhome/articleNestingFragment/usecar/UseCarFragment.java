package com.lanou3g.an.carhome.articleNestingFragment.usecar;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.lanou3g.an.carhome.R;
import com.lanou3g.an.carhome.articleNestingFragment.usecar.usecarDetail.UseCarDetailActivity;
import com.lanou3g.an.carhome.beas.BaseFragment;
import com.lanou3g.an.carhome.utils.DividerItemDecoration;

/**
 * Created by anfeng on 16/5/9.
 * 推荐中的用车
 */
public class UseCarFragment extends BaseFragment implements UseCarAdapter.OnClickListener {
    private RecyclerView recyclerView;
    private UseCarAdapter useCarAdapter;

    @Override
    public int setLayout() {
        return R.layout.fragment_usecar;
    }

    @Override
    protected void initView() {
        recyclerView = bindView(R.id.fragment_usecar_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST));
        useCarAdapter = new UseCarAdapter(context);
        useCarAdapter.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(
                "http://app.api.autohome.com.cn/autov5.0.0/news/newslist-pm2-c0-nt82-p1-s20-l0.json",
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                UseCarBean useCarBean = gson.fromJson(response, UseCarBean.class);
                useCarAdapter.setUseCarBean(useCarBean);
                recyclerView.setAdapter(useCarAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }

    @Override
    public void onClick(int id,int type) {
        Intent intent = new Intent();
        intent.setClass(context, UseCarDetailActivity.class);
        intent.putExtra("id",id);
        intent.putExtra("type",type);
        startActivity(intent);
    }
}

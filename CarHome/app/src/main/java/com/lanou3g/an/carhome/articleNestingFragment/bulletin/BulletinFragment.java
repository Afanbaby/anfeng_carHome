package com.lanou3g.an.carhome.articleNestingFragment.bulletin;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.lanou3g.an.carhome.R;
import com.lanou3g.an.carhome.beas.BaseFragment;
import com.lanou3g.an.carhome.utils.DividerItemDecoration;

/**
 * Created by anfeng on 16/5/9.
 * 推荐中的快报
 */
public class BulletinFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private BulletinAdapter bulletinAdapter;

    @Override
    public int setLayout() {
        return R.layout.fragment_bulletin;
    }

    @Override
    protected void initView() {
        recyclerView = bindView(R.id.fragment_bulletin_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        bulletinAdapter = new BulletinAdapter(context);
        recyclerView.addItemDecoration(new DividerItemDecoration(context,DividerItemDecoration.VERTICAL_LIST));
    }

    @Override
    protected void initData() {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest("http://app.api.autohome.com.cn/autov5.0.0/news/fastnewslist-pm2-b0-l0-s20-lastid0.json",
                new Response.Listener<String>() {
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
        requestQueue.add(stringRequest);
    }
}

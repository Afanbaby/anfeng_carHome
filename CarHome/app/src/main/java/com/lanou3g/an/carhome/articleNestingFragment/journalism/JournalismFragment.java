package com.lanou3g.an.carhome.articleNestingFragment.journalism;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.lanou3g.an.carhome.BuildConfig;
import com.lanou3g.an.carhome.R;
import com.lanou3g.an.carhome.beas.BaseFragment;
import com.lanou3g.an.carhome.utils.DividerItemDecoration;

/**
 * Created by anfeng on 16/5/9.
 * 推荐中的新闻
 */
public class JournalismFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private JournalismBean journalismBean;
    private JournalismAdapter journalismAdapter;

    @Override
    public int setLayout() {
        return R.layout.fragment_journalism;
    }

    @Override
    protected void initView() {
        recyclerView = bindView(R.id.fragment_jorunalism_rv);
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        journalismAdapter = new JournalismAdapter(context);
    }

    @Override
    protected void initData() {

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(
                "http://app.api.autohome.com.cn/autov5.0.0/news/newslist-pm1-c0-nt1-p1-s30-l0.json"
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                journalismBean = gson.fromJson(response, JournalismBean.class);
                journalismAdapter.setJournalismBean(journalismBean);
                recyclerView.setAdapter(journalismAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);

    }
}

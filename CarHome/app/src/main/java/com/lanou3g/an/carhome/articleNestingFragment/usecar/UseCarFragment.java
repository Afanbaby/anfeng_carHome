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
import com.lanou3g.an.carhome.Collection;
import com.lanou3g.an.carhome.R;
import com.lanou3g.an.carhome.article.WebViewActivity;
import com.lanou3g.an.carhome.articleNestingFragment.usecar.usecarDetail.UseCarDetailActivity;
import com.lanou3g.an.carhome.beas.BaseFragment;
import com.lanou3g.an.carhome.utils.DividerItemDecoration;
import com.lanou3g.an.carhome.utils.VolleySinge;

/**
 * Created by anfeng on 16/5/9.
 * 推荐中的用车
 */
public class UseCarFragment extends BaseFragment implements UseCarAdapter.OnClickListener {
    private RecyclerView recyclerView;
    private UseCarAdapter useCarAdapter;
    private UseCarBean useCarBean;

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

        VolleySinge.addRequest("http://app.api.autohome.com.cn/autov5.0.0/news/newslist-pm2-c0-nt82-p1-s20-l0.json", UseCarBean.class,
                new Response.Listener<UseCarBean>() {
                    @Override
                    public void onResponse(UseCarBean response) {
                        useCarBean = response;
                        useCarAdapter.setUseCarBean(response);
                        recyclerView.setAdapter(useCarAdapter);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
    }

    @Override
    public void onClick(int id, int type, int position) {
        Intent intent = new Intent();
        if (type == 10) {
            intent.setClass(context, UseCarDetailActivity.class);
            intent.putExtra("id", id);
        } else {
            String url = "http://cont.app.autohome.com.cn/autov5.0.0/content/news/newscontent-n" + id + "-t0.json";
            intent.putExtra("url", url);
            Collection collection = new Collection();
            collection.setId((long) id);
            collection.setImageUrl(useCarBean.getResult().getNewslist().get(position).getSmallpic());
            collection.setUrl(url);
            collection.setTitle(useCarBean.getResult().getNewslist().get(position).getTitle());
            intent.putExtra("Collection", collection);
            intent.setClass(context, WebViewActivity.class);
        }
        startActivity(intent);
    }
}

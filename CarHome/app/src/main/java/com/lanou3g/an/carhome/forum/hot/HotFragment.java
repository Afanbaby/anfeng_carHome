package com.lanou3g.an.carhome.forum.hot;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.lanou3g.an.carhome.Collection;
import com.lanou3g.an.carhome.R;
import com.lanou3g.an.carhome.article.WebViewActivity;
import com.lanou3g.an.carhome.beas.BaseFragment;
import com.lanou3g.an.carhome.utils.DividerItemDecoration;
import com.lanou3g.an.carhome.utils.VolleySinge;

/**
 * Created by anfeng on 16/5/9.
 */
public class HotFragment extends BaseFragment implements HotAdapter.OnClickListener {

    private RecyclerView recyclerView;
    private HotAdapter hotAdapter;
    private HotBean hotBean;

    @Override
    public int setLayout() {
        return R.layout.fragment_hot;
    }

    @Override
    protected void initView() {
        recyclerView = bindView(R.id.fragment_hot_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST));
        hotAdapter = new HotAdapter(context);
    }

    @Override
    protected void initData() {
        VolleySinge.addRequest("http://club.app.autohome.com.cn/club_v5.6.0/club/shotfoumlist-pm1-p1-s50.json", HotBean.class,
                new Response.Listener<HotBean>() {
                    @Override
                    public void onResponse(HotBean response) {
                        hotBean = response;
                        hotAdapter.setHotBean(response);
                        recyclerView.setAdapter(hotAdapter);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        hotAdapter.setOnClickListener(this);
    }

    @Override
    public void onClick(int id, int position) {
        Intent intent = new Intent();
        String url = "http://forum.app.autohome.com.cn/forum_v5.7.0/forum/club/topiccontent-a2-pm2-v5.8.5-t" + id + "-o0-p1-s20-c1-nt0-fs0-sp0-al0-cw360.json";
        intent.putExtra("url", url);
        Collection collection = new Collection();
        collection.setId((long) id);
        collection.setTitle(hotBean.getResult().getList().get(position).getTitle());
        collection.setImageUrl(null);
        collection.setUrl(url);
        intent.putExtra("Collection", collection);
        intent.setClass(context, WebViewActivity.class);
        startActivity(intent);
    }
}

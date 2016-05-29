package com.lanou3g.an.carhome.articleNestingFragment.journalism;

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
import com.lanou3g.an.carhome.beas.BaseFragment;
import com.lanou3g.an.carhome.utils.DividerItemDecoration;
import com.lanou3g.an.carhome.utils.VolleySinge;


/**
 * Created by anfeng on 16/5/9.
 * 推荐中的新闻
 */
public class JournalismFragment extends BaseFragment implements JournalismAdapter.OnClickListener {
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
        journalismAdapter.setOnClickListener(this);
    }

    @Override
    protected void initData() {

        VolleySinge.addRequest("http://app.api.autohome.com.cn/autov5.0.0/news/newslist-pm1-c0-nt1-p1-s30-l0.json", JournalismBean.class,
                new Response.Listener<JournalismBean>() {
                    @Override
                    public void onResponse(JournalismBean response) {
                        journalismBean = response;
                        journalismAdapter.setJournalismBean(response);
                        recyclerView.setAdapter(journalismAdapter);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

    }

    @Override
    public void onClick(int id, int position) {
        Intent intent = new Intent();
        String url = "http://cont.app.autohome.com.cn/autov4.2.5/content/News/newscontent-a2-pm1-v4.2.5-n" + id + "-lz0-sp0-nt0-sa1-p0-c1-fs0-cw320.html";
        intent.putExtra("url", url);
        Collection collection = new Collection();
        collection.setId((long) id);
        collection.setTitle(journalismBean.getResult().getNewslist().get(position).getTitle());
        collection.setUrl(url);
        collection.setImageUrl(journalismBean.getResult().getNewslist().get(position).getSmallpic());
        intent.putExtra("Collection", collection);
        intent.setClass(context, WebViewActivity.class);
        startActivity(intent);
    }
}

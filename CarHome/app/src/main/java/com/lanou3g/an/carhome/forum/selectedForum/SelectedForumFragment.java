package com.lanou3g.an.carhome.forum.selectedForum;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.lanou3g.an.carhome.Collection;
import com.lanou3g.an.carhome.R;
import com.lanou3g.an.carhome.article.WebViewActivity;
import com.lanou3g.an.carhome.beas.BaseFragment;
import com.lanou3g.an.carhome.utils.VolleySinge;

/**
 * Created by anfeng on 16/5/9.
 */
public class SelectedForumFragment extends BaseFragment implements SelectedForumAdapter.OnClickListener {

    private SelectedForumAdapter selectedForumAdapter;
    private RecyclerView recyclerView;
    private SelectedForumBean selectedForumBean;

    @Override
    public int setLayout() {
        return R.layout.fragment_selectedforum;
    }

    @Override
    protected void initView() {
        recyclerView = bindView(R.id.fragment_selected_forum_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        selectedForumAdapter = new SelectedForumAdapter(context);
        selectedForumAdapter.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        VolleySinge.addRequest("http://app.api.autohome.com.cn/autov5.0.0/club/jingxuantopic-pm2-c0-p1-s20.json",
                SelectedForumBean.class, new Response.Listener<SelectedForumBean>() {
                    @Override
                    public void onResponse(SelectedForumBean response) {
                        selectedForumBean = response;
                        selectedForumAdapter.setSelectedForumBean(response);
                        recyclerView.setAdapter(selectedForumAdapter);
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
        String url = "http://forum.app.autohome.com.cn/autov5.0.0/forum/club/topiccontent-a2-pm2-v5.0.0-t" + id + "-o0-p1-s20-c1-nt0-fs0-sp0-al0-cw320.json";
        intent.putExtra("url", url);
        Collection collection = new Collection();
        collection.setId((long) id);
        collection.setTitle(selectedForumBean.getResult().getList().get(position).getTitle());
        collection.setUrl(url);
        collection.setImageUrl(selectedForumBean.getResult().getList().get(position).getSmallpic());
        intent.putExtra("Collection", collection);
        intent.setClass(context, WebViewActivity.class);
        startActivity(intent);
    }
}

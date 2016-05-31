package com.lanou3g.an.carhome.articleNestingFragment.usecar;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
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
public class UseCarFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    private PullToRefreshListView pullToRefreshListView;
    private UseCarAdapter useCarAdapter;
    private UseCarBean useCarBean;
    private ILoadingLayout startLabels;

    @Override
    public int setLayout() {
        return R.layout.fragment_usecar;
    }

    @Override
    protected void initView() {
        pullToRefreshListView = bindView(R.id.fragment_usecar_lv);
        useCarAdapter = new UseCarAdapter(context);
        //设置下拉
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
    }

    @Override
    protected void initData() {

        startLabels = pullToRefreshListView.getLoadingLayoutProxy(true, false);
        startLabels.setRefreshingLabel("正在刷新");
        startLabels.setReleaseLabel("释放开始刷新");

        ILoadingLayout startLabelsNext = pullToRefreshListView.getLoadingLayoutProxy(false, true);
        startLabelsNext.setRefreshingLabel("正在加载");
        startLabelsNext.setPullLabel("上拉加载更多");

        //使用单例进行解析
        VolleySinge.addRequest("http://app.api.autohome.com.cn/autov5.0.0/news/newslist-pm2-c0-nt82-p1-s20-l0.json", UseCarBean.class,
                new Response.Listener<UseCarBean>() {
                    @Override
                    public void onResponse(UseCarBean response) {
                        useCarBean = response;
                        useCarAdapter.setUseCarBean(response);
                        pullToRefreshListView.setAdapter(useCarAdapter);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        pullToRefreshListView.setOnItemClickListener(this);


        //设置刷新事件
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            //下拉事件
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                VolleySinge.addRequest("http://app.api.autohome.com.cn/autov5.0.0/news/newslist-pm2-c0-nt82-p1-s20-l0.json", UseCarBean.class,
                        new Response.Listener<UseCarBean>() {
                            @Override
                            public void onResponse(UseCarBean response) {
                                useCarAdapter.setUseCarBean(response);
                                pullToRefreshListView.setAdapter(useCarAdapter);
                                //设置停止
                                pullToRefreshListView.onRefreshComplete();
                                String str = DateUtils.formatDateTime(getContext(), System.currentTimeMillis(), DateUtils.
                                        FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
                                startLabels.setLastUpdatedLabel("最后更新时间:" + str);
                                Toast.makeText(context, "刷新数据成功", Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        });
            }

            //上拉加载
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                VolleySinge.addRequest("http://app.api.autohome.com.cn/autov5.0.0/news/newslist-pm2-c0-nt82-p2-s20-l" + useCarBean.getResult().getNewslist().get(useCarBean.getResult().getNewslist().size() - 1).getLasttime() + ".json",
                        UseCarBean.class,
                        new Response.Listener<UseCarBean>() {
                            @Override
                            public void onResponse(UseCarBean response) {
                                useCarBean.getResult().getNewslist().addAll(response.getResult().getNewslist());
                                useCarAdapter.setUseCarBean(useCarBean);
                                pullToRefreshListView.onRefreshComplete();
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        });
            }
        });
    }

    //list的每一行的点击事件
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //获取id
        id = useCarBean.getResult().getNewslist().get(position).getId();
        Intent intent = new Intent();
        int type = useCarBean.getResult().getNewslist().get(position).getMediatype();
        //根据type的不同进行处理
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

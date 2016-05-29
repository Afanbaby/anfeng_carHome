package com.lanou3g.an.carhome.findcar.depreciate;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.lanou3g.an.carhome.R;
import com.lanou3g.an.carhome.beas.BaseFragment;
import com.lanou3g.an.carhome.eventBus.DataBean;
import com.lanou3g.an.carhome.utils.VolleySinge;

import de.greenrobot.event.EventBus;

/**
 * Created by anfeng on 16/5/9.
 */
public class DepreciateFragment extends BaseFragment implements View.OnClickListener {
    private TextView brandTv, priceTv, levelTv, sortTv;
    private RecyclerView recyclerView;
    private DepreciateAdapter depreciateAdapter;

    @Override
    public int setLayout() {
        return R.layout.fragment_depreciate;
    }

    @Override
    protected void initView() {
        recyclerView = bindView(R.id.fragment_depreciate_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        depreciateAdapter = new DepreciateAdapter(context);
        brandTv = bindView(R.id.fragment_depreciate_brand_tv);
        priceTv = bindView(R.id.fragment_depreciate_brand_price_tv);
        levelTv = bindView(R.id.fragment_depreciate_brand_level_tv);
        sortTv = bindView(R.id.fragment_depreciate_sort_tv);
        brandTv.setOnClickListener(this);
        priceTv.setOnClickListener(this);
        levelTv.setOnClickListener(this);
        sortTv.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        VolleySinge.addRequest("http://223.99.255.20/cars.app.autohome.com.cn/dealer_v5.7.0/dealer/pdspecs-pm2-pi0-c110100-o0-b0-ss0-sp0-p1-s20-l0-minp0-maxp0-lon0.0-lat0.0.json", DepreciateBean.class,
                new Response.Listener<DepreciateBean>() {
                    @Override
                    public void onResponse(DepreciateBean response) {
                        depreciateAdapter.setDepreciateBean(response);
                        recyclerView.setAdapter(depreciateAdapter);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_depreciate_brand_tv:
                EventBus.getDefault().post(new DataBean(4));
                break;
            case R.id.fragment_depreciate_brand_price_tv:
                EventBus.getDefault().post(new DataBean(5));
                break;
            case R.id.fragment_depreciate_brand_level_tv:
                EventBus.getDefault().post(new DataBean(6));
                break;
            case R.id.fragment_depreciate_sort_tv:
                EventBus.getDefault().post(new DataBean(7));
                break;

        }
    }
}

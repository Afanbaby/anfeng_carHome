package com.lanou3g.an.carhome.findcar.screen;

import android.graphics.Color;
import android.graphics.drawable.LayerDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.lanou3g.an.carhome.R;
import com.lanou3g.an.carhome.beas.BaseFragment;
import com.lanou3g.an.carhome.utils.DividerItemDecoration;
import com.lanou3g.an.carhome.utils.VolleySinge;

/**
 * Created by anfeng on 16/5/9.
 * 筛选
 */
public class ScreenFragment extends BaseFragment implements View.OnClickListener {

    private RecyclerView recyclerView;
    private ScreenAdapter screenAdapter;
    private LinearLayout conditionLayout;
    private ImageView conditionIv;
    private TextView conditionTv;
    private int i = 0;
    private MyPopupWindow myPopupWindow;

    @Override
    public int setLayout() {
        return R.layout.fragment_screen;
    }

    @Override
    protected void initView() {
        recyclerView = bindView(R.id.fragment_screen_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST));
        screenAdapter = new ScreenAdapter(context);
        conditionLayout = bindView(R.id.fragment_screen_layout);
        conditionLayout.setOnClickListener(this);
        conditionIv = bindView(R.id.fragment_screen_condition_iv);
        conditionTv = bindView(R.id.fragment_screen_condition_tv);
        myPopupWindow = new MyPopupWindow(context);
    }


    @Override
    protected void initData() {
        VolleySinge.addRequest("http://223.99.255.20/cars.app.autohome.com.cn/cars_v5.7.0/cars/gethotseries-a2-pm2-v5.8.5-p1-s20.json", ScreenBean.class,
                new Response.Listener<ScreenBean>() {
                    @Override
                    public void onResponse(ScreenBean response) {
                        screenAdapter.setScreenBean(response);
                        recyclerView.setAdapter(screenAdapter);
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
            //当点击条件的时候
            case R.id.fragment_screen_layout:
                i++;
                if (i % 2 != 0) {
                    conditionIv.setImageResource(R.mipmap.triangle_top);
                    conditionTv.setTextColor(Color.BLUE);
                    myPopupWindow.showAsDropDown(conditionLayout);

                } else {
                    conditionIv.setImageResource(R.mipmap.triangle_down);
                    conditionTv.setTextColor(Color.BLACK);
                    myPopupWindow.dismiss();
                }
                break;
        }
    }


}

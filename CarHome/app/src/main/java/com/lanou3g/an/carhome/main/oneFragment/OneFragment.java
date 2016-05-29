package com.lanou3g.an.carhome.main.oneFragment;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.lanou3g.an.carhome.R;
import com.lanou3g.an.carhome.beas.BaseFragment;
import com.lanou3g.an.carhome.eventBus.DataBeanName;
import com.lanou3g.an.carhome.findcar.brand.BrandBean;
import com.lanou3g.an.carhome.main.twoFragment.TwoFragmentAdapter;
import com.lanou3g.an.carhome.utils.SideBar;
import com.lanou3g.an.carhome.utils.VolleySinge;

import de.greenrobot.event.EventBus;

/**
 * Created by anfeng on 16/5/25.
 */
public class OneFragment extends BaseFragment implements SideBar.OnTouchingLetterChangedListener {
    private OneFragmentAdapter oneFragmentAdapter;
    private ExpandableListView lv;
    private SideBar sideBar;
    private TextView sidebarTv;
    private BrandBean brandBean;

    @Override
    public int setLayout() {
        return R.layout.fragment_one;
    }

    @Override
    protected void initView() {
        lv = bindView(R.id.fragment_one_lv);
        //取消父item的指示器
        lv.setGroupIndicator(null);
        oneFragmentAdapter = new OneFragmentAdapter(context);
        sideBar = bindView(R.id.fragment_one_sidebar);
        sidebarTv = bindView(R.id.fragment_one_sidebar_tv);
    }

    @Override
    protected void initData() {
        VolleySinge.addRequest("http://app.api.autohome.com.cn/autov5.0.0/news/brandsfastnews-pm1-ts0.json", BrandBean.class,
                new Response.Listener<BrandBean>() {
                    @Override
                    public void onResponse(BrandBean response) {
                        brandBean = response;
                        oneFragmentAdapter.setBrandBean(response);
                        lv.setAdapter(oneFragmentAdapter);
                        //让首次加载的时候,让父item展开
                        if (response.getResult().getBrandlist().size() > 0) {
                            for (int i = 0; i < response.getResult().getBrandlist().size(); i++) {
                                lv.expandGroup(i);
                            }
                        }
                        //取消父item的点击收缩
                        lv.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                            @Override
                            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                                return true;
                            }
                        });

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        sideBar.setOnTouchingLetterChangedListener(this);
        //给抽屉里面的Child的item添加点击事件
        lv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(context, brandBean.getResult().getBrandlist().get(groupPosition).getList().get(childPosition).getName(), Toast.LENGTH_SHORT).show();
                String name = brandBean.getResult().getBrandlist().get(groupPosition).getList().get(childPosition).getName();
                EventBus.getDefault().post(new DataBeanName(name));
                return false;
            }
        });
    }

    @Override
    public void onTouchingLetterChanged(String s) {
        //该字母首次出现的位置
        sideBar.setTextView(sidebarTv);
        int position = oneFragmentAdapter.getPosition(s.charAt(0));
        if (position != -1) {
            lv.setSelectedGroup(position);
        }
    }
}

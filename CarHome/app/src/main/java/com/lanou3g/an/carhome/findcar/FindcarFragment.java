package com.lanou3g.an.carhome.findcar;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.lanou3g.an.carhome.R;
import com.lanou3g.an.carhome.beas.BaseFragment;
import com.lanou3g.an.carhome.findcarNesting.BrandFragment;
import com.lanou3g.an.carhome.findcarNesting.DepreciateFragment;
import com.lanou3g.an.carhome.findcarNesting.ScreenFragment;
import com.lanou3g.an.carhome.findcarNesting.SecondHandCarFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anfeng on 16/5/9.
 */
public class FindcarFragment extends BaseFragment {

    private List<Fragment> fragmentList;
    private FindcarAdapter findcarAdapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    public int setLayout() {
        return R.layout.fragment_findcar;
    }

    @Override
    protected void initView() {
        viewPager = bindView(R.id.findcar_vp);
        tabLayout = bindView(R.id.findcar_tab);
        findcarAdapter = new FindcarAdapter(getChildFragmentManager());

    }

    @Override
    protected void initData() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new BrandFragment());
        fragmentList.add(new DepreciateFragment());
        fragmentList.add(new ScreenFragment());
        fragmentList.add(new SecondHandCarFragment());
        findcarAdapter.setFragmentList(fragmentList);
        viewPager.setAdapter(findcarAdapter);

        tabLayout.setupWithViewPager(viewPager);
        //设置tabLayout
        tabLayout.setTabTextColors(Color.BLUE, Color.BLUE);
        tabLayout.setSelectedTabIndicatorColor(Color.BLUE);
    }
}

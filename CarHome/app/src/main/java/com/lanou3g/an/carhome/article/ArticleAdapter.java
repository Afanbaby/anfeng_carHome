package com.lanou3g.an.carhome.article;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.DisplayMetrics;
import android.widget.RadioButton;

import java.util.List;

/**
 * Created by anfeng on 16/5/9.
 */
public class ArticleAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList;
    private String[] titlts = {"最新","快报","视频","新闻",
            "评测","导购","行情","用车","技术","文化","改装",
            "游记","原创视频","说客"};

    public void setFragmentList(List<Fragment> fragmentList) {
        this.fragmentList = fragmentList;
    }

    public ArticleAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList != null ? fragmentList.size() : 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titlts[position];
    }

}

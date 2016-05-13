package com.lanou3g.an.carhome.forum;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.lanou3g.an.carhome.R;
import com.lanou3g.an.carhome.articleNestingFragment.CultureFragment;
import com.lanou3g.an.carhome.articleNestingFragment.ShoppingFragment;
import com.lanou3g.an.carhome.beas.BaseFragment;
import com.lanou3g.an.carhome.forumNesting.CommonForumFragment;
import com.lanou3g.an.carhome.forumNesting.HotFragment;
import com.lanou3g.an.carhome.forumNesting.SelectedForumFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anfeng on 16/5/9.
 */
public class ForumFragment extends BaseFragment {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<Fragment> fragmentList;
    private ForumAdapter forumAdapter;

    @Override
    public int setLayout() {
        return R.layout.fragment_forum;
    }

    @Override
    protected void initView() {
        viewPager = bindView(R.id.forum_vp);
        tabLayout = bindView(R.id.forum_tab);
        forumAdapter = new ForumAdapter(getChildFragmentManager());
    }

    @Override
    protected void initData() {
        fragmentList = new ArrayList<>();

        fragmentList.add(new SelectedForumFragment());
        fragmentList.add(new HotFragment());
        fragmentList.add(new CommonForumFragment());

        forumAdapter.setFragmentList(fragmentList);
        viewPager.setAdapter(forumAdapter);
        tabLayout.setupWithViewPager(viewPager);
        //设置tabLayout
        tabLayout.setTabTextColors(Color.BLUE, Color.BLUE);
        tabLayout.setSelectedTabIndicatorColor(Color.BLUE);
    }
}

package com.lanou3g.an.carhome.article;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.lanou3g.an.carhome.R;
import com.lanou3g.an.carhome.articleNestingFragment.bulletin.BulletinFragment;
import com.lanou3g.an.carhome.articleNestingFragment.CultureFragment;
import com.lanou3g.an.carhome.articleNestingFragment.EvaluatingFragment;
import com.lanou3g.an.carhome.articleNestingFragment.JournalismFragment;
import com.lanou3g.an.carhome.articleNestingFragment.LobbyistsFragment;
import com.lanou3g.an.carhome.articleNestingFragment.newest.NewestFragment;
import com.lanou3g.an.carhome.articleNestingFragment.OriginalVideoFragment;
import com.lanou3g.an.carhome.articleNestingFragment.QuotationFragment;
import com.lanou3g.an.carhome.articleNestingFragment.RefitFragment;
import com.lanou3g.an.carhome.articleNestingFragment.ShoppingFragment;
import com.lanou3g.an.carhome.articleNestingFragment.TechnologyFragment;
import com.lanou3g.an.carhome.articleNestingFragment.TravelsFragment;
import com.lanou3g.an.carhome.articleNestingFragment.UseCarFragment;
import com.lanou3g.an.carhome.articleNestingFragment.video.VideoFragment;
import com.lanou3g.an.carhome.beas.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anfeng on 16/5/9.
 */
public class ArticleFragment extends BaseFragment {

    private TabLayout tab;
    private ViewPager vp;
    private ArticleAdapter articleAdapter;
    private List<Fragment> fragmentList;

    @Override
    public int setLayout() {
        return R.layout.fragment_article;
    }

    @Override
    protected void initView() {
        tab = bindView(R.id.article_tab);
        vp = bindView(R.id.vp);
        articleAdapter = new ArticleAdapter(getChildFragmentManager());

    }

    @Override
    protected void initData() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new NewestFragment());
        fragmentList.add(new BulletinFragment());
        fragmentList.add(new VideoFragment());
        fragmentList.add(new CultureFragment());
        fragmentList.add(new EvaluatingFragment());
        fragmentList.add(new JournalismFragment());
        fragmentList.add(new LobbyistsFragment());
        fragmentList.add(new OriginalVideoFragment());
        fragmentList.add(new QuotationFragment());
        fragmentList.add(new RefitFragment());
        fragmentList.add(new ShoppingFragment());
        fragmentList.add(new TechnologyFragment());
        fragmentList.add(new TravelsFragment());
        fragmentList.add(new UseCarFragment());

        articleAdapter.setFragmentList(fragmentList);
        vp.setAdapter(articleAdapter);
        vp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));
        tab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        //设置tabLayout
        tab.setupWithViewPager(vp);
        tab.setTabTextColors(Color.GRAY, Color.BLUE);
        tab.setSelectedTabIndicatorColor(Color.BLUE);
    }
}

package com.lanou3g.an.carhome.forum;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;

import com.lanou3g.an.carhome.R;
import com.lanou3g.an.carhome.beas.BaseFragment;
import com.lanou3g.an.carhome.forum.commonForum.CommonForumFragment;
import com.lanou3g.an.carhome.forum.hot.HotFragment;
import com.lanou3g.an.carhome.forum.selectedForum.SelectedForumFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anfeng on 16/5/9.
 */
public class ForumFragment extends BaseFragment implements View.OnClickListener {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<Fragment> fragmentList;
    private ForumAdapter forumAdapter;
    private RadioButton radio1, radio2, radio3, radio4, radio5, radio6, radio7, radio8, radio9, radio10, radio11, radio12,
            radio13, radio14, radio15, radio16, radio17, radio18, radio19, radio20, radio21, radio22, radio23, radio24, radio25,
            radio26, radio27, radio28, radio29, radio30, radio31, radio32,
            radio33, radio34, radio35, radio36, radio37, radio38, radio39, radio40, radio41;

    @Override
    public int setLayout() {
        return R.layout.fragment_forum;
    }

    @Override
    protected void initView() {
        viewPager = bindView(R.id.forum_vp);
        tabLayout = bindView(R.id.forum_tab);
        forumAdapter = new ForumAdapter(getChildFragmentManager());
        radioData();
    }

    private void radioData() {
        radio1 = bindView(R.id.fragment_forum_radio1);
        radio1.setOnClickListener(this);
        radio2 = bindView(R.id.fragment_forum_radio2);
        radio2.setOnClickListener(this);
        radio3 = bindView(R.id.fragment_forum_radio3);
        radio3.setOnClickListener(this);
        radio4 = bindView(R.id.fragment_forum_radio4);
        radio4.setOnClickListener(this);
        radio5 = bindView(R.id.fragment_forum_radio5);
        radio5.setOnClickListener(this);
        radio6 = bindView(R.id.fragment_forum_radio6);
        radio6.setOnClickListener(this);
        radio7 = bindView(R.id.fragment_forum_radio7);
        radio7.setOnClickListener(this);
        radio8 = bindView(R.id.fragment_forum_radio8);
        radio8.setOnClickListener(this);
        radio9 = bindView(R.id.fragment_forum_radio9);
        radio9.setOnClickListener(this);
        radio10 = bindView(R.id.fragment_forum_radio10);
        radio10.setOnClickListener(this);
        radio11 = bindView(R.id.fragment_forum_radio11);
        radio11.setOnClickListener(this);
        radio12 = bindView(R.id.fragment_forum_radio12);
        radio12.setOnClickListener(this);
        radio13 = bindView(R.id.fragment_forum_radio13);
        radio13.setOnClickListener(this);
        radio14 = bindView(R.id.fragment_forum_radio14);
        radio14.setOnClickListener(this);
        radio15 = bindView(R.id.fragment_forum_radio15);
        radio15.setOnClickListener(this);
        radio16 = bindView(R.id.fragment_forum_radio16);
        radio16.setOnClickListener(this);
        radio17 = bindView(R.id.fragment_forum_radio17);
        radio17.setOnClickListener(this);
        radio18 = bindView(R.id.fragment_forum_radio18);
        radio18.setOnClickListener(this);
        radio19 = bindView(R.id.fragment_forum_radio19);
        radio19.setOnClickListener(this);
        radio20 = bindView(R.id.fragment_forum_radio20);
        radio20.setOnClickListener(this);
        radio21 = bindView(R.id.fragment_forum_radio21);
        radio21.setOnClickListener(this);

        radio22 = bindView(R.id.fragment_forum_radio22);
        radio22.setOnClickListener(this);
        radio23 = bindView(R.id.fragment_forum_radio23);
        radio23.setOnClickListener(this);
        radio24 = bindView(R.id.fragment_forum_radio24);
        radio24.setOnClickListener(this);
        radio25 = bindView(R.id.fragment_forum_radio25);
        radio25.setOnClickListener(this);

        radio26 = bindView(R.id.fragment_forum_radio26);
        radio26.setOnClickListener(this);

        radio27 = bindView(R.id.fragment_forum_radio27);
        radio27.setOnClickListener(this);

        radio28 = bindView(R.id.fragment_forum_radio28);
        radio28.setOnClickListener(this);
        radio29 = bindView(R.id.fragment_forum_radio29);
        radio29.setOnClickListener(this);

        radio30 = bindView(R.id.fragment_forum_radio30);
        radio30.setOnClickListener(this);

        radio31 = bindView(R.id.fragment_forum_radio31);
        radio31.setOnClickListener(this);

        radio32 = bindView(R.id.fragment_forum_radio32);
        radio32.setOnClickListener(this);

        radio33 = bindView(R.id.fragment_forum_radio33);
        radio33.setOnClickListener(this);

        radio34 = bindView(R.id.fragment_forum_radio34);
        radio34.setOnClickListener(this);

        radio35 = bindView(R.id.fragment_forum_radio35);
        radio35.setOnClickListener(this);

        radio36 = bindView(R.id.fragment_forum_radio36);
        radio36.setOnClickListener(this);

        radio37 = bindView(R.id.fragment_forum_radio37);
        radio37.setOnClickListener(this);

        radio38 = bindView(R.id.fragment_forum_radio38);
        radio38.setOnClickListener(this);

        radio39 = bindView(R.id.fragment_forum_radio39);
        radio39.setOnClickListener(this);
        radio40 = bindView(R.id.fragment_forum_radio40);
        radio40.setOnClickListener(this);

        radio41 = bindView(R.id.fragment_forum_radio41);
        radio41.setOnClickListener(this);

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
        tabLayout.setTabTextColors(Color.GRAY, Color.BLUE);
        tabLayout.setSelectedTabIndicatorColor(Color.BLUE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_forum_radio1:

                break;
            case R.id.fragment_forum_radio2:


                break;
            case R.id.fragment_forum_radio3:

                break;
            case R.id.fragment_forum_radio4:

                break;
            case R.id.fragment_forum_radio5:

                break;
            case R.id.fragment_forum_radio6:

                break;
            case R.id.fragment_forum_radio7:

                break;
            case R.id.fragment_forum_radio8:

                break;
            case R.id.fragment_forum_radio9:

                break;
            case R.id.fragment_forum_radio10:

                break;
            case R.id.fragment_forum_radio11:

                break;
            case R.id.fragment_forum_radio12:

                break;
            case R.id.fragment_forum_radio13:

                break;
            case R.id.fragment_forum_radio14:

                break;
            case R.id.fragment_forum_radio15:

                break;
            case R.id.fragment_forum_radio16:

                break;
            case R.id.fragment_forum_radio17:

                break;
            case R.id.fragment_forum_radio18:

                break;
            case R.id.fragment_forum_radio19:

                break;
            case R.id.fragment_forum_radio20:

                break;
            case R.id.fragment_forum_radio21:

                break;
            case R.id.fragment_forum_radio22:

                break;
            case R.id.fragment_forum_radio23:

                break;
            case R.id.fragment_forum_radio24:

                break;
            case R.id.fragment_forum_radio25:

                break;
            case R.id.fragment_forum_radio26:

                break;
            case R.id.fragment_forum_radio27:

                break;
            case R.id.fragment_forum_radio28:

                break;
            case R.id.fragment_forum_radio29:

                break;
            case R.id.fragment_forum_radio30:

                break;
            case R.id.fragment_forum_radio31:

                break;
            case R.id.fragment_forum_radio32:

                break;
            case R.id.fragment_forum_radio33:

                break;
            case R.id.fragment_forum_radio34:

                break;
            case R.id.fragment_forum_radio35:

                break;
            case R.id.fragment_forum_radio36:

                break;
            case R.id.fragment_forum_radio37:

                break;
            case R.id.fragment_forum_radio38:

                break;
            case R.id.fragment_forum_radio39:

                break;
            case R.id.fragment_forum_radio40:

                break;
            case R.id.fragment_forum_radio41:

                break;

        }
    }
}

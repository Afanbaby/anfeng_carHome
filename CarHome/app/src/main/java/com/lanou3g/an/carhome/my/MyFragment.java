package com.lanou3g.an.carhome.my;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lanou3g.an.carhome.R;
import com.lanou3g.an.carhome.beas.BaseFragment;
import com.lanou3g.an.carhome.my.login.LoginActivity;
import com.lanou3g.an.carhome.my.myCollection.MyCollectionActivity;
import com.lanou3g.an.carhome.utils.ThemeChangeUtil;

/**
 * Created by anfeng on 16/5/9.
 */
public class MyFragment extends BaseFragment implements View.OnClickListener {

    private ImageView nightIv;
    private int i = 0;

    @Override
    public int setLayout() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initView() {
        nightIv = bindView(R.id.my_include_night);
        nightIv.setOnClickListener(this);
    }


    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_include_night:
                i = i + 1;
                if (i % 2 != 0) {
                    nightIv.setImageResource(R.mipmap.my_day);
                } else {
                    nightIv.setImageResource(R.mipmap.my_night);
                }
                //判断当前主题的状态
                if (ThemeChangeUtil.isChange) {
                    ThemeChangeUtil.isChange = false;
                } else {
                    ThemeChangeUtil.isChange = true;
                }
                //重新创建当前的Activity的实例
                getActivity().recreate();
                //  MyApplication.changeTTT();
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        i++;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (i == 1) {
            FragmentManager manager = getChildFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.fragment_my_frameLayout, new LoginAfterFragment());
            transaction.commit();
        } else {
            FragmentManager manager = getChildFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.fragment_my_frameLayout, new LoginBeforeFragment());
            transaction.commit();
        }
    }
}

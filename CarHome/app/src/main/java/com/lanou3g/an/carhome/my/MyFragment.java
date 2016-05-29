package com.lanou3g.an.carhome.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lanou3g.an.carhome.Collection;
import com.lanou3g.an.carhome.CollectionDao;
import com.lanou3g.an.carhome.R;
import com.lanou3g.an.carhome.article.GreendaoSingle;
import com.lanou3g.an.carhome.beas.BaseFragment;
import com.lanou3g.an.carhome.main.MainActivity;
import com.lanou3g.an.carhome.my.login.LoginActivity;
import com.lanou3g.an.carhome.my.myCollection.MyCollectionActivity;
import com.lanou3g.an.carhome.utils.ThemeChangeUtil;

import java.util.List;

/**
 * Created by anfeng on 16/5/9.
 */
public class MyFragment extends BaseFragment implements View.OnClickListener {

    private ImageView nightIv;
    private int i = 0;
    private LinearLayout collectionLayout, accountNumberLayout;

    @Override
    public int setLayout() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initView() {
        nightIv = bindView(R.id.my_include_night);
        nightIv.setOnClickListener(this);
        collectionLayout = bindView(R.id.my_collection_linear);
        collectionLayout.setOnClickListener(this);
        //账号登录的点击事件
        accountNumberLayout = bindView(R.id.fragment_my_link_man);
        accountNumberLayout.setOnClickListener(this);
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
                break;
            case R.id.my_collection_linear:
                Intent intent = new Intent();
                intent.setClass(context, MyCollectionActivity.class);
                startActivity(intent);
                break;
            case R.id.fragment_my_link_man:
                Intent loginIntent = new Intent();
                loginIntent.setClass(context, LoginActivity.class);
                startActivity(loginIntent);
                break;
        }
    }

}

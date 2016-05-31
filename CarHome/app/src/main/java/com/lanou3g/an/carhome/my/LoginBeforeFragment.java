package com.lanou3g.an.carhome.my;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;
import android.widget.LinearLayout;

import com.lanou3g.an.carhome.R;
import com.lanou3g.an.carhome.beas.BaseFragment;
import com.lanou3g.an.carhome.my.login.LoginActivity;
import com.lanou3g.an.carhome.my.myCollection.MyCollectionActivity;

/**
 * Created by anfeng on 16/5/30.
 */
public class LoginBeforeFragment extends BaseFragment implements View.OnClickListener {

    private LinearLayout collectionLayout, accountNumberLayout;

    @Override
    public int setLayout() {
        return R.layout.fragment_login_before;
    }

    @Override
    protected void initView() {
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

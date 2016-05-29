package com.lanou3g.an.carhome.my.login;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.lanou3g.an.carhome.R;
import com.lanou3g.an.carhome.beas.BaseActivity;
import com.lanou3g.an.carhome.my.register.RegisterActivity;

/**
 * Created by anfeng on 16/5/29.
 * 登录页面的Activity
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private TextView registerTv, returnTv;

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        registerTv = bindView(R.id.activity_login_register);
        registerTv.setOnClickListener(this);
        returnTv = bindView(R.id.activity_login_return);
        returnTv.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    //注册的点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_login_register:
                Intent intent = new Intent();
                intent.setClass(this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.activity_login_return:
                finish();
                break;
        }
    }
}

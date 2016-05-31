package com.lanou3g.an.carhome.my.login;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lanou3g.an.carhome.R;
import com.lanou3g.an.carhome.beas.BaseActivity;
import com.lanou3g.an.carhome.my.register.RegisterActivity;

/**
 * Created by anfeng on 16/5/29.
 * 登录页面的Activity
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private TextView registerTv, returnTv;
    private Button loginBtn;
    private EditText userEt;

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
        loginBtn = bindView(R.id.activity_login_btn);
        loginBtn.setOnClickListener(this);
        userEt = bindView(R.id.activity_login_user_et);
    }

    @Override
    protected void initData() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //注册的点击事件
            case R.id.activity_login_register:
                Intent intent = new Intent();
                intent.setClass(this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.activity_login_return:
                finish();
                break;
            case R.id.activity_login_btn:
                if (userEt.getText().length() == 0) {
                    Toast.makeText(LoginActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    finish();
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在onDestroy发送广播,若在点击事件中发送的话,然后finish掉,,这样那一面的广播接受就接收不到了
        //因为广播并不是一直在发送
        String userName = userEt.getText().toString();
        Intent sendIntent = new Intent("com.lanou3g.an.carhome.USERNAME");
        sendIntent.putExtra("userName", userName);
        sendBroadcast(sendIntent);
    }
}

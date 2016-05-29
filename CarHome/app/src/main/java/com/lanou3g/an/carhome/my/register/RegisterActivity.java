package com.lanou3g.an.carhome.my.register;

import android.view.View;
import android.widget.TextView;

import com.lanou3g.an.carhome.R;
import com.lanou3g.an.carhome.beas.BaseActivity;

/**
 * Created by anfeng on 16/5/29.
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private TextView returnTv;

    @Override
    protected int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        returnTv = bindView(R.id.activity_register_return);
        returnTv.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_register_return:
                finish();
                break;
        }
    }
}

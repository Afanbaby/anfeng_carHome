package com.lanou3g.an.carhome.articleNestingFragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lanou3g.an.carhome.BuildConfig;
import com.lanou3g.an.carhome.R;
import com.lanou3g.an.carhome.beas.BaseFragment;

/**
 * Created by anfeng on 16/5/9.
 * 推荐中的行情
 */
public class QuotationFragment extends BaseFragment implements View.OnClickListener {
    private static final String CLOSE_DRAWER = "com.lanou3g.an.carhome.CLOSEBROADCAST";
    private LinearLayout linearLayoutLocaltion;
    private GetNameBroadcast getNameBroadcast;
    private TextView localtionTv;

    @Override
    public int setLayout() {
        return R.layout.fragment_quotation;
    }

    @Override
    protected void initView() {
        linearLayoutLocaltion = bindView(R.id.fragment_quotation_all_linearlayout);
        linearLayoutLocaltion.setOnClickListener(this);
        //注册广播,用来接收name
        getNameBroadcast = new GetNameBroadcast();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.lanou3g.an.carhome.TYPENAME");
        context.registerReceiver(getNameBroadcast, intentFilter);
        localtionTv = bindView(R.id.fragment_quotation_localtion);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_quotation_all_linearlayout:
                Intent intent = new Intent(CLOSE_DRAWER);
                intent.putExtra("type", 4);
                context.sendBroadcast(intent);
                break;
        }
    }

    //当接收到广播的时候
    class GetNameBroadcast extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String name = intent.getStringExtra("name");
            int nameType = intent.getIntExtra("nameType", 0);
            if (nameType == 4) {
                localtionTv.setText(name);
            }
        }
    }
}

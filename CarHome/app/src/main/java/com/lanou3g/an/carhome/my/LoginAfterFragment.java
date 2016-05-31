package com.lanou3g.an.carhome.my;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou3g.an.carhome.R;
import com.lanou3g.an.carhome.article.ArticleFragment;
import com.lanou3g.an.carhome.beas.BaseFragment;
import com.lanou3g.an.carhome.findcar.FindcarFragment;
import com.lanou3g.an.carhome.forum.ForumFragment;
import com.lanou3g.an.carhome.main.MainActivity;
import com.lanou3g.an.carhome.sale.SaleFragment;
import com.lanou3g.an.carhome.utils.MyDrawable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anfeng on 16/5/30.
 */
public class LoginAfterFragment extends BaseFragment implements View.OnClickListener {

    private ImageView linkManIv;
    private TextView returnIv, userTv;
    private GetUserName getUserName;
    private FragmentManager manager;
    private List<Fragment> fragmentList;

    @Override
    public int setLayout() {
        return R.layout.activity_login_after;
    }

    @Override
    protected void initView() {
        linkManIv = bindView(R.id.myself_photo);
        returnIv = bindView(R.id.activity_login_return_login);
        returnIv.setOnClickListener(this);
        userTv = bindView(R.id.my_self_name);
        //注册广播
        getUserName = new GetUserName();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.lanou3g.an.carhome.USERNAME");
        context.registerReceiver(getUserName, intentFilter);
    }

    @Override
    protected void initData() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.my_love);
        linkManIv.setImageDrawable(new MyDrawable(bitmap));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //用户退出登录
            case R.id.activity_login_return_login:
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.changeFragment(2);

//                manager = mainActivity.getSupportFragmentManager();
//                fragmentList = new ArrayList<>();
//                fragmentList.add(new ArticleFragment());
//                fragmentList.add(new ForumFragment());
//                fragmentList.add(new FindcarFragment());
//                fragmentList.add(new SaleFragment());
//                fragmentList.add(new MyFragment());
//                manager.beginTransaction()
//                        .hide(fragmentList.get(0))
//                        .hide(fragmentList.get(1))
//                        .hide(fragmentList.get(2))
//                        .hide(fragmentList.get(3))
//                        .show(fragmentList.get(4))
//                        .commit();

                break;
        }
    }

    //接收用户名的广播
    class GetUserName extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String userName = intent.getStringExtra("userName");
            userTv.setText(userName);
        }
    }

    //取消广播(要在对应的方法中注销)
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        context.unregisterReceiver(getUserName);
    }
}

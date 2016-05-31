package com.lanou3g.an.carhome.main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.lanou3g.an.carhome.R;
import com.lanou3g.an.carhome.article.ArticleFragment;
import com.lanou3g.an.carhome.beas.BaseActivity;
import com.lanou3g.an.carhome.eventBus.DataBeanName;
import com.lanou3g.an.carhome.findcar.FindcarFragment;
import com.lanou3g.an.carhome.forum.ForumFragment;
import com.lanou3g.an.carhome.eventBus.DataBean;
import com.lanou3g.an.carhome.main.oneFragment.*;
import com.lanou3g.an.carhome.main.threeFragmnet.ThreeFragment;
import com.lanou3g.an.carhome.main.twoFragment.TwoFragment;
import com.lanou3g.an.carhome.my.MyFragment;
import com.lanou3g.an.carhome.sale.SaleFragment;
import com.lanou3g.an.carhome.utils.ExampleUtil;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.android.api.JPushInterface;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;


public class MainActivity extends BaseActivity implements View.OnClickListener {

    private RadioButton articleRb, forumRb, findcarRb, saleRb, myRb;
    private DrawerLayout drawerLayout;
    //关抽屉的广播
    private static final String CLOSE_DRAWER = "com.lanou3g.an.carhome.CLOSEBROADCAST";
    private CloseDrawer closeDrawer;
    private TextView closeTv, nameTv;
    private MainAdapter mainAdapter;
    private List<String> list;

    private String[] arrayLocation;
    private int type;
    public static boolean isForeground = false;
    private FragmentManager manager;
    private List<Fragment> fragmentList;


    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        articleRb = bindView(R.id.article_page);
        forumRb = bindView(R.id.forum_page);
        findcarRb = bindView(R.id.findcar_page);
        saleRb = bindView(R.id.sale_page);
        myRb = bindView(R.id.my_page);
        nameTv = bindView(R.id.main_name_text);
        mainAdapter = new MainAdapter(this);
        drawerLayout = bindView(R.id.main_drawer_layout);

        registerMessageReceiver();  // used for receive msg 极光推送
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
    }

    @Override
    protected void initData() {
        //注册事件
        EventBus.getDefault().register(this);
        articleRb.setOnClickListener(this);
        forumRb.setOnClickListener(this);
        findcarRb.setOnClickListener(this);
        saleRb.setOnClickListener(this);
        myRb.setOnClickListener(this);

        //动态注册广播
        closeDrawer = new CloseDrawer();
        IntentFilter filter = new IntentFilter();
        filter.addAction(CLOSE_DRAWER);
        registerReceiver(closeDrawer, filter);
        //关闭抽屉
        closeTv = bindView(R.id.close_drawer_text);
        closeTv.setOnClickListener(this);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        drawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }

        });

        manager = getSupportFragmentManager();
        fragmentList = new ArrayList<>();
        fragmentList.add(new ArticleFragment());
        fragmentList.add(new ForumFragment());
        fragmentList.add(new FindcarFragment());
        fragmentList.add(new SaleFragment());
        fragmentList.add(new MyFragment());
        manager.beginTransaction()
                .add(R.id.replace_view, fragmentList.get(0))
                .add(R.id.replace_view, fragmentList.get(1))
                .add(R.id.replace_view, fragmentList.get(2))
                .add(R.id.replace_view, fragmentList.get(3))
                .add(R.id.replace_view, fragmentList.get(4))
                .show(fragmentList.get(0))
                .hide(fragmentList.get(1))
                .hide(fragmentList.get(2))
                .hide(fragmentList.get(3))
                .hide(fragmentList.get(4))
                .commit();

    }


    /************/

    // 初始化 JPush。如果已经初始化，但没有登录成功，则执行重新登录。
    private void init() {
        JPushInterface.init(getApplicationContext());
    }


    @Override
    protected void onResume() {
        isForeground = true;
        super.onResume();
    }

    @Override
    protected void onPause() {
        isForeground = false;
        super.onPause();
    }

    //for receive customer msg from jpush server
    private MessageReceiver mMessageReceiver;
    public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";

    public void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        registerReceiver(mMessageReceiver, filter);
    }

    public class MessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                String messge = intent.getStringExtra(KEY_MESSAGE);
                String extras = intent.getStringExtra(KEY_EXTRAS);
                StringBuilder showMsg = new StringBuilder();
                showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
                if (!ExampleUtil.isEmpty(extras)) {
                    showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
                }

            }
        }
    }

    //当点击redioButton时,切换fragment
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.article_page:
                changeFragment(0);
                break;
            case R.id.forum_page:
                changeFragment(1);
                break;
            case R.id.findcar_page:
                changeFragment(2);
                break;
            case R.id.sale_page:
                changeFragment(3);
                break;
            case R.id.my_page:
                changeFragment(4);
                break;
            case R.id.close_drawer_text:
                drawerLayout.closeDrawer(Gravity.RIGHT);
                break;
        }
//        transaction.commit();
    }

    public void changeFragment(int positon) {
        manager.beginTransaction()
                .hide(fragmentList.get(0))
                .hide(fragmentList.get(1))
                .hide(fragmentList.get(2))
                .hide(fragmentList.get(3))
                .hide(fragmentList.get(4))
                .show(fragmentList.get(positon))
                .commit();
    }

    //当接到广播的时候,需要打开抽屉
    public class CloseDrawer extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {

            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            type = intent.getIntExtra("type", 0);
            if (type == 1) {
                transaction.replace(R.id.fragment_main_replace, new OneFragment());
                transaction.commit();
                nameTv.setText("全部品牌");
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN);
                drawerLayout.openDrawer(Gravity.RIGHT);
            } else if (type == 2) {
                transaction.replace(R.id.fragment_main_replace, new TwoFragment());
                transaction.commit();
                nameTv.setText("全部级别");
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN);
                drawerLayout.openDrawer(Gravity.RIGHT);
            } else if (type == 3) {
                nameTv.setText("视频分类");
                transaction.replace(R.id.fragment_main_replace, new ThreeFragment());
                transaction.commit();
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN);
                drawerLayout.openDrawer(Gravity.RIGHT);
            } else if (type == 4) {
                nameTv.setText("选择省份");
                list = new ArrayList<>();
                arrayLocation = getResources().getStringArray(R.array.data_location);
                for (int i = 0; i <= arrayLocation.length - 1; i++) {
                    list.add(arrayLocation[i]);
                }
                mainAdapter.setList(list);
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN);
                drawerLayout.openDrawer(Gravity.RIGHT);
            }
        }
    }

    //手动取消广播
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(closeDrawer);
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void getData(DataBean dataBean) {
        if (dataBean.getType() == 1) {
            nameTv.setText("车系论坛");
            drawerLayout.openDrawer(Gravity.RIGHT);
        }
        if (dataBean.getType() == 2) {
            nameTv.setText("地区论坛");
            drawerLayout.openDrawer(Gravity.RIGHT);
        }
        if (dataBean.getType() == 3) {
            nameTv.setText("主题论坛");
            drawerLayout.openDrawer(Gravity.RIGHT);
        }
        if (dataBean.getType() == 4) {
            nameTv.setText("品牌");
            drawerLayout.openDrawer(Gravity.RIGHT);
        }
        if (dataBean.getType() == 5) {
            nameTv.setText("价格");
            drawerLayout.openDrawer(Gravity.RIGHT);
        }
        if (dataBean.getType() == 6) {
            nameTv.setText("级别");
            drawerLayout.openDrawer(Gravity.RIGHT);
        }
        if (dataBean.getType() == 7) {
            nameTv.setText("排序");
            drawerLayout.openDrawer(Gravity.RIGHT);
        }
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void getDataName(DataBeanName dataBeanName) {
        if (dataBeanName.getName() != null) {
            drawerLayout.closeDrawer(Gravity.RIGHT);
        }
    }
}

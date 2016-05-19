package com.lanou3g.an.carhome.main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.Transition;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.lanou3g.an.carhome.BuildConfig;
import com.lanou3g.an.carhome.R;
import com.lanou3g.an.carhome.article.ArticleFragment;
import com.lanou3g.an.carhome.beas.BaseActivity;
import com.lanou3g.an.carhome.findcar.FindcarFragment;
import com.lanou3g.an.carhome.forum.ForumFragment;
import com.lanou3g.an.carhome.my.MyFragment;
import com.lanou3g.an.carhome.sale.SaleFragment;
import com.lanou3g.an.carhome.utils.SwipeRefreshLoadingLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private RadioButton articleRb, forumRb, findcarRb, saleRb, myRb;
    private DrawerLayout drawerLayout;
    private static final String CLOSE_DRAWER = "com.lanou3g.an.carhome.CLOSEBROADCAST";
    private CloseDrawer closeDrawer;
    private TextView closeTv, nameTv;
    private ListView listView;
    private MainAdapter mainAdapter;
    private List<String> list;
    private SideBar sideBar;
    private String[] array, arrayGrade, arrayVideo, arrayLocation;
    private int type;


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
        listView = bindView(R.id.main_lv);
        nameTv = bindView(R.id.main_name_text);
        mainAdapter = new MainAdapter(this);
        sideBar = bindView(R.id.sidrbar);

        //将首页作为进入的界面
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.replace_view, new ArticleFragment());
        transaction.commit();
        drawerLayout = bindView(R.id.main_drawer_layout);

    }

    @Override
    protected void initData() {
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
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MainAdapter.ViewHolder viewHolder = (MainAdapter.ViewHolder) view.getTag();
                viewHolder.typeImage.setVisibility(View.VISIBLE);
                viewHolder.typeName.setTextColor(Color.BLUE);
                drawerLayout.closeDrawer(Gravity.RIGHT);
                //发广播
                Intent intent = new Intent("com.lanou3g.an.carhome.TYPENAME");
                if (type == 1) {
                    intent.putExtra("nameType", 1);
                    intent.putExtra("name", array[position]);
                } else if (type == 2) {
                    intent.putExtra("nameType", 2);
                    intent.putExtra("name", arrayGrade[position]);
                } else if (type == 3) {
                    intent.putExtra("nameType", 3);
                    intent.putExtra("name", arrayVideo[position]);
                } else if (type == 4) {
                    intent.putExtra("nameType", 4);
                    intent.putExtra("name", arrayLocation[position]);
                }
                sendBroadcast(intent);
            }
        });

    }

    //当点击redioButton时,切换fragment
    @Override
    public void onClick(View v) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        switch (v.getId()) {
            case R.id.article_page:
                transaction.replace(R.id.replace_view, new ArticleFragment());
                break;
            case R.id.forum_page:
                transaction.replace(R.id.replace_view, new ForumFragment());
                break;
            case R.id.findcar_page:
                transaction.replace(R.id.replace_view, new FindcarFragment());
                break;
            case R.id.sale_page:
                transaction.replace(R.id.replace_view, new SaleFragment());
                break;
            case R.id.my_page:
                transaction.replace(R.id.replace_view, new MyFragment());
                break;
            case R.id.close_drawer_text:
                drawerLayout.closeDrawer(Gravity.RIGHT);
                break;
        }
        transaction.commit();
    }


    //当接到广播的时候,需要打开抽屉
    public class CloseDrawer extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {

            type = intent.getIntExtra("type", 0);
            if (type == 1) {
                nameTv.setText("全部品牌");
                list = new ArrayList<>();
                array = getResources().getStringArray(R.array.data);
                for (int i = 0; i <= array.length - 1; i++) {
                    list.add(array[i]);
                }
                mainAdapter.setList(list);
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN);
                drawerLayout.openDrawer(Gravity.RIGHT);
            } else if (type == 2) {
                nameTv.setText("全部级别");
                list = new ArrayList<>();
                arrayGrade = getResources().getStringArray(R.array.data_grade);
                for (int i = 0; i <= arrayGrade.length - 1; i++) {
                    list.add(arrayGrade[i]);
                }
                mainAdapter.setList(list);
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN);
                drawerLayout.openDrawer(Gravity.RIGHT);
            } else if (type == 3) {
                nameTv.setText("视频分类");
                list = new ArrayList<>();
                arrayVideo = getResources().getStringArray(R.array.data_video);
                for (int i = 0; i <= arrayVideo.length - 1; i++) {
                    list.add(arrayVideo[i]);
                }
                mainAdapter.setList(list);
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
            listView.setAdapter(mainAdapter);
        }
    }

    //手动取消广播
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(closeDrawer);
    }


}

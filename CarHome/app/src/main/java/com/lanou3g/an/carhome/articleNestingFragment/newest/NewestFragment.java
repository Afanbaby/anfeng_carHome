package com.lanou3g.an.carhome.articleNestingFragment.newest;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;
import com.google.gson.Gson;
import com.lanou3g.an.carhome.BuildConfig;
import com.lanou3g.an.carhome.R;
import com.lanou3g.an.carhome.articleNestingFragment.newest.newestDetail.NewestDetailAvtivity;
import com.lanou3g.an.carhome.beas.BaseFragment;
import com.lanou3g.an.carhome.utils.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by anfeng on 16/5/9.
 * 推荐中的最新
 */
public class NewestFragment extends BaseFragment implements NewestAdapter.OnClickListener {


    private RecyclerViewHeader header;
    private RecyclerView recyclerView;
    private NewestAdapter newestAdapter;
    private LayoutInflater inflater;
    private ViewPager mviewPager;
    /**
     * 用于小圆点图片
     */
    private List<ImageView> dotViewList;
    /**
     * 用于存放轮播效果图片
     */
    private List<ImageView> list;
    LinearLayout dotLayout;
    private int currentItem = 0;//当前页面
    boolean isAutoPlay = true;//是否自动轮播
    private ScheduledExecutorService scheduledExecutorService;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            if (msg.what == 100) {
                mviewPager.setCurrentItem(currentItem);
            }
        }
    };
    private NewestBean newestBean;

    @Override
    public int setLayout() {
        return R.layout.fragment_newest;
    }

    @Override
    protected void initView() {
        recyclerView = bindView(R.id.item_newest_bom_rv);

        newestAdapter = new NewestAdapter(context);
        header = RecyclerViewHeader.fromXml(context, R.layout.image_item);

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST));
        inflater = LayoutInflater.from(context);
        header.attachTo(recyclerView);
        mviewPager = bindView(R.id.myviewPager);
        dotLayout = bindView(R.id.dotLayout);


        /******/

        dotLayout.removeAllViews();

        //判断是否轮播
        if (isAutoPlay) {
            //如果是，就开启轮播切换
            startPlay();
        }
    }

    @Override
    protected void initData() {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(
                "http://app.api.autohome.com.cn/autov4.2.5/news/newslist-a2-pm1-v4.2.5-c0-nt0-p1-s30-l0.html"
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                newestBean = gson.fromJson(response, NewestBean.class);
                //初始化小圆点
                initViewImage();
                newestAdapter.setNewestBean(newestBean);
                recyclerView.setAdapter(newestAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue.add(request);
        newestAdapter.setOnClickListener(this);
    }

    @Override
    public void onClick(int id) {
        Intent intent = new Intent();
        intent.putExtra("webId", id);
        intent.setClass(context, NewestDetailAvtivity.class);
        startActivity(intent);
    }


    public void initViewImage() {
        dotViewList = new ArrayList<ImageView>();
        list = new ArrayList<ImageView>();

        for (int i = 0; i < 4; i++) {
            ImageView dotView = new ImageView(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new ActionBar.LayoutParams(
                    ActionBar.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            params.leftMargin = 15;//设置小圆点的外边距
            params.rightMargin = 15;

            params.height = 40;//设置小圆点的大小
            params.width = 40;

            if (i == 0) {
                dotView.setBackgroundResource(R.mipmap.point_pressed);
            } else {

                dotView.setBackgroundResource(R.mipmap.point_unpressed);
            }
            dotLayout.addView(dotView, params);

            dotViewList.add(dotView);
            //上面是动态添加了四个小圆点
        }


        ImageView img1 = (ImageView) inflater.inflate(R.layout.scroll_vew_item, null);
        ImageView img2 = (ImageView) inflater.inflate(R.layout.scroll_vew_item, null);
        ImageView img3 = (ImageView) inflater.inflate(R.layout.scroll_vew_item, null);
        ImageView img4 = (ImageView) inflater.inflate(R.layout.scroll_vew_item, null);

        img1.setBackgroundResource(R.mipmap.main_img1);
        img2.setBackgroundResource(R.mipmap.main_img2);
        img3.setBackgroundResource(R.mipmap.main_img3);
        img4.setBackgroundResource(R.mipmap.main_img4);
        list.add(img1);
        list.add(img2);
        list.add(img3);
        list.add(img4);

        ImagePaperAdapter adapter = new ImagePaperAdapter((ArrayList<ImageView>) list);
        List<String> list = new ArrayList<>();
        for (int i = 0; i <= newestBean.getResult().getFocusimg().size() - 1; i++) {
            String url = newestBean.getResult().getFocusimg().get(0).getImgurl();
            list.add(url);
            adapter.setStringList(list);
        }
        mviewPager.setAdapter(adapter);
        mviewPager.setCurrentItem(0);
        mviewPager.setOnPageChangeListener(new MyPageChangeListener());
    }

    /**
     * 开始轮播图切换
     */
    private void startPlay() {
        //ExecutorService：可安排在给定的延迟后运行或定期执行的命令。
        //scheduleAtFixedRate 和 scheduleWithFixedDelay 方法创建并执行某些在取消前一直定期运行的任务。
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(new SlideShowTask(), 1, 4, TimeUnit.SECONDS);
        //根据他的参数说明，第一个参数是执行的任务(这里就是切换轮播图的任务)，第二个参数是第一次执行的间隔，第三个参数是执行任务的周期；
    }

    /**
     * 执行轮播图切换任务
     */
    private class SlideShowTask implements Runnable {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            synchronized (mviewPager) {
                currentItem = (currentItem + 1) % list.size();
                handler.sendEmptyMessage(100);
            }
        }
    }

    /**
     * ViewPager的监听器
     * 当ViewPager中页面的状态发生改变时调用
     */
    private class MyPageChangeListener implements ViewPager.OnPageChangeListener {
        //当用手滑动的时候，将轮播取消
        boolean isAutoPlay = false;

        //onPageScrollStateChanged：当状态发生改变的时候回调用
        @Override
        public void onPageScrollStateChanged(int arg0) {
            // TODO Auto-generated method stub
            switch (arg0) {
                case 1:// 手势滑动，空闲中；arg0 = 1 默示正在滑动
                    isAutoPlay = false;
                    System.out.println(" 手势滑动，空闲中");
                    break;
                case 2:// 界面切换中;arg0==2的时辰默示滑动完毕了
                    isAutoPlay = true;
                    System.out.println(" 界面切换中");
                    break;
                case 0:// 滑动结束，即切换完毕或者加载完毕;arg0==0的时辰默示什么都没做
                    // 当前为最后一张，此时从右向左滑，则切换到第一张
                    if (mviewPager.getCurrentItem() == mviewPager.getAdapter().getCount() - 1 && !isAutoPlay) {
                        mviewPager.setCurrentItem(0);
                        System.out.println(" 滑动到最后一张");
                    }
                    // 当前为第一张，此时从左向右滑，则切换到最后一张
                    else if (mviewPager.getCurrentItem() == 0 && !isAutoPlay) {
                        mviewPager.setCurrentItem(mviewPager.getAdapter().getCount() - 1);
                        System.out.println(" 滑动到第一张");
                    }
                    break;
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // TODO Auto-generated method stub
        }

        //当页面改变的时候调用的方法，也就是滑动页面的时候，改变小圆点的背景颜色
        @Override
        public void onPageSelected(int pos) {
            // TODO Auto-generated method stub
            //这里面动态改变小圆点的被背景，来实现效果
            currentItem = pos;
            for (int i = 0; i < dotViewList.size(); i++) {
                if (i == pos) {
                    ((View) dotViewList.get(pos)).setBackgroundResource(R.mipmap.point_pressed);
                } else {
                    ((View) dotViewList.get(i)).setBackgroundResource(R.mipmap.point_unpressed);
                }
            }
        }

    }
}

package com.lanou3g.an.carhome.sale;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.lanou3g.an.carhome.Collection;
import com.lanou3g.an.carhome.R;
import com.lanou3g.an.carhome.article.WebViewActivity;
import com.lanou3g.an.carhome.beas.BaseFragment;
import com.lanou3g.an.carhome.sale.saleBean.SaleFragmentBean;
import com.lanou3g.an.carhome.sale.saleBean.SaleFragmentCampaignBean;
import com.lanou3g.an.carhome.sale.saleBean.SaleFragmentImageBean;
import com.lanou3g.an.carhome.utils.SwipeRefreshLoadingLayout;
import com.lanou3g.an.carhome.utils.VolleySinge;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by anfeng on 16/5/9.
 * 发现页面
 */
public class SaleFragment extends BaseFragment implements SwipeRefreshLoadingLayout.OnLoadListener, SwipeRefreshLoadingLayout.OnRefreshListener, View.OnClickListener {

    private ListView listView;
    private SaleAdapter saleAdapter;
    private SaleFragmentBean saleFragmentBean;
    private SaleFragmentImageBean saleFragmentImageBean;
    private SaleFragmentCampaignBean saleFragmentCampaignBean;
    private LayoutInflater inflater;
    private ViewPager mviewPager;
    private SwipeRefreshLoadingLayout srff;
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
    private ImageView fiveIv1, fiveIv2;
    private TextView fiveTitle1, fiveTitle2, fiveSmallTitle1, fiveSmallTitle2, fivePrice1, fivePrice2, fiveSmallPrice1, fiveSmallPrice2;
    private View headViewFive;
    private View headViewSix;
    private ImageView sixIv1, sixIv2, sixIv3, sixIv4;
    private TextView sixTitle1, sixTitle2, sixTitle3, sixTitle4,
            sixSmallTitle1, sixSmallTitle2, sixSmallTitle3, sixSmallTitle4,
            sixPrice1, sixPrice2, sixPrice3, sixPrice4,
            sixSmallPrice1, sixSmallPrice2, sixSmallPrice3, sixSmallPrice4;
    private View headViewTwo;
    private ImageView twoIv1, twoIv2, twoIv3, twoIv4, twoIv5, twoIv6, twoIv7, twoIv8;
    private TextView twoTitle1, twoTitle2, twoTitle3, twoTitle4, twoTitle5, twoTitle6, twoTitle7, twoTitle8;
    private View headViewFour;
    private ImageView fourIv1, fourIv2, fourIv3, fourIv4, fourIv5, fourIv6;
    private ImageView threeIv1, threeIv2, threeIv3;
    private TextView moreTv;
    private View headView;
    private ImageView img;


    @Override
    public int setLayout() {
        return R.layout.fragment_sale;
    }

    @Override
    protected void initView() {
        listView = bindView(R.id.fragment_sale_lv);
        saleAdapter = new SaleAdapter(context);
        headView = getLayoutInflater(null).inflate(R.layout.item_sale_one, null);
        listView.addHeaderView(headView);
        headViewTwo = LayoutInflater.from(context).inflate(R.layout.item_sale_two, null);
        //初始化数据
        initHeadTwoData();
        listView.addHeaderView(headViewTwo);
        View headViewThree = LayoutInflater.from(context).inflate(R.layout.item_sale_three, null);
        moreTv = (TextView) headViewThree.findViewById(R.id.item_sale_three_more);
        moreTv.setOnClickListener(this);
        threeIv1 = (ImageView) headViewThree.findViewById(R.id.item_sale_three_iv1);
        threeIv2 = (ImageView) headViewThree.findViewById(R.id.item_sale_three_iv2);
        threeIv3 = (ImageView) headViewThree.findViewById(R.id.item_sale_three_iv3);
        listView.addHeaderView(headViewThree);
        headViewFour = LayoutInflater.from(context).inflate(R.layout.item_sale_four, null);
        initHeadFourData();

        listView.addHeaderView(headViewFour);
        headViewFive = LayoutInflater.from(context).inflate(R.layout.item_sale_five, null);
        //初始化数据
        initHeadFiveData();
        listView.addHeaderView(headViewFive);
        headViewSix = LayoutInflater.from(context).inflate(R.layout.item_sale_six, null);
        initHeadSixData();
        listView.addHeaderView(headViewSix);

        inflater = LayoutInflater.from(context);

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

    private void initHeadFourData() {
        fourIv1 = (ImageView) headViewFour.findViewById(R.id.item_sale_four_iv1);
        fourIv2 = (ImageView) headViewFour.findViewById(R.id.item_sale_four_iv2);
        fourIv3 = (ImageView) headViewFour.findViewById(R.id.item_sale_four_iv3);
        fourIv4 = (ImageView) headViewFour.findViewById(R.id.item_sale_four_iv4);
        fourIv5 = (ImageView) headViewFour.findViewById(R.id.item_sale_four_iv5);
        fourIv6 = (ImageView) headViewFour.findViewById(R.id.item_sale_four_iv6);
    }

    private void initHeadTwoData() {
        twoIv1 = (ImageView) headViewTwo.findViewById(R.id.item_sale_two_iv_one);
        twoIv2 = (ImageView) headViewTwo.findViewById(R.id.item_sale_two_iv_two);
        twoIv3 = (ImageView) headViewTwo.findViewById(R.id.item_sale_two_iv_three);
        twoIv4 = (ImageView) headViewTwo.findViewById(R.id.item_sale_two_iv_four);
        twoIv5 = (ImageView) headViewTwo.findViewById(R.id.item_sale_two_iv_five);
        twoIv6 = (ImageView) headViewTwo.findViewById(R.id.item_sale_two_iv_six);
        twoIv7 = (ImageView) headViewTwo.findViewById(R.id.item_sale_two_iv_seven);
        twoIv8 = (ImageView) headViewTwo.findViewById(R.id.item_sale_two_iv_eight);

        twoTitle1 = (TextView) headViewTwo.findViewById(R.id.item_sale_two_title_one);
        twoTitle2 = (TextView) headViewTwo.findViewById(R.id.item_sale_two_title_two);
        twoTitle3 = (TextView) headViewTwo.findViewById(R.id.item_sale_two_title_three);
        twoTitle4 = (TextView) headViewTwo.findViewById(R.id.item_sale_two_title_four);
        twoTitle5 = (TextView) headViewTwo.findViewById(R.id.item_sale_two_title_five);
        twoTitle6 = (TextView) headViewTwo.findViewById(R.id.item_sale_two_title_six);
        twoTitle7 = (TextView) headViewTwo.findViewById(R.id.item_sale_two_title_seven);
        twoTitle8 = (TextView) headViewTwo.findViewById(R.id.item_sale_two_title_eight);

    }

    private void initHeadSixData() {
        sixIv1 = (ImageView) headViewSix.findViewById(R.id.item_sale_six_one_iv);
        sixIv2 = (ImageView) headViewSix.findViewById(R.id.item_sale_six_two_iv);
        sixIv3 = (ImageView) headViewSix.findViewById(R.id.item_sale_six_three_iv);
        sixIv4 = (ImageView) headViewSix.findViewById(R.id.item_sale_six_four_iv);

        sixTitle1 = (TextView) headViewSix.findViewById(R.id.item_sale_six_one_title);
        sixTitle2 = (TextView) headViewSix.findViewById(R.id.item_sale_six_two_title);
        sixTitle3 = (TextView) headViewSix.findViewById(R.id.item_sale_six_three_title);
        sixTitle4 = (TextView) headViewSix.findViewById(R.id.item_sale_six_four_title);

        sixSmallTitle1 = (TextView) headViewSix.findViewById(R.id.item_sale_six_one_small_title);
        sixSmallTitle2 = (TextView) headViewSix.findViewById(R.id.item_sale_six_two_small_title);
        sixSmallTitle3 = (TextView) headViewSix.findViewById(R.id.item_sale_six_three_small_title);
        sixSmallTitle4 = (TextView) headViewSix.findViewById(R.id.item_sale_six_four_small_title);

        sixPrice1 = (TextView) headViewSix.findViewById(R.id.item_sale_six_one_price);
        sixPrice2 = (TextView) headViewSix.findViewById(R.id.item_sale_six_two_price);
        sixPrice3 = (TextView) headViewSix.findViewById(R.id.item_sale_six_three_price);
        sixPrice4 = (TextView) headViewSix.findViewById(R.id.item_sale_six_four_price);

        sixSmallPrice1 = (TextView) headViewSix.findViewById(R.id.item_sale_six_one_small_price);
        sixSmallPrice2 = (TextView) headViewSix.findViewById(R.id.item_sale_six_two_small_price);
        sixSmallPrice3 = (TextView) headViewSix.findViewById(R.id.item_sale_six_three_small_price);
        sixSmallPrice4 = (TextView) headViewSix.findViewById(R.id.item_sale_six_four_small_price);

    }

    private void initHeadFiveData() {
        //初始化第五个布局的数据
        fiveIv1 = (ImageView) headViewFive.findViewById(R.id.item_sale_five_one_iv);
        fiveIv2 = (ImageView) headViewFive.findViewById(R.id.item_sale_five_two_iv);
        fiveTitle1 = (TextView) headViewFive.findViewById(R.id.item_sale_five_one_title);
        fiveTitle2 = (TextView) headViewFive.findViewById(R.id.item_sale_five_two_title);
        fiveSmallTitle1 = (TextView) headViewFive.findViewById(R.id.item_sale_five_one_small_title);
        fiveSmallTitle2 = (TextView) headViewFive.findViewById(R.id.item_sale_five_two_small_title);
        fivePrice1 = (TextView) headViewFive.findViewById(R.id.item_sale_five_one_price);
        fivePrice2 = (TextView) headViewFive.findViewById(R.id.item_sale_five_two_price);
        fiveSmallPrice1 = (TextView) headViewFive.findViewById(R.id.item_sale_five_one_small_price);
        fiveSmallPrice2 = (TextView) headViewFive.findViewById(R.id.item_sale_five_two_small_price);
    }

    @Override
    protected void initData() {
        //获取轮播图的数据
        VolleySinge.addRequest("http://app.api.autohome.com.cn/autov5.0.0/mobile/appadvert-a2-pm1-v5.0.1-sid2-pid340000-cid0-lat0.000000-lng0.000000.json",
                SaleFragmentImageBean.class,
                new Response.Listener<SaleFragmentImageBean>() {
                    @Override
                    public void onResponse(SaleFragmentImageBean response) {
                        saleFragmentImageBean = response;
                        initViewImage();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //获取listView显示的数据
        VolleySinge.addRequest("http://223.99.255.20/mobilenc.app.autohome.com.cn/discover_v5.8.0/mall/intelligentrecommend.ashx?a=2&pm=2&v=5.8.5&uid=0&deviceid=99000628573771&gps=38.889659,121.551063&cityid=210200&pid=210000&pageindex=1&pagesize=20&hid=",
                SaleFragmentBean.class,
                new Response.Listener<SaleFragmentBean>() {
                    @Override
                    public void onResponse(SaleFragmentBean response) {
                        saleFragmentBean = response;
                        //设置特惠热卖布局的数据
                        initSaleFiveData();
                        //设置精品的布局的数据
                        // initSaleSixData();
                        saleAdapter.setSaleBean(response);
                        listView.setAdapter(saleAdapter);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position > 5) {
                    Intent intent = new Intent();
                    intent.putExtra("url", saleFragmentBean.getResult().getGoodslist().getList().get(position - 6).getMurl());
                    intent.setClass(context, WebViewActivity.class);
                    startActivity(intent);
                }
            }
        });
        //获取活动的布局显示的数据
        VolleySinge.addRequest("http://223.99.255.20/mobilenc.app.autohome.com.cn/discover_v5.8.0/mobile/functionlist-a2-pm2-v5.8.5-pid210000-cid210200.json",
                SaleFragmentCampaignBean.class,
                new Response.Listener<SaleFragmentCampaignBean>() {
                    @Override
                    public void onResponse(SaleFragmentCampaignBean response) {
                        saleFragmentCampaignBean = response;
                        //设置汽车音频的布局的数据
                        initSaleTwoData();
                        //设置车商城的布局的数据
                        initSaleFourData();
                        //设置活动专区的布局的数据
                        initSaleThreeData();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

    }

    //设置第三个布局的数据
    private void initSaleThreeData() {
        List<ImageView> list = new ArrayList<>();
        list.add(threeIv1);
        list.add(threeIv2);
        list.add(threeIv3);
        for (int i = 0; i < 3; i++) {
            Picasso.with(context).load(saleFragmentCampaignBean.getResult().getImageads().getImageadsinfo().get(i).getImgurl()).placeholder(R.mipmap.fild).error(R.mipmap.fild).into(list.get(i));
            list.get(i).setOnClickListener(this);
        }
    }

    private void initSaleFourData() {
        List<ImageView> list = new ArrayList<>();
        list.add(fourIv1);
        list.add(fourIv2);
        list.add(fourIv3);
        list.add(fourIv4);
        list.add(fourIv5);
        list.add(fourIv6);
        for (int i = 0; i <= 5; i++) {
            Picasso.with(context).load(saleFragmentCampaignBean.getResult().getBusinesslist().get(i).getIconurl()).placeholder(R.mipmap.fild).error(R.mipmap.fild).into(list.get(i));
            list.get(i).setOnClickListener(this);
        }
    }

    private void initSaleTwoData() {
        List<ImageView> list = new ArrayList<>();
        list.add(twoIv1);
        list.add(twoIv2);
        list.add(twoIv3);
        list.add(twoIv4);
        list.add(twoIv5);
        list.add(twoIv6);
        list.add(twoIv7);
        list.add(twoIv8);
        List<TextView> titleList = new ArrayList<>();
        titleList.add(twoTitle1);
        titleList.add(twoTitle2);
        titleList.add(twoTitle3);
        titleList.add(twoTitle4);
        titleList.add(twoTitle5);
        titleList.add(twoTitle6);
        titleList.add(twoTitle7);
        titleList.add(twoTitle8);
        for (int i = 0; i < 8; i++) {
            Picasso.with(context).load(saleFragmentCampaignBean.getResult().getFunctionlist().get(i).getIconurl()).placeholder(R.mipmap.fild).error(R.mipmap.fild).into(list.get(i));
            titleList.get(i).setText(saleFragmentCampaignBean.getResult().getFunctionlist().get(i).getTitle());
        }
    }

    private void initSaleFiveData() {
        Picasso.with(context).load(saleFragmentBean.getResult().getModulelist().get(0).getList().get(0).getLogo()).placeholder(R.mipmap.fild).error(R.mipmap.fild).into(fiveIv1);
        Picasso.with(context).load(saleFragmentBean.getResult().getModulelist().get(0).getList().get(1).getLogo()).placeholder(R.mipmap.fild).error(R.mipmap.fild).into(fiveIv2);
        fiveTitle1.setText(saleFragmentBean.getResult().getModulelist().get(0).getList().get(0).getTitle());
        fiveTitle2.setText(saleFragmentBean.getResult().getModulelist().get(0).getList().get(1).getTitle());
        fiveSmallTitle1.setText(saleFragmentBean.getResult().getModulelist().get(0).getList().get(0).getAdinfo());
        fiveSmallTitle2.setText(saleFragmentBean.getResult().getModulelist().get(0).getList().get(1).getAdinfo());
        fivePrice1.setText(saleFragmentBean.getResult().getModulelist().get(0).getList().get(0).getPrice());
        fivePrice2.setText(saleFragmentBean.getResult().getModulelist().get(0).getList().get(1).getPrice());
        //设置删除线
        fiveSmallPrice1.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        fiveSmallPrice2.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        fiveSmallPrice1.setText(saleFragmentBean.getResult().getModulelist().get(0).getList().get(0).getFctprice());
        fiveSmallPrice2.setText(saleFragmentBean.getResult().getModulelist().get(0).getList().get(1).getFctprice());
        fiveIv1.setOnClickListener(this);
        fiveIv2.setOnClickListener(this);
    }

    public void initViewImage() {
        dotViewList = new ArrayList<ImageView>();
        list = new ArrayList<ImageView>();

        for (int i = 0; i < saleFragmentImageBean.getResult().getList().size(); i++) {
            ImageView dotView = new ImageView(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new ActionBar.LayoutParams(
                    ActionBar.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            params.leftMargin = 15;//设置小圆点的外边距
            params.rightMargin = 15;

            params.height = 20;//设置小圆点的大小
            params.width = 20;

            if (i == 0) {
                dotView.setBackgroundResource(R.mipmap.point_pressed);
            } else {

                dotView.setBackgroundResource(R.mipmap.point_unpressed);
            }
            dotLayout.addView(dotView, params);

            dotViewList.add(dotView);
            //上面是动态添加了四个小圆点
        }
        //设置发现页面的轮播图
        for (int i = 0; i < saleFragmentImageBean.getResult().getList().size(); i++) {
            String url = saleFragmentImageBean.getResult().getList().get(i).getImgurl();
            img = (ImageView) inflater.inflate(R.layout.scroll_vew_item, null);
            Picasso.with(context).load(url).placeholder(R.mipmap.fild).error(R.mipmap.fild).into(img);
            list.add(img);
        }

        ImagePaperAdapter adapter = new ImagePaperAdapter((ArrayList<ImageView>) list);
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

    @Override
    public void onLoad() {
        srff.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        Toast.makeText(context, "下拉刷新", Toast.LENGTH_SHORT).show();
        srff.setRefreshing(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.item_sale_three_iv1:
                jumpDetails(saleFragmentCampaignBean.getResult().getImageads().getImageadsinfo().get(0).getUrl());
                break;
            case R.id.item_sale_three_iv2:
                jumpDetails(saleFragmentCampaignBean.getResult().getImageads().getImageadsinfo().get(1).getUrl());
                break;
            case R.id.item_sale_three_iv3:
                jumpDetails(saleFragmentCampaignBean.getResult().getImageads().getImageadsinfo().get(2).getUrl());
                break;
            case R.id.item_sale_four_iv1:
                jumpDetails(saleFragmentCampaignBean.getResult().getBusinesslist().get(0).getUrl());
                break;
            case R.id.item_sale_four_iv2:
                jumpDetails(saleFragmentCampaignBean.getResult().getBusinesslist().get(1).getUrl());
                break;
            case R.id.item_sale_four_iv3:
                jumpDetails(saleFragmentCampaignBean.getResult().getBusinesslist().get(2).getUrl());
                break;
            case R.id.item_sale_four_iv4:
                jumpDetails(saleFragmentCampaignBean.getResult().getBusinesslist().get(3).getUrl());
                break;
            case R.id.item_sale_four_iv5:
                jumpDetails(saleFragmentCampaignBean.getResult().getBusinesslist().get(4).getUrl());
                break;
            case R.id.item_sale_four_iv6:
                jumpDetails(saleFragmentCampaignBean.getResult().getBusinesslist().get(5).getUrl());
                break;
            //特惠热卖的点击事件
            case R.id.item_sale_five_one_iv:
                jumpDetails(saleFragmentBean.getResult().getModulelist().get(0).getList().get(0).getMurl());
                break;
            case R.id.item_sale_five_two_iv:
                jumpDetails(saleFragmentBean.getResult().getModulelist().get(0).getList().get(1).getMurl());
                break;
            //品牌精品的点击事件
            case R.id.item_sale_six_one_iv:
                jumpDetails(saleFragmentBean.getResult().getModulelist().get(1).getList().get(0).getMurl());
                break;
            case R.id.item_sale_six_two_iv:
                jumpDetails(saleFragmentBean.getResult().getModulelist().get(1).getList().get(1).getMurl());
                break;
            case R.id.item_sale_six_three_iv:
                jumpDetails(saleFragmentBean.getResult().getModulelist().get(1).getList().get(2).getMurl());
                break;
            case R.id.item_sale_six_four_iv:
                jumpDetails(saleFragmentBean.getResult().getModulelist().get(1).getList().get(3).getMurl());
                break;
            case R.id.item_sale_three_more:
                jumpDetails(saleFragmentCampaignBean.getResult().getImageads().getMoreactivitysurl());
                break;

        }
    }

    //跳转详情的方法
    private void jumpDetails(String url) {
        Intent intent = new Intent();
        intent.putExtra("url", url);
        intent.setClass(context, SaleWebViewActivity.class);
        startActivity(intent);
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
        public void onPageSelected(final int pos) {
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
            ImageView imageView = list.get(pos);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra("url", saleFragmentImageBean.getResult().getList().get(pos).getUrl());
                    intent.setClass(context, SaleWebViewActivity.class);
                    startActivity(intent);
                }
            });
        }
    }
}


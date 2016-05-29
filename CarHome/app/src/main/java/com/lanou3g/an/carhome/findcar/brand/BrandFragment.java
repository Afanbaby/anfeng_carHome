package com.lanou3g.an.carhome.findcar.brand;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.lanou3g.an.carhome.R;
import com.lanou3g.an.carhome.beas.BaseFragment;
import com.lanou3g.an.carhome.utils.SideBar;
import com.lanou3g.an.carhome.utils.VolleySinge;

import de.greenrobot.event.EventBus;
import it.sephiroth.android.library.picasso.Picasso;


/**
 * Created by anfeng on 16/5/9.
 */
public class BrandFragment extends BaseFragment implements View.OnClickListener, SideBar.OnTouchingLetterChangedListener {

    private ExpandableListView listView;
    private BrandAdpater brandAdpater;
    private ImageView publicIv, HondaIv, ToyotaIv, modernIv, audiIv, geelyIv, buickIv, fordIv, harvardIv, nissanIv, byd_yuanIv, Lincoln_MkZ_iv, Xin_move_iv;
    private TextView sidebarTv, publicTv, HondaTv, ToyotaTv, modernTv, audiTv, geelyTv, buickTv, fordTv, harvardTv, nissanTv, byd_yuanTv, Lincoln_MkZ_tv, Xin_move_tv;
    private View headView;
    private BrandHeadTwoBean brandHeadTwoBean;
    private SideBar sideBar;

    @Override
    public int setLayout() {
        return R.layout.fragment_brand;
    }

    @Override
    protected void initView() {
        listView = bindView(R.id.fragment_brand_lv);
        //取消父item的指示器
        listView.setGroupIndicator(null);
        brandAdpater = new BrandAdpater(context);
        headView = LayoutInflater.from(context).inflate(R.layout.item_brand_head, null);
        initHeadData();
        listView.addHeaderView(headView);
        sideBar = bindView(R.id.fragment_brand_sidebar);
        sidebarTv = bindView(R.id.fragment_brand_tv);
    }

    private void initHeadData() {
        publicIv = (ImageView) headView.findViewById(R.id.publicIv);
        publicTv = (TextView) headView.findViewById(R.id.publicTv);
        HondaIv = (ImageView) headView.findViewById(R.id.HondaIv);
        HondaTv = (TextView) headView.findViewById(R.id.HondaTV);
        ToyotaIv = (ImageView) headView.findViewById(R.id.ToyotaIv);
        ToyotaTv = (TextView) headView.findViewById(R.id.ToyotaTv);
        modernIv = (ImageView) headView.findViewById(R.id.modernIv);
        modernTv = (TextView) headView.findViewById(R.id.modernTv);
        audiIv = (ImageView) headView.findViewById(R.id.audiIv);
        audiTv = (TextView) headView.findViewById(R.id.audiTv);
        geelyIv = (ImageView) headView.findViewById(R.id.geelyIv);
        geelyTv = (TextView) headView.findViewById(R.id.geelyTv);
        buickIv = (ImageView) headView.findViewById(R.id.buickIv);
        buickTv = (TextView) headView.findViewById(R.id.buickTv);
        fordIv = (ImageView) headView.findViewById(R.id.fordIv);
        fordTv = (TextView) headView.findViewById(R.id.fordTv);
        harvardIv = (ImageView) headView.findViewById(R.id.harvardIv);
        harvardTv = (TextView) headView.findViewById(R.id.harvardTv);
        nissanIv = (ImageView) headView.findViewById(R.id.nissanIv);
        nissanTv = (TextView) headView.findViewById(R.id.nissanTv);

        byd_yuanIv = (ImageView) headView.findViewById(R.id.byd_yuanIv);
        byd_yuanTv = (TextView) headView.findViewById(R.id.byd_yuanTv);
        Lincoln_MkZ_iv = (ImageView) headView.findViewById(R.id.Lincoln_MkZ_iv);
        Lincoln_MkZ_tv = (TextView) headView.findViewById(R.id.Lincoln_MkZ_tv);
        Xin_move_iv = (ImageView) headView.findViewById(R.id.Xin_move_iv);
        Xin_move_tv = (TextView) headView.findViewById(R.id.Xin_move_tv);
        buickIv.setOnClickListener(this);
        Lincoln_MkZ_iv.setOnClickListener(this);
        Xin_move_iv.setOnClickListener(this);

    }

    @Override
    protected void initData() {
        VolleySinge.addRequest("http://app.api.autohome.com.cn/autov5.0.0/news/brandsfastnews-pm1-ts0.json", BrandBean.class,
                new Response.Listener<BrandBean>() {
                    @Override
                    public void onResponse(BrandBean response) {
                        brandAdpater.setBrandBean(response);
                        //让首次加载的时候,让父item展开
                        if (response.getResult().getBrandlist().size() > 0) {
                            for (int i = 0; i < response.getResult().getBrandlist().size(); i++) {
                                listView.expandGroup(i);
                            }
                        }
                        //取消父item的点击收缩
                        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                            @Override
                            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                                return true;
                            }
                        });

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        VolleySinge.addRequest("http://223.99.255.20/cars.app.autohome.com.cn/dealer_v5.7.0/dealer/hotbrands-pm2.json", BrandHeadOneBean.class,
                new Response.Listener<BrandHeadOneBean>() {
                    @Override
                    public void onResponse(BrandHeadOneBean response) {

                        getIntentImage(publicIv, response.getResult().getList().get(0).getImg());
                        getIntentImage(HondaIv, response.getResult().getList().get(1).getImg());
                        getIntentImage(ToyotaIv, response.getResult().getList().get(2).getImg());
                        getIntentImage(modernIv, response.getResult().getList().get(3).getImg());
                        getIntentImage(audiIv, response.getResult().getList().get(4).getImg());
                        getIntentImage(geelyIv, response.getResult().getList().get(5).getImg());
                        getIntentImage(buickIv, response.getResult().getList().get(6).getImg());
                        getIntentImage(fordIv, response.getResult().getList().get(7).getImg());
                        getIntentImage(harvardIv, response.getResult().getList().get(8).getImg());
                        getIntentImage(nissanIv, response.getResult().getList().get(9).getImg());
                        publicTv.setText(response.getResult().getList().get(0).getName());
                        HondaTv.setText(response.getResult().getList().get(1).getName());
                        ToyotaTv.setText(response.getResult().getList().get(2).getName());
                        modernTv.setText(response.getResult().getList().get(3).getName());
                        audiTv.setText(response.getResult().getList().get(4).getName());
                        geelyTv.setText(response.getResult().getList().get(5).getName());
                        buickTv.setText(response.getResult().getList().get(6).getName());
                        fordTv.setText(response.getResult().getList().get(7).getName());
                        harvardTv.setText(response.getResult().getList().get(8).getName());
                        nissanTv.setText(response.getResult().getList().get(9).getName());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        VolleySinge.addRequest("http://223.99.255.20/adnewnc.app.autohome.com.cn/autov5.7.0/ad/infoad.ashx?version=5.8.5&platform=2&appid=2&networkid=0&adtype=1&provinceid=210000&cityid=0&lng=121.551079&lat=38.889656&gps_city=210200&pageid=04704225-c34a-425c-8e4b-f8781eaf19dd&isretry=1&deviceid=99000628573771",
                BrandHeadTwoBean.class,
                new Response.Listener<BrandHeadTwoBean>() {
                    @Override
                    public void onResponse(BrandHeadTwoBean response) {
                        brandHeadTwoBean = response;
                        if (response.getResult().getList().size() > 0) {
                            getIntentImage(byd_yuanIv, response.getResult().getList().get(0).getImg());
                            getIntentImage(Lincoln_MkZ_iv, response.getResult().getList().get(1).getImg());
                            getIntentImage(Xin_move_iv, response.getResult().getList().get(2).getImg());
                            byd_yuanTv.setText(response.getResult().getList().get(0).getSeriesname());
                            Lincoln_MkZ_tv.setText(response.getResult().getList().get(1).getSeriesname());
                            Xin_move_tv.setText(response.getResult().getList().get(2).getSeriesname());
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        listView.setAdapter(brandAdpater);
        sideBar.setOnTouchingLetterChangedListener(this);
    }

    private void getIntentImage(ImageView view, String url) {
        Picasso.with(context).load(url).placeholder(R.mipmap.fild).error(R.mipmap.fild).into(view);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.byd_yuanIv:
                EventBus.getDefault().post("aaaaa");
                Log.d("qq", "*************");
                break;
            case R.id.Lincoln_MkZ_iv:
                //EventBus.getDefault().post(brandHeadTwoBean.getResult().getList().get(1).getJumpurl());
                break;
            case R.id.Xin_move_iv:
                // EventBus.getDefault().post(brandHeadTwoBean.getResult().getList().get(2).getJumpurl());
                break;
        }
    }


    @Override
    public void onTouchingLetterChanged(String s) {
        //该字母首次出现的位置
        sideBar.setTextView(sidebarTv);
        int position = brandAdpater.getPosition(s.charAt(0));
        if (position != -1) {
            listView.setSelectedGroup(position);
        }
    }
}

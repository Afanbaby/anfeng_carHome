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
 * 找车中的品牌
 */
public class BrandFragment extends BaseFragment implements SideBar.OnTouchingLetterChangedListener {

    private ExpandableListView listView;
    private BrandAdpater brandAdpater;
    private ImageView publicIv, HondaIv, ToyotaIv, modernIv, audiIv, geelyIv, buickIv, fordIv, harvardIv, nissanIv;
    private TextView sidebarTv, publicTv, HondaTv, ToyotaTv, modernTv, audiTv, geelyTv, buickTv, fordTv, harvardTv, nissanTv;
    private View headView;
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
        //添加头布局
        headView = LayoutInflater.from(context).inflate(R.layout.item_brand_head, null);
        initHeadData();
        listView.addHeaderView(headView);
        sideBar = bindView(R.id.fragment_brand_sidebar);
        sidebarTv = bindView(R.id.fragment_brand_tv);
    }

    //初始化头布局中的数据
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
        listView.setAdapter(brandAdpater);
        sideBar.setOnTouchingLetterChangedListener(this);
    }

    private void getIntentImage(ImageView view, String url) {
        Picasso.with(context).load(url).placeholder(R.mipmap.fild).error(R.mipmap.fild).into(view);
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

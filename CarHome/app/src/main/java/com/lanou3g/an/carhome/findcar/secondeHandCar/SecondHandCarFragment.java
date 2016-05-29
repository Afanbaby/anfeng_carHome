package com.lanou3g.an.carhome.findcar.secondeHandCar;

import android.widget.ListView;

import com.lanou3g.an.carhome.R;
import com.lanou3g.an.carhome.beas.BaseFragment;

/**
 * Created by anfeng on 16/5/9.
 */
public class SecondHandCarFragment extends BaseFragment {

    private ListView listView;

    @Override
    public int setLayout() {
        return R.layout.fragment_secondhandcar;
    }

    @Override
    protected void initView() {
        listView = bindView(R.id.secodhandcar_lv);
    }

    @Override
    protected void initData() {

    }
}

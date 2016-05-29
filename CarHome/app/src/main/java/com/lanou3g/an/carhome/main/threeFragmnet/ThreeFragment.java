package com.lanou3g.an.carhome.main.threeFragmnet;

import android.widget.ListView;

import com.lanou3g.an.carhome.R;
import com.lanou3g.an.carhome.beas.BaseFragment;
import com.lanou3g.an.carhome.findcar.brand.BrandFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anfeng on 16/5/25.
 */
public class ThreeFragment extends BaseFragment {
    private List<String> list;
    private ListView listView;
    private String[] array;

    @Override
    public int setLayout() {
        return R.layout.fragment_three;
    }

    @Override
    protected void initView() {
        listView = bindView(R.id.fragment_three_lv);
    }

    @Override
    protected void initData() {

        list = new ArrayList<>();
        array = getResources().getStringArray(R.array.data_location);
        for (int i = 0; i <= array.length - 1; i++) {
            list.add(array[i]);
        }

    }
}

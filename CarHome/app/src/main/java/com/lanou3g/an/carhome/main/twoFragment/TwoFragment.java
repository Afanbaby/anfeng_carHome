package com.lanou3g.an.carhome.main.twoFragment;

import android.graphics.Color;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lanou3g.an.carhome.R;
import com.lanou3g.an.carhome.beas.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anfeng on 16/5/25.
 */
public class TwoFragment extends BaseFragment {
    private ListView listView;
    private String[] array;
    private List<String> list;
    private TwoFragmentAdapter twoFragmentAdapter;

    @Override
    public int setLayout() {
        return R.layout.fragment_two;
    }

    @Override
    protected void initView() {
        listView = bindView(R.id.fragment_two_lv);
        twoFragmentAdapter = new TwoFragmentAdapter(context);
    }


    @Override
    protected void initData() {

        //listview的点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TwoFragmentAdapter.ViewHolder viewHolder = (TwoFragmentAdapter.ViewHolder) view.getTag();
                viewHolder.typeImage.setVisibility(View.VISIBLE);
                viewHolder.typeName.setTextColor(Color.BLUE);
            }
        });
        array = getResources().getStringArray(R.array.data_video);
        list = new ArrayList<>();
        for (int i = 0; i <= array.length - 1; i++) {
            list.add(array[i]);
        }
        twoFragmentAdapter.setList(list);
        listView.setAdapter(twoFragmentAdapter);
    }


}






package com.lanou3g.an.carhome.forum.commonForum;

import android.view.View;
import android.widget.LinearLayout;

import com.lanou3g.an.carhome.R;
import com.lanou3g.an.carhome.beas.BaseFragment;
import com.lanou3g.an.carhome.eventBus.DataBean;

import de.greenrobot.event.EventBus;

/**
 * Created by anfeng on 16/5/9.
 * 常用论坛
 */
public class CommonForumFragment extends BaseFragment implements View.OnClickListener {
    private LinearLayout layoutOne, layoutTwo, layoutThree;

    @Override
    public int setLayout() {
        return R.layout.fragment_commonforum;
    }

    @Override
    protected void initView() {
        layoutOne = bindView(R.id.fragment_common_forum_layout_one);
        layoutTwo = bindView(R.id.fragment_common_forum_layout_two);
        layoutThree = bindView(R.id.fragment_common_forum_layout_three);
        layoutOne.setOnClickListener(this);
        layoutTwo.setOnClickListener(this);
        layoutThree.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_common_forum_layout_one:
                EventBus.getDefault().post(new DataBean(1));
                break;
            case R.id.fragment_common_forum_layout_two:
                EventBus.getDefault().post(new DataBean(2));
                break;
            case R.id.fragment_common_forum_layout_three:
                EventBus.getDefault().post(new DataBean(3));
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(context);
    }
}

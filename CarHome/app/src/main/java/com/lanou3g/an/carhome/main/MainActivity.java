package com.lanou3g.an.carhome.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.transition.Transition;
import android.view.View;
import android.widget.RadioButton;

import com.lanou3g.an.carhome.R;
import com.lanou3g.an.carhome.article.ArticleFragment;
import com.lanou3g.an.carhome.beas.BaseActivity;
import com.lanou3g.an.carhome.findcar.FindcarFragment;
import com.lanou3g.an.carhome.forum.ForumFragment;
import com.lanou3g.an.carhome.my.MyFragment;
import com.lanou3g.an.carhome.sale.SaleFragment;

import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private RadioButton articleRb, forumRb, findcarRb, saleRb, myRb;

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
        //将首页作为进入的界面
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.replace_view, new ArticleFragment());
        transaction.commit();
    }

    @Override
    protected void initData() {
        articleRb.setOnClickListener(this);
        forumRb.setOnClickListener(this);
        findcarRb.setOnClickListener(this);
        saleRb.setOnClickListener(this);
        myRb.setOnClickListener(this);
    }


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
        }
        transaction.commit();
    }
}

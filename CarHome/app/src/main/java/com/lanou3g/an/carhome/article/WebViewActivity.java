package com.lanou3g.an.carhome.article;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lanou3g.an.carhome.Collection;
import com.lanou3g.an.carhome.CollectionDao;
import com.lanou3g.an.carhome.R;
import com.lanou3g.an.carhome.articleNestingFragment.newest.NewestBean;
import com.lanou3g.an.carhome.beas.BaseActivity;

import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;

/**
 * Created by anfeng on 16/5/24.
 */
public class WebViewActivity extends BaseActivity implements View.OnClickListener {

    private WebView webView;
    private TextView returnTv;
    private ImageView collectionIv, shareIv;
    Boolean flag = false;
    private CollectionDao collectionDao;//数据库的操作对象
    private NewestBean.ResultBean.NewslistBean newslistBean;
    private String url;
    private Collection myCollection;


    @Override
    protected int getLayout() {
        return R.layout.activity_web_view;
    }

    @Override
    protected void initView() {
        webView = bindView(R.id.activity_web_view);
        returnTv = bindView(R.id.activity_web_return_tv);
        collectionIv = bindView(R.id.activity_web_collection_iv);
        shareIv = bindView(R.id.activity_web_share_iv);
        returnTv.setOnClickListener(this);
        collectionIv.setOnClickListener(this);

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        myCollection = (Collection) intent.getSerializableExtra("Collection");

        //是否使用其他浏览器来加载WebView
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webView.loadUrl(url);

        //使用单例的创建collectionDao,保证在所有的额类当中只有一个collectionDao
        collectionDao = GreendaoSingle.getInstance().getCollectionDao();
//        //删除所有,为了防止id冲突,事先将数据库里的所有数据内容清空
//        collectionDao.deleteAll();
        //遍历数据库中的数据,查找id,当传进来的id与数据库中id向对应,就证明存储过
        List<Collection> collectionList = collectionDao.queryBuilder().list();

        for (Collection collection : collectionList) {
            if (collection.getId().toString().equals(myCollection.getId().toString())) {
                collectionIv.setImageResource(R.mipmap.collectionshi);
                flag = true;
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //点击返回按钮,finish
            case R.id.activity_web_return_tv:
                finish();
                break;
            //点击收藏,换一张图片,并且加入到数据库中
            case R.id.activity_web_collection_iv:

                if (flag == false) {
                    collectionIv.setImageResource(R.mipmap.collectionshi);
                    Toast.makeText(WebViewActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
                    collectionDao.insert(myCollection);
                    flag = true;
                } else {
                    collectionIv.setImageResource(R.mipmap.collectionkong);
                    collectionDao.deleteByKey(myCollection.getId());
                    flag = false;
                }
                break;
        }
    }
}

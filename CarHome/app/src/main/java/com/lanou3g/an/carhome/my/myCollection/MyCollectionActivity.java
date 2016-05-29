package com.lanou3g.an.carhome.my.myCollection;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.lanou3g.an.carhome.Collection;
import com.lanou3g.an.carhome.CollectionDao;
import com.lanou3g.an.carhome.R;
import com.lanou3g.an.carhome.article.GreendaoSingle;
import com.lanou3g.an.carhome.article.WebViewActivity;
import com.lanou3g.an.carhome.beas.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anfeng on 16/5/27.
 */
public class MyCollectionActivity extends BaseActivity implements View.OnClickListener {

    private ListView listView;
    private MyCollectionAdapter myCollectionAdapter;
    private CollectionDao collectionDao;
    private TextView returnTv;

    @Override
    protected int getLayout() {
        return R.layout.activity_my_collection;
    }

    @Override
    protected void initView() {
        listView = bindView(R.id.activity_my_collection_lv);
        myCollectionAdapter = new MyCollectionAdapter(this);
        returnTv = bindView(R.id.activity_my_return_tv);
        returnTv.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        //从数据库中取出将收藏中内容,并添加到Adapter中,
        collectionDao = GreendaoSingle.getInstance().getCollectionDao();
        final List<Collection> collectionList = collectionDao.queryBuilder().list();
        myCollectionAdapter.setCollectionList(collectionList);
        listView.setAdapter(myCollectionAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String cUrl = collectionList.get(position).getUrl();
                Long cId = collectionList.get(position).getId();
                String cTitle = collectionList.get(position).getTitle();
                String cImageUrl = collectionList.get(position).getImageUrl();
                Collection collection = new Collection();
                collection.setId(cId);
                collection.setTitle(cTitle);
                collection.setUrl(cUrl);
                collection.setImageUrl(cImageUrl);

                Intent intent = new Intent();
                intent.putExtra("url", cUrl);
                intent.putExtra("Collection", collection);
                intent.setClass(getApplication(), WebViewActivity.class);
                startActivity(intent);

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_my_return_tv:
                finish();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("11", "onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        CollectionDao collectionDao2 = GreendaoSingle.getInstance().getCollectionDao();
        List<Collection> collectionList2 = collectionDao2.queryBuilder().list();
        Log.d("MyCollectionActivity", "collectionDao2:" + collectionList2.size());
        myCollectionAdapter.setCollectionList(collectionList2);
        listView.setAdapter(myCollectionAdapter);
    }


}

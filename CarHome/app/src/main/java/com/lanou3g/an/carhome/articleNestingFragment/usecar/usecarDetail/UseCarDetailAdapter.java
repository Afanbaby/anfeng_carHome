package com.lanou3g.an.carhome.articleNestingFragment.usecar.usecarDetail;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.lanou3g.an.carhome.R;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by anfeng on 16/5/19.
 */
public class UseCarDetailAdapter extends PagerAdapter {

    private Context context;
    private UseCarDetailBean useCarDetailBean;

    public void setUseCarDetailBean(UseCarDetailBean useCarDetailBean) {
        this.useCarDetailBean = useCarDetailBean;
        notifyDataSetChanged();
    }

    public UseCarDetailAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return useCarDetailBean != null ? useCarDetailBean.getResult().getImages().size() : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View imageView = LayoutInflater.from(context).inflate(R.layout.item_activity_usecar_detail_image, null);
        ImageView image = (ImageView) imageView.findViewById(R.id.item_activity_usecar_detail_iv);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //当点击图片的时候,发送一条广播
                Intent intent = new Intent("com.lanou3g.an.carhome.VIEWHIDE");
                context.sendBroadcast(intent);
                Toast.makeText(context, "点击了图片", Toast.LENGTH_SHORT).show();
            }
        });
        getIntentdata(image, useCarDetailBean.getResult().getImages().get(position).getImgurl());
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //里面不用写东西
    }

    private ImageView getIntentdata(ImageView view, String url) {
        Picasso.with(context).load(url).placeholder(R.mipmap.fild).error(R.mipmap.fild).into(view);
        return view;
    }
}

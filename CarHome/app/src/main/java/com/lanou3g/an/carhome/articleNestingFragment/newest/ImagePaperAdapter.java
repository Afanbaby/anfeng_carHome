package com.lanou3g.an.carhome.articleNestingFragment.newest;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import com.lanou3g.an.carhome.BuildConfig;

import java.util.ArrayList;
import java.util.List;

public class ImagePaperAdapter extends PagerAdapter {

	private ArrayList<ImageView>list;
	private List<String> stringList;

	public void setStringList(List<String> stringList) {
		this.stringList = stringList;
		notifyDataSetChanged();
	}


	public ImagePaperAdapter(ArrayList<ImageView> list) {
		// TODO Auto-generated constructor stub
		this.list = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}
	
    @Override    
    public void destroyItem(ViewGroup container, int position,    
            Object object) {    
        //Warning：不要在这里调用removeView  
    }
	
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// TODO Auto-generated method stub
		String url = stringList.get(position);
		if (BuildConfig.DEBUG) Log.d("ImagePaperAdapter", url);
		ImageView view = list.get(position) ;
		ViewParent vp =  view.getParent();
		if(vp != null){
			ViewGroup parent = (ViewGroup)vp;
			parent.removeView(view);
		}
		//上面这些语句必须加上，如果不加的话，就会产生则当用户滑到第四个的时候就会触发这个异常
		//原因是我们试图把一个有父组件的View添加到另一个组件。
		((ViewPager)container).addView(list.get(position));
		
		System.out.println("------------");
		return list.get(position);
	}

}
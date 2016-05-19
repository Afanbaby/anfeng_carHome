package com.lanou3g.an.carhome.articleNestingFragment.bulletin.bulletinDetail;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou3g.an.carhome.R;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by anfeng on 16/5/18.
 */
public class BulletinDetailAdapter extends BaseAdapter {

    private Context context;
    private BulletinDetailBean bulletinDetailBean;

    public BulletinDetailAdapter(Context context) {
        this.context = context;
    }

    public void setBulletinDetailBean(BulletinDetailBean bulletinDetailBean) {
        this.bulletinDetailBean = bulletinDetailBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return bulletinDetailBean != null ? bulletinDetailBean.getResult().getMessagelist().size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder myViewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_bulletin_detail_body, parent, false);
            myViewHolder = new MyViewHolder(convertView);
            convertView.setTag(myViewHolder);
        } else {
            myViewHolder = (MyViewHolder) convertView.getTag();
        }
        Log.d("BulletinDetailAdapter", "bulletinDetailBean:" + bulletinDetailBean);
        //设置数据
        getIntentData(myViewHolder.authorIv, bulletinDetailBean.getResult().getNewsdata().getHeadimg());
        getIntentData(myViewHolder.propleIv, bulletinDetailBean.getResult().getMessagelist().get(position).getCommentlist().get(position).getHeadimg());
        getIntentData(myViewHolder.badyIv, bulletinDetailBean.getResult().getMessagelist().get(position).getAttachments().get(position).getPicurl());
        myViewHolder.authorName.setText(bulletinDetailBean.getResult().getMessagelist().get(position).getAuthorname());
        myViewHolder.authorTime.setText(bulletinDetailBean.getResult().getMessagelist().get(position).getPublishtime());
        myViewHolder.authorBody.setText(bulletinDetailBean.getResult().getMessagelist().get(position).getContent());
        myViewHolder.authorZambia.setText(bulletinDetailBean.getResult().getMessagelist().get(position).getUpcount() + "赞");
        myViewHolder.authorComment.setText(bulletinDetailBean.getResult().getMessagelist().get(position).getReplycount() + "评论");
        myViewHolder.commentName.setText(bulletinDetailBean.getResult().getMessagelist().get(position).getCommentlist().get(position).getUsername());
        myViewHolder.commentBody.setText(bulletinDetailBean.getResult().getMessagelist().get(position).getCommentlist().get(position).getContent());
        return convertView;
    }


    class MyViewHolder {
        private ImageView authorIv, badyIv, propleIv;
        private TextView authorName, authorTime, authorBody,
                authorZambia, authorComment,
                commentName, commentBody;

        public MyViewHolder(View itemView) {
            authorIv = (ImageView) itemView.findViewById(R.id.item_bulletin_detail_body_iv);
            badyIv = (ImageView) itemView.findViewById(R.id.item_bulletin_detail_body_bodyIv);
            propleIv = (ImageView) itemView.findViewById(R.id.item_bulletin_detail_body_comment_iv);

            authorName = (TextView) itemView.findViewById(R.id.item_bulletin_detail_body_auother);
            authorTime = (TextView) itemView.findViewById(R.id.item_bulletin_detail_body_time);
            authorBody = (TextView) itemView.findViewById(R.id.item_bulletin_detail_body_body);
            authorZambia = (TextView) itemView.findViewById(R.id.item_bulletin_detail_body_Zambia);

            authorComment = (TextView) itemView.findViewById(R.id.item_bulletin_detail_body_comment);
            commentName = (TextView) itemView.findViewById(R.id.item_bulletin_detail_body_comment_name);
            commentBody = (TextView) itemView.findViewById(R.id.item_bulletin_detail_body_comment_bady);
        }
    }

    public void getIntentData(ImageView view, String url) {
        Picasso.with(context).load(url).placeholder(R.mipmap.fild).error(R.mipmap.fild).into(view);
    }

}

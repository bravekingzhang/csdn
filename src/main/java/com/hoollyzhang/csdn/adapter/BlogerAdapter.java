package com.hoollyzhang.csdn.adapter;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hoollyzhang.csdn.R;
import com.hoollyzhang.csdn.mode.Bloger;
import com.hoollyzhang.csdn.view.ArticalActivity;

import java.util.List;

import in.srain.cube.image.CubeImageView;
import in.srain.cube.image.ImageLoader;
import in.srain.cube.image.ImageLoaderFactory;

/**
 * Created by brzhang on 15/5/17.
 */
public class BlogerAdapter extends BaseAdapter {
    private Activity     mActivity;
    private List<Bloger> blogerList;
    ImageLoader imageLoader;

    public BlogerAdapter(Activity mActivity) {
        this.mActivity = mActivity;
        imageLoader = ImageLoaderFactory.create(mActivity);
    }

    @Override
    public int getCount() {
        if (blogerList != null)
            return blogerList.size();
        else return 0;
    }

    @Override
    public Object getItem(int position) {
        if (blogerList != null)
            return blogerList.get(position);
        else return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mActivity.getLayoutInflater().inflate(R.layout.csdn_acivity_main_bloger_item, null);
            viewHolder = new ViewHolder();
            viewHolder.blogerHeader = (CubeImageView) convertView.findViewById(R.id.csdn_main_activity_bloger_iv_header);
            viewHolder.blogerName = (TextView) convertView.findViewById(R.id.csdn_main_activity_bloger_tv_name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        addOnclickListener(viewHolder,position);
        fillData(viewHolder,position);
        return  convertView;
    }

    private void addOnclickListener(ViewHolder viewHolder, final int position) {
        viewHolder.blogerHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpToBlogerPaper(position);
            }
        });
    }

    private void jumpToBlogerPaper(int position) {
        Bloger bloger = blogerList.get(position);
        Intent intent = new Intent(mActivity,ArticalActivity.class);
        intent.putExtra(ArticalActivity.BLOG_SRC,bloger.getBlogerSrc());
        mActivity.startActivity(intent);
    }

    private void fillData(ViewHolder viewHolder,int position) {
        Bloger bloger = blogerList.get(position);
        if (bloger == null){
            return;
        }
        Log.e("hoolly","iamge src is "+bloger.getBlogerPic());
        viewHolder.blogerHeader.loadImage(imageLoader,bloger.getBlogerPic());
        viewHolder.blogerName.setText(bloger.getBlogerName());

    }

    public List<Bloger> getBlogerList() {
        return blogerList;
    }

    public void setBlogerList(List<Bloger> blogerList) {
        this.blogerList = blogerList;
    }

    class ViewHolder {
        CubeImageView blogerHeader;
        TextView      blogerName;
    }
}

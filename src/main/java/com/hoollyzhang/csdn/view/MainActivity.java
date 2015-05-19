package com.hoollyzhang.csdn.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;
import com.hoollyzhang.csdn.CsdnApplication;
import com.hoollyzhang.csdn.R;
import com.hoollyzhang.csdn.adapter.BlogerAdapter;
import com.hoollyzhang.csdn.listener.BlogerListener;
import com.hoollyzhang.csdn.manger.BlogerManger;
import com.hoollyzhang.csdn.mode.Bloger;
import com.hoollyzhang.csdn.mode.manger.DbBlogerManger;
import com.hoollyzhang.csdn.response.BlogerResponse;
import com.zhy.utils.SPUtils;

import in.srain.cube.views.GridViewWithHeaderAndFooter;


public class MainActivity extends SherlockActivity implements BlogerListener{

    DbBlogerManger blogerManger;

    public static final int ADD_BLOG_INTENT = 1;

    private GridViewWithHeaderAndFooter gridViewBloger;
    private TextView                    headerTV;
    private BlogerAdapter               blogerAdapter;
    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        blogerManger = new DbBlogerManger(this);

        setupView();
        initData();
    }

    @Override
    public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu) {
        super.onCreateOptionsMenu(menu);
        getSupportMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {//得到被点击的item的itemId
            case R.id.action_settings:
                Intent intent = new Intent(MainActivity.this, AddBlogerActivty.class);
                startActivityForResult(intent, ADD_BLOG_INTENT);
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initData() {
        initBlogers();
        blogerAdapter = new BlogerAdapter(this);
        gridViewBloger.setAdapter(blogerAdapter);
        //到异步任务中去做
        listBloger();

    }

    private void initBlogers() {
        boolean isBlogInit = (boolean) SPUtils.get(this, CsdnApplication.BlogerIsInit, false);
        if (isBlogInit) {
            return;
        }
        Bloger bloger = new Bloger();
        bloger.setBlogerSrc("lmj623565791");
        bloger.setBlogerName("鸿神");
        bloger.setBlogerPic("http://avatar.csdn.net/F/F/5/1_lmj623565791.jpg");
        blogerManger.insertBloger(bloger);
        bloger = new Bloger();
        bloger.setBlogerSrc("guolin_blog");
        bloger.setBlogerName("郭神");
        bloger.setBlogerPic("http://avatar.csdn.net/8/B/B/1_sinyu890807.jpg");
        blogerManger.insertBloger(bloger);
        bloger = new Bloger();
        bloger.setBlogerSrc("liuhe688");
        bloger.setBlogerName("liuhe688");
        bloger.setBlogerPic("http://avatar.csdn.net/8/5/D/1_liuhe688.jpg");
        blogerManger.insertBloger(bloger);
        bloger = new Bloger();
        bloger.setBlogerSrc("bravekingzhang");
        bloger.setBlogerName("brzhang");
        bloger.setBlogerPic("http://avatar.csdn.net/7/4/F/1_bravekingzhang.jpg");
        blogerManger.insertBloger(bloger);
        Log.e(TAG, "insert data finished");
        SPUtils.put(this, CsdnApplication.BlogerIsInit, true);
    }

    private void setupView() {
        gridViewBloger = (GridViewWithHeaderAndFooter) findViewById(R.id.csdn_main_activity_bloger_gv);
        headerTV = (TextView) findViewById(R.id.csdn_main_activity_header_tv);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_BLOG_INTENT && resultCode == Activity.RESULT_OK) {
            listBloger();
        }
    }

    private void listBloger() {
        BlogerManger.getBlogerList(this, this);
    }

    @Override
    public void onProgress(Integer progres) {

    }

    @Override
    public void onAsyncCallBack(BlogerResponse result) {
        if (result == null){
            return;
        }
        blogerAdapter.setBlogerList(result.getBlogers());
        blogerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onInsert() {

    }
}

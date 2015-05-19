package com.hoollyzhang.csdn.view;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hoollyzhang.csdn.R;
import com.hoollyzhang.csdn.listener.BlogerListener;
import com.hoollyzhang.csdn.manger.BlogerManger;
import com.hoollyzhang.csdn.mode.Bloger;
import com.hoollyzhang.csdn.response.BlogerResponse;
import com.zhy.utils.T;

import info.hoang8f.widget.FButton;

/**
 * Created by brzhang on 15/5/19.
 */
public class AddBlogerActivty extends Activity implements View.OnClickListener,BlogerListener {

    FButton  addBlog;
    FButton  cancelAdd;
    TextView blogName;
    TextView blogPic;
    TextView blogSrc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.csdn_add_bloger_activity);
        setupView();
        initData();
    }

    private void setupView() {
        addBlog = (FButton) findViewById(R.id.csdn_add_bloger_activity_add_bt);
        addBlog.setOnClickListener(this);
        cancelAdd = (FButton) findViewById(R.id.csdn_add_bloger_activity_add_bt);
        cancelAdd.setOnClickListener(this);
        blogName = (TextView) findViewById(R.id.csdn_add_bloger_activity_blogname);
        blogPic = (TextView) findViewById(R.id.csdn_add_bloger_activity_headersrc);
        blogSrc = (TextView) findViewById(R.id.csdn_add_bloger_activity_blogsrc);
    }

    private void initData() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.csdn_add_bloger_activity_add_bt:
                doAddBlog();
                break;
            case R.id.csdn_add_bloger_activity_cancel_bt:
                canAdd();
                break;
            default:
                break;
        }
    }

    private void doAddBlog() {
        if (TextUtils.isEmpty(blogName.getText().toString()) ||
                TextUtils.isEmpty(blogPic.getText().toString()) ||
                TextUtils.isEmpty(blogSrc.getText().toString())) {
            T.show(this, "信息没有填写完整", Toast.LENGTH_LONG);
            return;
        }
        Bloger bloger = new Bloger();
        bloger.setBlogerName(blogName.getText().toString());
        bloger.setBlogerPic(blogPic.getText().toString());
        bloger.setBlogerSrc(blogSrc.getText().toString());
        BlogerManger.insertBloger(this, this, bloger);
    }

    private void canAdd() {
        setResult(Activity.RESULT_CANCELED);
        this.finish();
    }

    @Override
    public void onProgress(Integer progres) {

    }

    @Override
    public void onAsyncCallBack(BlogerResponse result) {

    }

    @Override
    public void onInsert() {
        T.show(this,"添加博主成功",Toast.LENGTH_LONG);
        setResult(Activity.RESULT_OK);
        this.finish();
    }
}

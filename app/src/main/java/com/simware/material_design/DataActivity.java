package com.simware.material_design;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by Administrator on 2017/6/7.
 */

public class DataActivity extends AppCompatActivity{
    public static final String DATA_NAME="data_name";
    public static final String DATA_IMAGE_ID="data_image_id";
    public static final String DATA="data";
    private ImageView imageView;
    private TextView textView;
    private ImageData data;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        initView();
        initData();

    }
    private void initData(){
        Intent intent=getIntent();
        data=intent.getParcelableExtra(DATA);
        Glide.with(this).load(data.getImageId()).into(imageView);
        textView.setText(setText(data.getName()));
        CollapsingToolbarLayout toolbarLayout=(CollapsingToolbarLayout)findViewById(R.id.collapsing);
        toolbarLayout.setTitle(data.getName());
    }
    private void initView() {
        imageView = (ImageView) findViewById(R.id.image);
        textView = (TextView)findViewById(R.id.text);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    private String setText(String s){
        StringBuilder builder=new StringBuilder();
        for (int i=0;i<500;i++){
            builder.append(s);
        }
        return builder.toString();
    }
}

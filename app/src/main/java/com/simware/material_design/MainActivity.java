package com.simware.material_design;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private ImageView image;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ImageAdapter adapter;
    private ImageData[] imageDatas={new ImageData("data 1",R.drawable.dish),
                                    new ImageData("data 2",R.drawable.alpha_ceskykrumlov),
                                    new ImageData("data 3",R.drawable.royal_palace_square),
                                    new ImageData("data 4",R.drawable.seine),
                                    new ImageData("data 5",R.drawable.warsaw_historic_area)};
    private List<ImageData> datas=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=(Toolbar)findViewById(R.id.tool);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        }
        initData();
        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
                snackBarShow(item.getTitle().toString());
                break;

        }
        return true;
    }

    private void init(){
        swipeRefreshLayout=(SwipeRefreshLayout)findViewById(R.id.refresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();
            }
        });
        drawerLayout=(DrawerLayout)findViewById(R.id.drawer);
        recyclerView=(RecyclerView)findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        adapter=new ImageAdapter(datas);
        recyclerView.setAdapter(adapter);
        FloatingActionButton floatingActionButton=(FloatingActionButton)findViewById(R.id.floating);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.scrollToPosition(0);
            }
        });

        final NavigationView navigationView=(NavigationView)findViewById(R.id.nav);
        navigationView.setCheckedItem(R.id.i1);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                navigationView.setCheckedItem(item.getItemId());
                switch (item.getItemId()){
                    case R.id.i1:
                        Glide.with(getApplicationContext()).load(R.drawable.dish).into(image);
                        break;
                    case R.id.i2:
                        Glide.with(getApplicationContext()).load(R.drawable.alpha_ceskykrumlov).into(image);
                        break;
                    case R.id.i3:
                        Glide.with(getApplicationContext()).load(R.drawable.royal_palace_square).into(image);
                        break;
                    case R.id.i4:
                        Glide.with(getApplicationContext()).load(R.drawable.warsaw_historic_area).into(image);
                        break;
                    case R.id.i5:
                        Glide.with(getApplicationContext()).load(R.drawable.seine).into(image);
                        break;
                }
                return false;
            }
        });
        image=(ImageView)navigationView.getHeaderView(0).findViewById(R.id.image);
        Glide.with(getApplicationContext()).load(R.drawable.dish).into(image);
    }
    private void snackBarShow(String s){
        Snackbar.make(recyclerView,s,Snackbar.LENGTH_SHORT)
                .setAction("test", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(),"onClick",Toast.LENGTH_SHORT).show();
                    }
                })
                .show();

    }
    private void initData(){
        for (int i=0;i<50;i++){
            Random random=new Random();
            datas.add(imageDatas[random.nextInt(imageDatas.length)]);
        }
    }
    private void refreshData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        datas.clear();
                        initData();
                        adapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                        recyclerView.scrollToPosition(0);
                    }
                });
            }
        }).start();
    }
}

package com.imooc.studyapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.imooc.studyapplication.adapter.BasePageAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * ViewPagerActivity 用于实验各种ViewPager控件效果的Activity
 * <p>
 * author:张冠之<br>
 * time: 2016/12/13 16:21 <br>
 * e-mail: zhangguanzhi@csii.com.cn <br>
 * </p>
 */

public class ViewPagerActivity extends AppCompatActivity {
    @Bind(R.id.btn1)
    Button btn1;
    @Bind(R.id.btn2)
    Button btn2;
    @Bind(R.id.vp)
    ViewPager vp;
    @Bind(R.id.tabLayout)
    TabLayout tabLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        ImageView imageView1 = new ImageView(this);
        imageView1.setImageResource(R.mipmap.shasha);
        ImageView imageView2 = new ImageView(this);
        imageView2.setImageResource(R.mipmap.ic_launcher);
        ImageView imageView3 = new ImageView(this);
        imageView3.setImageResource(R.mipmap.cod6);
        ImageView imageView4 = new ImageView(this);
        imageView4.setImageResource(R.mipmap.img_credit_pay);
        List<ImageView> list = new ArrayList<>();
        list.add(imageView1);
        list.add(imageView2);
        list.add(imageView3);
        list.add(imageView4);
        vp.setAdapter(new BasePageAdapter<ImageView>(list));
    }

    @OnClick({R.id.btn1, R.id.btn2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                if (tabLayout.getVisibility() == View.GONE) {
                    tabLayout.setVisibility(View.VISIBLE);
                    tabLayout.setupWithViewPager(vp);
                } else {
                    tabLayout.setVisibility(View.GONE);
                }
                break;
            case R.id.btn2:

                break;
        }
    }
}

package com.imooc.studyapplication;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import com.imooc.studyapplication.Util.ToastUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * TabLayoutActivity TabLayout测试
 * <p>
 * author:张冠之<br>
 * time: 2016/12/08 13:56 <br>
 * e-mail: zhangguanzhi@csii.com.cn <br>
 * </p>
 */

public class TabLayoutActivity extends AppCompatActivity {
    @Bind(R.id.tabLayout)
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablayout);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        tabLayout.addTab(tabLayout.newTab().setText("Tab 1").setIcon(R.mipmap.ic_launcher));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 2").setIcon(R.mipmap.ic_launcher));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 3").setIcon(R.mipmap.ic_launcher));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 3").setIcon(R.mipmap.ic_launcher));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 3").setIcon(R.mipmap.ic_launcher));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 3").setIcon(R.mipmap.ic_launcher));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 3").setIcon(R.mipmap.ic_launcher));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                ToastUtil.showToast(TabLayoutActivity.this,"Tab"+tab.getPosition()+"被选中了!");
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                ToastUtil.showToast(TabLayoutActivity.this,"Tab"+tab.getPosition()+"再次被选中了!");
            }
        });
    }
}

package com.imooc.studyapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.imooc.studyapplication.Util.ToastUtil;
import com.imooc.studyapplication.adapter.BaseFragmentPageAdapter;
import com.imooc.studyapplication.adapter.BasePageAdapter;
import com.imooc.studyapplication.fragment.BaseFragment;
import com.imooc.studyapplication.fragment.Test1Fragment;
import com.imooc.studyapplication.fragment.Test2Fragment;
import com.imooc.studyapplication.fragment.Test3Fragment;
import com.imooc.studyapplication.fragment.Test4Fragment;

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

    Test1Fragment mTest1Fragment = new Test1Fragment();
    Test2Fragment mTest2Fragment = new Test2Fragment();
    Test3Fragment mTest3Fragment = new Test3Fragment();
    Test4Fragment mTest4Fragment = new Test4Fragment();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        ButterKnife.bind(this);
        init();
//        initFragment();
    }

    /**
     * 动态添加Fragment的方法
     * @param position 转换fragment
     */
    private void initFragment(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(mTest1Fragment,"test1");
        transaction.add(mTest2Fragment,"test2");
        transaction.add(mTest3Fragment,"test3");
        transaction.add(mTest4Fragment,"test4");
        transaction.commit();
        SwitchTo(position);
    }

    /**
     * 切换用方法
     * @param position
     */
    private void SwitchTo(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //切换特效
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        switch (position) {
            case 0:
                transaction.hide(mTest1Fragment);
                transaction.hide(mTest2Fragment);
                transaction.hide(mTest3Fragment);
                transaction.show(mTest4Fragment);
                transaction.commitAllowingStateLoss();
                break;
            case 1:
                transaction.hide(mTest1Fragment);
                transaction.hide(mTest2Fragment);
                transaction.hide(mTest3Fragment);
                transaction.show(mTest4Fragment);
                transaction.commitAllowingStateLoss();
                break;
            case 2:
                transaction.hide(mTest1Fragment);
                transaction.hide(mTest2Fragment);
                transaction.hide(mTest3Fragment);
                transaction.show(mTest4Fragment);
                transaction.commitAllowingStateLoss();
                break;
            case 3:
                transaction.hide(mTest1Fragment);
                transaction.hide(mTest2Fragment);
                transaction.hide(mTest3Fragment);
                transaction.show(mTest4Fragment);
                transaction.commitAllowingStateLoss();
                break;
            default:
                break;
        }
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
            //绑定TabLayout到ViewPager
            case R.id.btn1:
                if (tabLayout.getVisibility() == View.GONE) {
                    tabLayout.setVisibility(View.VISIBLE);
                    tabLayout.setupWithViewPager(vp);
                } else {
                    tabLayout.setVisibility(View.GONE);
                }
                break;
            case R.id.btn2:
                //绑定Fragment和ViewPager
                List<BaseFragment> fragmentList = new ArrayList<>();
                fragmentList.add(mTest1Fragment);
                fragmentList.add(mTest2Fragment);
                fragmentList.add(mTest3Fragment);
                fragmentList.add(mTest4Fragment);
                String[] title = {"test1", "test2", "test3", "test4"};
                vp.setAdapter(new BaseFragmentPageAdapter<>(getSupportFragmentManager(), fragmentList, title));
                vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        ToastUtil.showToast(ViewPagerActivity.this,"test"+(position+1));
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
                break;
        }
    }
}

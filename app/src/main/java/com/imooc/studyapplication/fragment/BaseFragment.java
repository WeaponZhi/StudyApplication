package com.imooc.studyapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * BaseFragment Fragment基类
 * <p>
 * author:张冠之<br>
 * time: 2016/12/22 14:52 <br>
 * e-mail: zhangguanzhi@csii.com.cn <br>
 * </p>
 */

public abstract class BaseFragment extends Fragment {
    protected View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null)
            rootView = initViewPre(inflater, container);
        ButterKnife.bind(this, rootView);
        initView();
        return rootView;
    }

    /**
     * 绑定视图
     *
     * @param inflater
     * @param container
     * @return
     */
    protected abstract View initViewPre(LayoutInflater inflater, ViewGroup container);
    /**
     * 初始化方法
     */
    protected abstract void initView();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}

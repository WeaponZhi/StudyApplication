
package com.imooc.studyapplication.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.imooc.studyapplication.R;

/**
 * Test1Fragment 测试 Fragment 2
 * <p>
 * author:张冠之<br>
 * time: 2016/12/22 14:59 <br>
 * e-mail: zhangguanzhi@csii.com.cn <br>
 * </p>
 */

public class Test2Fragment extends BaseFragment{
    @Override
    protected void initView() {

    }

    @Override
    protected View initViewPre(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_test2,null);
    }
}

package com.imooc.studyapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
/**
 * MainActivity 学习测试专用项目
 * <p>
 * author:张冠之<br>
 * time: 2016/11/12 17:02 <br>
 * e-mail: zhangguanzhi@csii.com.cn <br>
 * </p>
 */

/**
 * ************************************************************************
 * **                              _oo0oo_                               **
 * **                             o8888888o                              **
 * **                             88" . "88                              **
 * **                             (| -_- |)                              **
 * **                             0\  =  /0                              **
 * **                           ___/'---'\___                            **
 * **                        .' \\\|     |// '.                          **
 * **       保佑代码无敌      / \\\|||  :  |||// \\        弘扬战神之类       **
 * **                      / _ ||||| -:- |||||- \\                       **
 * **                      | |  \\\\  -  /// |   |                       **
 * **                      | \_|  ''\---/''  |_/ |                       **
 * **                      \  .-\__  '-'  __/-.  /                       **
 * **                    ___'. .'  /--.--\  '. .'___                     **
 * **                 ."" '<  '.___\_<|>_/___.' >'  "".                  **
 * **                | | : '-  \'.;'\ _ /';.'/ - ' : | |                 **
 * **                \  \ '_.   \_ __\ /__ _/   .-' /  /                 **
 * **            ====='-.____'.___ \_____/___.-'____.-'=====             **
 * **                              '=---='                               **
 * ************************************************************************
 * **                        佛祖保佑      镇类之宝                         **
 * ************************************************************************
 */
public class MainActivity extends AppCompatActivity {

    @Bind(R.id.btn1)
    Button btn1;
    @Bind(R.id.btn2)
    Button btn2;
    @Bind(R.id.btn3)
    Button btn3;
    @Bind(R.id.btn4)
    Button btn4;
    @Bind(R.id.btn5)
    Button btn5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,R.id.btn5})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.btn1:
                intent.setClass(this, TextDrawableTestActivity.class);
                startActivity(intent);
                break;
            case R.id.btn2:
                intent.setClass(this, RecyclerViewDecorationTestActivity.class);
                startActivity(intent);
                break;
            case R.id.btn3:
                intent.setClass(this, TabLayoutActivity.class);
                startActivity(intent);
                break;
            case R.id.btn4:
                intent.setClass(this, ProgressBarTestActivity.class);
                startActivity(intent);
                break;
            case R.id.btn5:
                intent.setClass(this, ViewPagerActivity.class);
                startActivity(intent);
                break;
        }
    }
}

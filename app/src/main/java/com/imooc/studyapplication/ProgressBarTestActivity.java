package com.imooc.studyapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * ProgressBarTestActivity 进度条测试
 * <p>
 * author:张冠之<br>
 * time: 2016/12/09 10:40 <br>
 * e-mail: zhangguanzhi@csii.com.cn <br>
 * </p>
 */

public class ProgressBarTestActivity extends AppCompatActivity {
    @Bind(R.id.pb1)
    ProgressBar pb1;//常规原型进度圈
    @Bind(R.id.pb2)
    ProgressBar pb2;//横向系统自带进度条
    @Bind(R.id.pb3)
    ProgressBar pb3;//自定义进度条
    @Bind(R.id.tv1)
    TextView tv1;
    @Bind(R.id.tv2)
    TextView tv2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar_test);
        ButterKnife.bind(this);
        init();
        initDef();
    }

    /**
     * 自定义进度条
     */
    private void initDef() {
        new Thread(){
            @Override
            public void run() {
                int i=0;
                while(i<100){
                    i++;
                    try {
                        Thread.sleep(80);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    final int j=i;
                    pb3.setProgress(i);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv2.setText(j+"%");
                        }
                    });
                }
            }
        }.start();
    }

    private void init() {
        //横向进度条
        new Thread(){
            @Override
            public void run() {
                int i=0;
                while(i<100){
                    i++;
                    try {
                        Thread.sleep(80);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    final int j=i;
                    pb2.setProgress(i);
                    //在主线程中更新UI
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv1.setText(j+"%");
                        }
                    });
                }
            }
        }.start();
    }

}

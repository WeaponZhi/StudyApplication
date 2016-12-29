package com.imooc.studyapplication;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.imooc.studyapplication.view.BezierTestView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * BezierActivity 贝塞尔曲线
 * <p>
 * author:张冠之<br>
 * time: 2016/12/27 13:45 <br>
 * e-mail: zhangguanzhi@csii.com.cn <br>
 * </p>
 */

public class BezierActivity extends AppCompatActivity {
    @Bind(R.id.btv_bezier)
    BezierTestView btvBezier;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bezier);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btv_bezier)
    public void onClick() {
        if (btvBezier.isOpen()) {
            ObjectAnimator.ofFloat(btvBezier, "topEyelidControlPointHeight", 0f, btvBezier.getBottomEyelidControlPointHeight())
                    .setDuration(1000)
                    .start();
            btvBezier.setOpen(false);
        }else {
            ObjectAnimator.ofFloat(btvBezier,"topEyelidControlPointHeight",btvBezier.getBottomEyelidControlPointHeight(),0f)
                    .setDuration(1000)
                    .start();
            btvBezier.setOpen(true);
        }
    }
}

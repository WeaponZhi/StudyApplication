package com.imooc.studyapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.imooc.studyapplication.bean.ParcelableTestBean;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * ParcelableTestActivity 测试Parcelable
 * <p>
 * author:张冠之<br>
 * time: 2016/12/25 22:14 <br>
 * e-mail: zhangguanzhi@csii.com.cn <br>
 * </p>
 */

public class ParcelableTestActivity extends AppCompatActivity {
    @Bind(R.id.tv_parcelable)
    TextView tvParcelable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcelable_test);
        ButterKnife.bind(this);
        tvParcelable = (TextView) findViewById(R.id.tv_parcelable);
        initTextView();
    }

    private void initTextView() {
        ParcelableTestBean parcelableTestBean = getIntent().
                getExtras().
                getParcelable("parcelable");
        tvParcelable.setText(parcelableTestBean.getTestId() + "\n" +
                parcelableTestBean.getTestName() + "\n" +
                parcelableTestBean.getTestNum() + "\n" +
                parcelableTestBean.getTestOrderNum() + "\n" +
                parcelableTestBean.getTestType());
    }
}

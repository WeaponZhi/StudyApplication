package com.imooc.studyapplication.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.imooc.studyapplication.Util.ToastUtil;

/**
 * TestBroadcast 普通广播和有序广播
 * <p>
 * author:张冠之<br>
 * time: 2016/12/26 15:58 <br>
 * e-mail: zhangguanzhi@csii.com.cn <br>
 * </p>
 */

public class TestBroadcast extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        ToastUtil.showToast(context,
                "接收到Broadcast,消息为："+intent.getStringExtra("msg"));
    }
}

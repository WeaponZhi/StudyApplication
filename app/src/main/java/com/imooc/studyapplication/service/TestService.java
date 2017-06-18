package com.imooc.studyapplication.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.imooc.studyapplication.Util.LogUtils;

import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * TestService 测试服务类
 * Service和Thread之间没有任何关系,Service其实是运行在主线程中的
 * 在Service的onCreate()方法中打印Log.d("MyService","MyService thread id is"+Thread.currentThread().getId());
 * 会发现和主线程的ID一致
 * 但Android的后台是指它的运行完全不依赖UI的，即使Activity被销毁，或者程序被关闭，只要进程还在，Service
 * 就可以继续运行，所以只需要在Service中创建子线程，并在该子线程中进行耗时操作，就不用担心在Activity被销毁后
 * 无法对子线程进行操作了。
 * 当Activity被销毁之后，只要之后重新与Service建立关联，就可以重新获取到原有的Service中Binder实例
 * 一个Service必须要在既没有和任何Activity关联又处理停止状态的时候才会被销毁
 *
 * 建立跨进程的Service几个关键词：
 * process=":remote"
 * AIDL：Android接口定义语言
 * <action android:name="com.xx.xx.MyAIDLService"/>
 * 隐式Intent
 * 如果需要传递数据，基本只能传递Java的基本数据类型、字符串、List或Map
 * 如果需要传递自定义的类，则需要让这个类实现Parcelable接口，并要给这个类定义一个同名的AIDL文件
 * <p>
 * author:张冠之<br>
 * time: 2016/12/24 15:13 <br>
 * e-mail: zhangguanzhi@csii.com.cn <br>
 * </p>
 */

public class TestService extends Service {
    public static final String TAG = "MyService";
    private ScheduledThreadPoolExecutor executor;
    private Future future;
    private Runnable runnable;
    private MyBinder mBinder = new MyBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    /**
     * 注意：任何一个Service在整个应用程序范围内都是通用的，即TestService不仅可以和
     * MainActivity关联，还可以和任何一个Activity关联，而且在建立关联时它们都可以获
     * 取到相同的MyBinder实例。
     */
   public class MyBinder extends Binder {
        public void startDownload() {
            //这是标准的写法
            new Thread(new Runnable() {
                @Override
                public void run() {
                   LogUtils.logd("startDownload() executed");
                }
            }).start();
        }

    }

    /**
     * 前台Service可以一直保持运行，它会一直有一个正在运行的图标在状态栏显示
     * 有点类似通知
     */
    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.logd("onCreate executed");
        executor = new ScheduledThreadPoolExecutor(1);
        LogUtils.logd("executor is "+executor.hashCode());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtils.logd("onStartCommand() executed");
        //这是标准写法
        if (future == null || future.isCancelled()) {
            runnable = new Runnable() {
                @Override
                public void run() {
                    LogUtils.logd("Timer is running");
                }
            };
            future = executor.scheduleAtFixedRate(runnable,0, 1,TimeUnit.SECONDS);
        }
        return super.onStartCommand(intent,flags,startId);

    }

    /**
     * 我们应该始终记得在onDestroy()方法里去清理那些不再使用的资源，
     * 防止在Service被销毁后还有会有一些不再使用的对象仍占用着内存
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.logd(TAG, "onDestroy() executed");
    }
}

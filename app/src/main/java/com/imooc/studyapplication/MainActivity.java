package com.imooc.studyapplication;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.imooc.studyapplication.bean.ParcelableTestBean;
import com.imooc.studyapplication.service.TestService;

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
    @Bind(R.id.start_service)
    Button btn6;
    @Bind(R.id.stop_service)
    Button btn7;
    @Bind(R.id.bind_service)
    Button btn8;
    @Bind(R.id.unbind_service)
    Button btn9;
    @Bind(R.id.btn_parcelable)
    Button btn10;
    @Bind(R.id.btn_normal_bc)
    Button btn11;
    @Bind(R.id.btn_ordered_bc)
    Button btn12;
    private TestService.MyBinder mMyBinder;
    //关联activity和service
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mMyBinder = (TestService.MyBinder) service;//向下转型得到MyBinder实例
            mMyBinder.startDownload();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.start_service, R.id.stop_service, R.id.bind_service, R.id.unbind_service, R.id.btn_parcelable,R.id.btn_normal_bc,R.id.btn_ordered_bc})
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
            case R.id.start_service:
                //启动服务
                Intent startIntent = new Intent(this, TestService.class);
                startService(startIntent);
                break;
            case R.id.stop_service:
                //停止服务
                Intent stopIntent = new Intent(this, TestService.class);
                stopService(stopIntent);
                break;
            case R.id.bind_service:
                //绑定服务
                Intent bindIntent = new Intent(this, TestService.class);
                //BIND_AUTO_CREATE代表在activity和service建立关系的时候自动调用Service的onCreate方法
                //注意：这个时候onStartCommand()方法是不会执行的
                bindService(bindIntent,mConnection,BIND_AUTO_CREATE);
                break;
            case R.id.unbind_service:
                //解绑服务
                unbindService(mConnection);
                break;
            case R.id.btn_parcelable:
                Intent intentParcelable = new Intent(this, ParcelableTestActivity.class);
                Bundle bundle = new Bundle();
                ParcelableTestBean parcelableTestBean = new ParcelableTestBean();
                parcelableTestBean.setTestType("This is TestType");
                parcelableTestBean.setTestNum("This is TestNum");
                parcelableTestBean.setTestId("This is TestId");
                parcelableTestBean.setTestName("This is TestName");
                parcelableTestBean.setTestOrderNum("This is TestOrderNum");
                bundle.putParcelable("parcelable",parcelableTestBean);
                intentParcelable.putExtras(bundle);
                startActivity(intentParcelable);
                break;
            case R.id.btn_normal_bc:
                Intent normalBroadcast = new Intent();
                normalBroadcast.setAction("com.imooc.studyapplication.broadcast.TestBroadcast");
                normalBroadcast.putExtra("msg","这是一条普通广播哦~");
                sendBroadcast(normalBroadcast);
                break;
            case R.id.btn_ordered_bc:
                Intent orderedBroadcast = new Intent();
                orderedBroadcast.setAction("com.imooc.studyapplication.broadcast.TestBroadcast");
                orderedBroadcast.putExtra("msg","这是一条有序广播哦~");
                sendOrderedBroadcast(orderedBroadcast,null);
                break;
        }
    }
}

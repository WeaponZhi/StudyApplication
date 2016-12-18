package com.imooc.studyapplication;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * RecyclerViewDecorationTestActivity RecyclerView简易添加分割线
 * <p>
 * author:张冠之<br>
 * time: 2016/11/12 16:53 <br>
 * e-mail: zhangguanzhi@csii.com.cn <br>
 * </p>
 */

public class RecyclerViewDecorationTestActivity extends AppCompatActivity{
    @Bind(R.id.rv_test)
    RecyclerView rvTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_decoration_test);
        ButterKnife.bind(this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rvTest.setLayoutManager(manager);
        rvTest.addItemDecoration(new MyItemDecoration());
        rvTest.setAdapter(new MyAdapter());
        Toast.makeText(this, String.valueOf(getViewHeight(rvTest)), Toast.LENGTH_LONG).show();
    }

    class MyAdapter extends RecyclerView.Adapter {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(RecyclerViewDecorationTestActivity.this).inflate(R.layout.item_recyclerview_test, parent, false));

        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 15;
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }

    class MyItemDecoration extends RecyclerView.ItemDecoration {
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.set(0, 0, 0, 1);
        }
    }

    public static int getViewHeight(View view) {
        view.measure(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        view.getMeasuredHeight();//高度
        view.getMeasuredWidth();//宽度
        return view.getMeasuredHeight();
    }
}
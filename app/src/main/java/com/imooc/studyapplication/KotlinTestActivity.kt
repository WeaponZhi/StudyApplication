package com.imooc.studyapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_kotlin_test.*


class KotlinTestActivity : AppCompatActivity() {

   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_test)
        btn_kotlin_1.text = "小之1"
        btn_kotlin_2.text = "小之2"
        btn_kotlin_3.text = "小之3"

   }


}

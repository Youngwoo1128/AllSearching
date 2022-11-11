package com.woojoo.allsearching.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

//어떻게 Base 하게 쓸까
abstract class BindingActivity<T: ViewDataBinding>(@LayoutRes private val contentLayoutResId: Int) :
    AppCompatActivity() {

    protected lateinit var binding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, contentLayoutResId)
    }
}
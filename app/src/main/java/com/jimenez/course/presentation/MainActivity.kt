package com.jimenez.course.presentation

import android.os.Bundle
import com.jimenez.course.R
import com.jimenez.course.presentation.core.base.BaseActivity
import com.jimenez.course.presentation.utils.extensionFuntion.presentLongToast

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presentLongToast("Hola")
    }

}
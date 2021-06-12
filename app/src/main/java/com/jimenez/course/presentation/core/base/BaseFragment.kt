package com.jimenez.course.presentation.core.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

abstract class BaseFragment: Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setUp(arguments)
    }

    protected open fun initView() = Unit

    protected open fun setUp(arguments: Bundle?) = Unit

}
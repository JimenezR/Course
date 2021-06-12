package com.jimenez.course.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jimenez.course.R
import com.jimenez.course.presentation.core.base.BaseFragment

class HomeFragment : BaseFragment() {

    override fun setUp(arguments: Bundle?) {
        super.setUp(arguments)
        arguments?.let { bundle ->
            val welcome = bundle.getString("welcome")
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

}
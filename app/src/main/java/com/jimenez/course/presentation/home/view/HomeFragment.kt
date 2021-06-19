package com.jimenez.course.presentation.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.jimenez.course.R
import com.jimenez.course.databinding.FragmentHomeBinding
import com.jimenez.course.presentation.core.base.BaseFragment
import com.jimenez.course.presentation.home.viewModel.HomeViewModel
import com.jimenez.course.presentation.utils.extensionFuntion.presentShortSnackBar

class HomeFragment : BaseFragment() {

    private var fragmentHomeBinding: FragmentHomeBinding? = null

    override fun setUp(arguments: Bundle?) {
        super.setUp(arguments)
        arguments?.let { bundle ->
            val welcome = bundle.getString("welcome")
            //fragmentHomeBinding?.root?.presentShortSnackBar(welcome.toString())
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentHomeBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_home,
                container,
                false
            )

        fragmentHomeBinding?.homeViewModel =
            ViewModelProvider(
                this
            ).get(HomeViewModel::class.java)

        return fragmentHomeBinding?.root
    }

}
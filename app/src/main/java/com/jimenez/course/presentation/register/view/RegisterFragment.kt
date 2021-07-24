package com.jimenez.course.presentation.register.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.jimenez.course.R
import com.jimenez.course.data.local.database.CourseRoomDataBase
import com.jimenez.course.data.local.entites.User
import com.jimenez.course.databinding.FragmentRegisterBinding
import com.jimenez.course.presentation.core.base.BaseFragment
import com.jimenez.course.presentation.core.callbacks.ResultCallback
import com.jimenez.course.presentation.register.viewModel.RegisterViewModel
import com.jimenez.course.presentation.register.viewModel.RegisterViewModelFactory
import com.jimenez.course.presentation.utils.extensionFuntion.presentShortSnackBar

class RegisterFragment: BaseFragment(), ResultCallback<User>  {

    private var fragmentRegisterBinding: FragmentRegisterBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentRegisterBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_register,
                container,
                false
            )

        fragmentRegisterBinding?.registerViewModel =
            ViewModelProvider(
                this, RegisterViewModelFactory(
                    this,
                    CourseRoomDataBase.getDataBase(requireContext())
                )
            ).get(RegisterViewModel::class.java)

        return fragmentRegisterBinding?.root
    }

    override fun onSuccess(type: User) {
        fragmentRegisterBinding?.root?.presentShortSnackBar("Bienvenido ${type.firstName}")
        findNavController().navigateUp()
    }

    override fun onError(message: String, type: User?) {
        fragmentRegisterBinding?.root?.presentShortSnackBar(message)
    }

}
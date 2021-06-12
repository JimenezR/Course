package com.jimenez.course.presentation.login.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.jimenez.course.R
import com.jimenez.course.databinding.FragmentLoginBinding
import com.jimenez.course.presentation.core.base.BaseFragment
import com.jimenez.course.presentation.login.viewModel.LoginViewModel


class LoginFragment : BaseFragment() {

    private var fragmentLoginBinding: FragmentLoginBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentLoginBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_login,
                container,
                false
            )

        fragmentLoginBinding?.loginViewModel =
            ViewModelProvider(
                this
            ).get(LoginViewModel::class.java)

        fragmentLoginBinding?.btnLogin?.setOnClickListener {
            val bundle = bundleOf(
                "welcome" to "Bienvenido"
            )
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment, bundle)
        }

        fragmentLoginBinding?.loginViewModel?.titleViewModel?.let { obserbale ->
            fragmentLoginBinding?.titleView?.text = obserbale.get()
        }

        return fragmentLoginBinding?.root
    }


}
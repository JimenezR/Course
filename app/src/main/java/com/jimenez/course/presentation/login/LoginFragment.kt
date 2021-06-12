package com.jimenez.course.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import com.jimenez.course.R
import com.jimenez.course.presentation.core.base.BaseFragment


class LoginFragment : BaseFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_login, container, false)

        val btnLogin = root.findViewById<MaterialButton>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val bundle = bundleOf(
                "welcome" to "Bienvenido"
            )
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment, bundle)
        }

        return root
    }


}
package com.jimenez.course.presentation.login.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.jimenez.course.R
import com.jimenez.course.data.local.database.CourseRoomDataBase
import com.jimenez.course.databinding.FragmentLoginBinding
import com.jimenez.course.presentation.core.base.BaseFragment
import com.jimenez.course.presentation.core.callbacks.ResultCallback
import com.jimenez.course.presentation.login.viewModel.LoginViewModel
import com.jimenez.course.presentation.login.viewModel.LoginViewModelFactory
import com.jimenez.course.presentation.utils.extensionFuntion.customAlertDialog
import com.jimenez.course.presentation.utils.extensionFuntion.customAlertDialogView
import com.jimenez.course.presentation.utils.extensionFuntion.presentShortSnackBar


class LoginFragment : BaseFragment(), ResultCallback<String> {

    private var fragmentLoginBinding: FragmentLoginBinding? = null
    private var email = ""

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
                this, LoginViewModelFactory(
                    this,
                    CourseRoomDataBase.getDataBase(requireContext())
                )
            ).get(LoginViewModel::class.java)

        fragmentLoginBinding?.btnLogin?.setOnClickListener {
            /** action - validate login for manual - pair */
            //getValidateLogin()
            /** action - validate login for callback */
            getValidateLoginCallBack()
        }

        fragmentLoginBinding?.infoImage?.setOnClickListener {
            viewAlertDialog()
        }

        fragmentLoginBinding?.recoverPassword?.setOnClickListener {
            customAlertDialog()
        }

        /** conection entre el viewModel - (titleViewModel) y la vista - (titleView) */

        fragmentLoginBinding?.loginViewModel?.emailMLD?.observe(
            viewLifecycleOwner, {
                email = it
            }
        )

        return fragmentLoginBinding?.root
    }

    private fun customAlertDialog() {
        activity?.customAlertDialogView(
            title = "CustomAlertDialogView",
            message = "Utilizados para crear un aviso de tipo informativo, preventivo o de un evento inesperado en nuestra aplicación. Tenemos disponibles estas alertas.",
            positive = "Aceptar",
            processPositive = this::processAlert,
            negative = "Cancelar",
            neutral = "Nada"
        )
    }

    // FIXME: 19/06/21 modificar textos 
    private fun viewAlertDialog() {
        activity?.customAlertDialog(
            title = "CustomAlertDialog",
            message = "Para recuperar tu contraseña necesitaras conocer y tener acceso a tu correo electronico",
            positive = "Aceptar",
            processPositive = this::processAlert,
            negative = "Cancelar"
        )
    }

    /** action - validate login for manual - pair */
    private fun getValidateLogin() {
        fragmentLoginBinding?.loginViewModel?.validateLoginPair()?.let { pair ->
            fragmentLoginBinding?.root?.presentShortSnackBar(pair.second)
            if (pair.first) {
                navigateToHomeFragment()
            }
        }
    }

    /** action - validate login for callback */
    private fun getValidateLoginCallBack() {
        fragmentLoginBinding?.loginViewModel?.validateLoginCallBack()
    }

    /** login success */
    override fun onSuccess(type: String) {
        fragmentLoginBinding?.root?.presentShortSnackBar(type)
        navigateToHomeFragment()
    }

    /** login error */
    override fun onError(message: String, type: String?) {
        fragmentLoginBinding?.root?.presentShortSnackBar(message)
    }

    private fun navigateToHomeFragment() {
        val bundle = bundleOf(
            "welcome" to "Bienvenido"
        )
        findNavController().navigate(R.id.action_loginFragment_to_homeFragment, bundle)
    }

    private fun processAlert() {
        fragmentLoginBinding?.root?.presentShortSnackBar("message")
    }

}
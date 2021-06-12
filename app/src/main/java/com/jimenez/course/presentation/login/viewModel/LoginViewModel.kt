package com.jimenez.course.presentation.login.viewModel

import androidx.databinding.ObservableField
import com.jimenez.course.presentation.core.base.BaseViewModel

class LoginViewModel: BaseViewModel() {

    /** Código de prueba */
    var titleViewModel = ObservableField("")
    val time = 10

    init {
        if (time < 8) {
            titleViewModel.set("Inicio de la APP")
        } else {
            titleViewModel.set("Inicio de la APP 2")
        }
    }

}
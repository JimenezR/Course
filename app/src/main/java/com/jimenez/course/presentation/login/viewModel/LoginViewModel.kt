package com.jimenez.course.presentation.login.viewModel

import androidx.lifecycle.MutableLiveData
import com.jimenez.course.presentation.core.base.BaseViewModel
import com.jimenez.course.presentation.core.callbacks.ResultCallback

class LoginViewModel(
    private val resultCallback: ResultCallback<String>
) : BaseViewModel() {

    val emailMLD = MutableLiveData<String>()
    val passwordMLD = MutableLiveData<String>()
    var mail = ""
    var password = ""

    init {
        emailMLD.observeForever {
            mail = it
        }

        passwordMLD.observeForever {
            password = it
        }
    }

    fun validateLoginPair(): Pair<Boolean, String> {
        var login = false
        var message = ""
        if (validateEmail() && validatePassword()) {
            login = true
            message = "Bienvenido: $mail"
            resultCallback.onSuccess(message)
        } else if (!validateEmail() && !validatePassword()) {
            login = false
            message = "Tu email y password son incorrectos"
            resultCallback.onError(message)
        } else if (!validateEmail()) {
            login = false
            message = "Tu email es incorrecto"
            resultCallback.onError(message)
        } else if (!validatePassword()) {
            login = false
            message = "Tu password es incorrecto"
            resultCallback.onError(message)
        }
        return Pair(login, message)
    }

    fun validateLoginCallBack() {
        if (validateEmail() && validatePassword()) {
            resultCallback.onSuccess("Bienvenido: $mail")
        } else if (!validateEmail() && !validatePassword()) {
            resultCallback.onError("Tu email y password son incorrectos")
        } else if (!validateEmail()) {
            resultCallback.onError("Tu email es incorrecto")
        } else if (!validatePassword()) {
            resultCallback.onError("Tu password es incorrecto")
        }
    }

    /** Validaciones a servidor(WS) o DB(ROOM) */

    private fun validateEmail(): Boolean = mail == "mail@mail.com"

    private fun validatePassword(): Boolean = password == "1234"

}
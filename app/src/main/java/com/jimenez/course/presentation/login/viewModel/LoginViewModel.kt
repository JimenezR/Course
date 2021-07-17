package com.jimenez.course.presentation.login.viewModel

import androidx.lifecycle.MutableLiveData
import com.jimenez.course.data.local.database.CourseRoomDataBase
import com.jimenez.course.data.local.entites.User
import com.jimenez.course.data.local.repositories.UserRepository
import com.jimenez.course.presentation.core.base.BaseViewModel
import com.jimenez.course.presentation.core.callbacks.ResultCallback
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginViewModel(
    private val resultCallback: ResultCallback<String>,
    private val courseRoomDataBase: CourseRoomDataBase
) : BaseViewModel() {

    val emailMLD = MutableLiveData<String>()
    val passwordMLD = MutableLiveData<String>()
    var mail = ""
    var password = ""

    var userRepository = UserRepository(courseRoomDataBase.userDao())

    init {

        setUser()

        emailMLD.observeForever {
            mail = it
        }

        passwordMLD.observeForever {
            password = it
        }
    }

    /** prueba */
    private fun setUser() = GlobalScope.launch {
        userRepository.insertUser(
            User(
                firstName = "Usuario Principal",
                email = "user@mail.com",
                password = "1234"
            )
        )
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

    private fun validateEmail(): Boolean = mail == ""

    private fun validatePassword(): Boolean = password == ""

}
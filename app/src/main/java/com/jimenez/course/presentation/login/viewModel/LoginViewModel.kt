package com.jimenez.course.presentation.login.viewModel

import androidx.lifecycle.MutableLiveData
import com.jimenez.course.data.local.database.CourseRoomDataBase
import com.jimenez.course.data.local.entites.User
import com.jimenez.course.data.local.repositories.UserRepository
import com.jimenez.course.presentation.core.base.BaseViewModel
import com.jimenez.course.presentation.core.callbacks.ResultCallback
import kotlinx.coroutines.*
import kotlin.math.log

class LoginViewModel(
    private val resultCallback: ResultCallback<String>,
    courseRoomDataBase: CourseRoomDataBase
) : BaseViewModel() {

    val emailMLD = MutableLiveData<String>()
    val passwordMLD = MutableLiveData<String>()
    val navigateToRegisterMLD = MutableLiveData(false)
    var mail = ""
    var password = ""

    var userRepository = UserRepository(courseRoomDataBase.userDao())

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
        /** validación en duro */
        /*if (validateEmail() && validatePassword()) {
            resultCallback.onSuccess("Bienvenido: $mail")
        } else if (!validateEmail() && !validatePassword()) {
            resultCallback.onError("Tu email y password son incorrectos")
        } else if (!validateEmail()) {
            resultCallback.onError("Tu email es incorrecto")
        } else if (!validatePassword()) {
            resultCallback.onError("Tu password es incorrecto")
        }*/
        getLogin()
    }

    /** Validación a la DB */
    private fun getLogin() {
        job = CoroutineScope(Dispatchers.IO).launch {
            val user = userRepository.getLogin(email = mail, password = password)
            withContext(Dispatchers.Main) {
                if (user != null) {
                    resultCallback.onSuccess("Bienvenido: ${user.firstName}")
                } else {
                    resultCallback.onError("Tu email y password o incorrectos")
                }
            }
        }
    }

    /** Validaciones a servidor(WS) o DB(ROOM) */
    private fun validateEmail(): Boolean {
        var login = false
        job = CoroutineScope(Dispatchers.IO).launch {
            val user = userRepository.getEmail(email = mail)
            withContext(Dispatchers.Main) {
                login = user == null
            }
        }
        return login
    }

    private fun validatePassword(): Boolean = password == ""

    fun navigateToRegister() {
        navigateToRegisterMLD.postValue(true)
    }

}
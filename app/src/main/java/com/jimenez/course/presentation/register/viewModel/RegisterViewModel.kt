package com.jimenez.course.presentation.register.viewModel

import androidx.databinding.ObservableField
import com.jimenez.course.data.local.database.CourseRoomDataBase
import com.jimenez.course.data.local.entites.User
import com.jimenez.course.data.local.repositories.UserRepository
import com.jimenez.course.presentation.core.base.BaseViewModel
import com.jimenez.course.presentation.core.callbacks.ResultCallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterViewModel(
    private val resultCallback: ResultCallback<User>,
    courseRoomDataBase: CourseRoomDataBase
): BaseViewModel() {

    val name = ObservableField("")
    val email = ObservableField("")
    val password = ObservableField("")

    var userRepository = UserRepository(courseRoomDataBase.userDao())

    fun insertUser() {
        if (validateData()){
            setUser()
        }
    }

    private fun setUser() {
        job = CoroutineScope(Dispatchers.IO).launch {
            val rows = userRepository.insertUser(getUser())
            withContext(Dispatchers.Main) {
                if (rows > 0) {
                    resultCallback.onSuccess(getUser())
                } else {
                    resultCallback.onError("No se inserto correctamente a la Base de datos")
                }
            }
        }
    }

    private fun getUser(): User {
        return User(
            firstName = name.get().toString(),
            email = email.get().toString(),
            password = password.get().toString()
        )
    }

    private fun validateData(): Boolean {
        return when {
            name.get().toString().isEmpty() -> {
                resultCallback.onError("Necesitamos saber tu nombre")
                false
            }
            email.get().toString().isEmpty() -> {
                resultCallback.onError("Necesitamos saber tu email")
                false
            }
            password.get().toString().isEmpty() -> {
                resultCallback.onError("Necesitamos una contraseña para su cuenta")
                false
            }
            else -> { true }
        }
    }

}
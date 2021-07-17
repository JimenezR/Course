package com.jimenez.course.presentation.login.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jimenez.course.data.local.database.CourseRoomDataBase
import com.jimenez.course.presentation.core.callbacks.ResultCallback

class LoginViewModelFactory(
    private val resultCallback: ResultCallback<String>,
    private val courseRoomDataBase: CourseRoomDataBase
): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return LoginViewModel(
            resultCallback,
            courseRoomDataBase
        ) as T
    }
}
package com.jimenez.course.presentation.register.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jimenez.course.data.local.database.CourseRoomDataBase
import com.jimenez.course.data.local.entites.User
import com.jimenez.course.presentation.core.callbacks.ResultCallback

class RegisterViewModelFactory(
    private val resultCallback: ResultCallback<User>,
    private val courseRoomDataBase: CourseRoomDataBase
): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return RegisterViewModel(
            resultCallback = resultCallback,
            courseRoomDataBase = courseRoomDataBase
        ) as T
    }
}
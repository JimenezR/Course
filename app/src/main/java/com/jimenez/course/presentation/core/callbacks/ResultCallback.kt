package com.jimenez.course.presentation.core.callbacks

interface ResultCallback<T> {

    fun onSuccess(type: T)
    fun onError(message: String, type: T? = null)

}
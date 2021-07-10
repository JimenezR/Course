package com.jimenez.course.presentation.core.callbacks

interface OnItemClickListener<T> {

    fun onItemClick(item: T, type: String? = null)

}
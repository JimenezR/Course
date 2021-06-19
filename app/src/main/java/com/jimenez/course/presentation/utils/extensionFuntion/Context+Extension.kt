package com.jimenez.course.presentation.utils.extensionFuntion

import android.content.Context
import android.widget.Toast

fun Context.presentLongToast(message: String) {
    Toast.makeText(this,message,Toast.LENGTH_LONG).show()
}
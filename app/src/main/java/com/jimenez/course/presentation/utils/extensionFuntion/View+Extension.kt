package com.jimenez.course.presentation.utils.extensionFuntion

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.presentShortSnackBar(message: String) {
    Snackbar.make(this,message,Snackbar.LENGTH_SHORT).show()
}

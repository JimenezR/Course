package com.jimenez.course.presentation.utils.extensionFuntion

import android.app.AlertDialog
import android.content.Context

fun Context.customAlertDialog(
    title: String = "",
    message: String = "",
    isCancelable: Boolean = false,
    positive: String = "",
    processPositive: () -> Unit? = { null },
    negative: String = "",
    processNegative: () -> Unit? = { null },
    neutral: String = "",
    processNeutral: () -> Unit? = { null },
) {
    val alertDialog = AlertDialog.Builder(this)
    alertDialog.setTitle(title)
    alertDialog.setMessage(message)
    alertDialog.setCancelable(isCancelable)
    alertDialog.setPositiveButton(positive) {_,_ ->
        processPositive()
    }
    alertDialog.setNegativeButton(negative) {_,_ ->
        processNegative()
    }
    alertDialog.setNeutralButton(neutral) {_,_ ->
        processNeutral()
    }
    alertDialog.show()
}
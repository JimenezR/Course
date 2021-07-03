package com.jimenez.course.presentation.utils.extensionFuntion

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.TextView
import com.google.android.material.button.MaterialButton
import com.jimenez.course.R

fun Context.customAlertDialogView(
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
    val alertDialog = AlertDialog.Builder(this, R.style.NormalButtonDialog)
    var inflaterDialog: AlertDialog? = null
    val inflater = LayoutInflater.from(this).inflate(R.layout.custom_alert_dialog,null)

    val titleTextView = inflater.findViewById<TextView>(R.id.titleTextView)
    val messageTextView = inflater.findViewById<TextView>(R.id.messageTextView)
    val positiveButton = inflater.findViewById<MaterialButton>(R.id.positiveButton)
    val negativeButton = inflater.findViewById<MaterialButton>(R.id.negativeButton)
    val neutralButton = inflater.findViewById<MaterialButton>(R.id.neutralButton)

    titleTextView.text = title
    messageTextView.text = message

    if (positive.isNotEmpty()) {
        positiveButton.text = positive
        positiveButton.setOnClickListener {
            processPositive()
            inflaterDialog?.dismiss()
        }
    }

    if (negative.isNotEmpty()) {
        negativeButton.text = negative
        negativeButton.setOnClickListener {
            processNegative()
            inflaterDialog?.dismiss()
        }
    }

    if (neutral.isNotEmpty()) {
        neutralButton.text = neutral
        neutralButton.setOnClickListener {
            processNeutral()
            inflaterDialog?.dismiss()
        }
    }

    alertDialog.setCancelable(isCancelable)
    alertDialog.setView(inflater)
    inflaterDialog = alertDialog.create()
    inflaterDialog.show()
}
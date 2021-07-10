package com.jimenez.course.presentation.components.buttons

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import com.jimenez.course.R


class ActionButtonRectangle @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var titleTextView: TextView? = null

    companion object {

    }

    init {

        LayoutInflater.from(getContext()).inflate(R.layout.action_button_rectangle, this, true)
        titleTextView = findViewById(R.id.titleTextView)

    }

    fun setTextTitle(text: String) {
        titleTextView?.text = text
    }

    fun setImageBackgroundTintColor(@ColorRes setImageBackgroundTintColor: Int) {
        val imageViewCard = findViewById<AppCompatImageView>(R.id.imageViewCard)
        imageViewCard.setColorFilter(ContextCompat.getColor(context, setImageBackgroundTintColor))
        titleTextView?.setTextColor(ContextCompat.getColor(context, setImageBackgroundTintColor))
    }


}
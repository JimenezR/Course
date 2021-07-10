package com.jimenez.course.presentation.home.viewModel

import androidx.lifecycle.MutableLiveData
import com.jimenez.course.R
import com.jimenez.course.presentation.core.base.BaseViewModel
import com.jimenez.course.presentation.home.model.Color

class HomeViewModel : BaseViewModel() {

    val colorList = MutableLiveData<List<Color>>()

    init {
        getColorsWS()
    }

    private fun getColorsWS() {

        /** Consumo a un ws o a una base de datos -> () */

        val colors = listOf(
            Color("rojo", R.color.red),
            Color("amarillo", R.color.amarillo),
            Color("azul", R.color.azul),
            Color("rosa", R.color.rosa),
            Color("negro", R.color.negro),
            Color("blanco", R.color.blanco),
            Color("gris", R.color.gris),
        )
        colorList.postValue(colors)
    }

}
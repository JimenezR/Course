package com.jimenez.course.presentation.home.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jimenez.course.data.local.database.CourseRoomDataBase
import com.jimenez.course.domain.interactors.MoviesPopularInteractor

class HomeViewModelFactory(
    private val moviesPopularInteractor: MoviesPopularInteractor,
    private val courseRoomDataBase: CourseRoomDataBase
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return HomeViewModel(
            moviesPopularInteractor = moviesPopularInteractor,
            courseRoomDataBase = courseRoomDataBase
        ) as T
    }
}
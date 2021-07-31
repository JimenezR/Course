package com.jimenez.course.presentation.home.viewModel

import androidx.lifecycle.MutableLiveData
import com.jimenez.course.R
import com.jimenez.course.data.local.database.CourseRoomDataBase
import com.jimenez.course.data.local.entites.Movie
import com.jimenez.course.data.local.repositories.MovieRepository
import com.jimenez.course.data.network.models.MoviesPopularResponse
import com.jimenez.course.data.utils.Configurations
import com.jimenez.course.domain.interactors.MoviesPopularInteractor
import com.jimenez.course.presentation.core.base.BaseViewModel
import com.jimenez.course.presentation.home.model.Color
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class HomeViewModel(
    private val moviesPopularInteractor: MoviesPopularInteractor,
    private val courseRoomDataBase: CourseRoomDataBase
) : BaseViewModel() {

    val colorListMLD = MutableLiveData<List<Color>>()
    private var moviesPopularResponse: Response<MoviesPopularResponse>? = null
    var movesRepository = MovieRepository(courseRoomDataBase.movieDao())
    /** contador para descarga de paginación */
    private var count = 1

    init {
        getPoppularMovies()
        //getColorsWS()
    }

    /** consumo de WS */
    private fun getPoppularMovies() {
        job = CoroutineScope(Dispatchers.IO).launch {
            moviesPopularResponse = moviesPopularInteractor.getMoviesPopular(
                apiKey = Configurations.MOVIE_API_KEY,
                page = count,
                language = "es-MX"
            )
            count++
            withContext(Dispatchers.Main) {
                /** Validamos que la pagina consultada sea menor al total de paginas posibles */
                if (count <= moviesPopularResponse?.body()?.totalPages ?: 0) {
                    /** si es menor volvemos a solicitar una nueva pagina */
                    getPoppularMovies()
                    /** insertamos lo que se ha descargado al momento */
                    insertDataDB()
                } else {
                    /** terminando de descargar todas las paginas mostramos la lista */
                    setList()
                }
            }
        }
    }

    /** insertamos datos a la DB */
    private fun insertDataDB() {
        job = CoroutineScope(Dispatchers.IO).launch {
            moviesPopularResponse?.body()?.apply {
                val listMovies = arrayListOf<Movie>()
                results?.forEach { movie ->
                    listMovies.add(
                        Movie(
                            adult = movie.adult,
                            backdropPath = movie.backdropPath,
                            genreIds = movie.genreIds,
                            id = movie.id,
                            originalLanguage = movie.originalLanguage,
                            originalTitle = movie.originalTitle,
                            overview = movie.overview,
                            popularity = movie.popularity,
                            posterPath = movie.posterPath,
                            releaseDate = movie.releaseDate,
                            title = movie.originalTitle,
                            video = movie.video,
                            voteAverage = movie.voteAverage,
                            voteCount = movie.voteCount
                        )
                    )
                }
                movesRepository.insertMovies(listMovies)
            }
        }
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
        colorListMLD.postValue(colors)
    }

    /** leemos la lista de la DB */
    private fun setList() {
        job = CoroutineScope(Dispatchers.IO).launch {
            val movies = movesRepository.getMovies()
            withContext(Dispatchers.Main) {
                val listColor = arrayListOf<Color>()
                movies?.forEach {
                    listColor.add(Color(it.title))
                }
                colorListMLD.postValue(listColor)
            }
        }

    }

}
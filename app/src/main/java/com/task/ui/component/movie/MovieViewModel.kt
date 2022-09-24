package com.task.ui.component.movie

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.task.data.DataRepositorySource
import com.task.data.Resource
import com.task.data.dto.movies.Genres
import com.task.data.dto.movies.MovieItem
import com.task.data.dto.movies.Movies
import com.task.ui.base.BaseViewModel
import com.task.utils.SingleEvent
import com.task.utils.wrapEspressoIdlingResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MovieViewModel @Inject
constructor(private val dataRepositoryRepository: DataRepositorySource) : BaseViewModel() {

    private val movieDetailsLiveDataPrivate = MutableLiveData<SingleEvent<Resource<MovieItem>>>()
    val movieDetailsLiveData: LiveData<SingleEvent<Resource<MovieItem>>> get() = movieDetailsLiveDataPrivate

    private val moviesLiveDataPrivate = MutableLiveData<SingleEvent<Resource<Movies>>>()
    val moviesLiveData: LiveData<SingleEvent<Resource<Movies>>> get() = moviesLiveDataPrivate

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val showSnackBarPrivate = MutableLiveData<SingleEvent<Any>>()
    val showSnackBar: LiveData<SingleEvent<Any>> get() = showSnackBarPrivate

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val showToastPrivate = MutableLiveData<SingleEvent<Any>>()
    val showToast: LiveData<SingleEvent<Any>> get() = showToastPrivate

    fun getGenres() {
        viewModelScope.launch {
            wrapEspressoIdlingResource {
                dataRepositoryRepository.requestGenres().collect {
                    if (it is Resource.Success)
                        it.data?.let { it1 -> getMovies(it1) }
                }
            }
        }
    }

    private fun getMovies(genres: Genres) {
        viewModelScope.launch {
            moviesLiveDataPrivate.value = SingleEvent(Resource.Loading())
            wrapEspressoIdlingResource {
                dataRepositoryRepository.requestMovies(genres).collect {
                    if (it is Resource.Success)
                        moviesLiveDataPrivate.value = SingleEvent(it)
                }

            }
        }
    }


    fun showToastMessage(errorCode: Int) {
        val error = errorManager.getError(errorCode)
        showToastPrivate.value = SingleEvent(error.description)
    }

    fun navigateToDetails() {

    }


}

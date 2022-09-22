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
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MovieViewModel @Inject
constructor(private val dataRepositoryRepository: DataRepositorySource) : BaseViewModel() {


    val movieDetailsLiveDataPrivate = MutableLiveData<Resource<MovieItem>>()
    val movieDetailsLiveData: LiveData<Resource<MovieItem>> get() = movieDetailsLiveDataPrivate

    val moviesLiveDataPrivate = MutableLiveData<Resource<Movies>>()
    val moviesLiveData: LiveData<Resource<Movies>> get() = moviesLiveDataPrivate


    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val citiesSearchFoundPrivate: MutableLiveData<Unit> = MutableLiveData()
    val citiesSearchFound: LiveData<Unit> get() = citiesSearchFoundPrivate

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val noSearchFoundPrivate: MutableLiveData<Unit> = MutableLiveData()
    val noSearchFound: LiveData<Unit> get() = noSearchFoundPrivate


    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val showSnackBarPrivate = MutableLiveData<SingleEvent<Any>>()
    val showSnackBar: LiveData<SingleEvent<Any>> get() = showSnackBarPrivate

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val showToastPrivate = MutableLiveData<SingleEvent<Any>>()
    val showToast: LiveData<SingleEvent<Any>> get() = showToastPrivate


    fun getGenres() {
        viewModelScope.launch {
            wrapEspressoIdlingResource {
                dataRepositoryRepository.requestGenres().map {
                    if (it is Resource.Success)
                        it.data?.genreList?.map { getMovies(it.name) }
                    it
                }.collect {

                }
            }
        }
    }

    fun getMovies(genre: String) {
        viewModelScope.launch {
            moviesLiveDataPrivate.value = Resource.Loading()
            wrapEspressoIdlingResource {
                dataRepositoryRepository.requestMovies(genre).collect {
                    it.data?.genre = genre
                    moviesLiveDataPrivate.value = it
                }
            }
        }
    }


    fun showToastMessage(errorCode: Int) {
        val error = errorManager.getError(errorCode)
        showToastPrivate.value = SingleEvent(error.description)
    }

    fun onEmptySearch() {
        return noSearchFoundPrivate.postValue(Unit)
    }

    fun onFoundSearch() {
        return citiesSearchFoundPrivate.postValue(Unit)
    }



}

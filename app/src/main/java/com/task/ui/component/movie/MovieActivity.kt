package com.task.ui.component.movie

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.task.data.Resource
import com.task.data.dto.movies.Movies
import com.task.data.error.SEARCH_ERROR
import com.task.databinding.HomeActivityBinding
import com.task.ui.base.BaseActivity
import com.task.ui.component.movie.adapter.GenreAdapter
import com.task.utils.*
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieActivity : BaseActivity() {
    private lateinit var binding: HomeActivityBinding

    private val movieViewModel: MovieViewModel by viewModels()
    private lateinit var genreAdapter: GenreAdapter

    var genres = ArrayList<Movies>()

    override fun initViewBinding() {
        binding = HomeActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        movieViewModel.getGenres()
        binding.rvGenres.layoutManager = LinearLayoutManager(this)
        binding.rvGenres.adapter=GenreAdapter(movieViewModel ,genres)

    }


    private fun bindMovieData(movies: Movies) {
        showDataView()
        genres.add(movies)
        binding.rvGenres.adapter?.notifyDataSetChanged()
    }


    private fun observeSnackBarMessages(event: LiveData<SingleEvent<Any>>) {
        binding.root.setupSnackbar(this, event, Snackbar.LENGTH_LONG)
    }

    private fun observeToast(event: LiveData<SingleEvent<Any>>) {
        binding.root.showToast(this, event, Snackbar.LENGTH_LONG)
    }

    private fun showSearchError() {
        movieViewModel.showToastMessage(SEARCH_ERROR)
    }

    private fun showDataView() {
        binding.pbLoading.toGone()
    }

    private fun showLoadingView() {
        binding.pbLoading.toVisible()
    }





    private fun handleMovies(status: Resource<Movies>) {
        when (status) {
            is Resource.Loading -> showLoadingView()
            is Resource.Success -> status.data?.let {
                bindMovieData(it)
            }
            is Resource.DataError -> {
                showDataView()
                status.errorCode?.let { movieViewModel.showToastMessage(it) }
            }
        }
    }


    override fun observeViewModel() {
        observe(movieViewModel.moviesLiveData, ::handleMovies)
        observeSnackBarMessages(movieViewModel.showSnackBar)
        observeToast(movieViewModel.showToast)

    }
}

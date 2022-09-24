package com.task.ui.component.movie

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.task.data.Resource
import com.task.data.dto.movies.MovieItem
import com.task.data.dto.movies.Movies
import com.task.databinding.FragmentMovieBinding
import com.task.ui.base.BaseFragment
import com.task.ui.base.listeners.MovieItemListener
import com.task.ui.component.movie.adapter.GenreAdapter
import com.task.utils.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment : BaseFragment<FragmentMovieBinding>(), MovieItemListener {
    lateinit var genreAdapter: GenreAdapter
    private val movieViewModel: MovieViewModel by viewModels()
    var genres = ArrayList<Movies>()

    override fun initViewBinding(): FragmentMovieBinding =
        FragmentMovieBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieViewModel.getGenres()

        genreAdapter = GenreAdapter(this)
        binding.rvGenres.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = genreAdapter
        }


    }

    private fun bindMovieData(movies: Movies) {
        showDataView()
        if (!genres.contains(movies))
            genres.add(movies)
        genreAdapter.submitList(genres)
    }

    private fun observeSnackBarMessages(event: LiveData<SingleEvent<Any>>) {
        binding.root.setupSnackbar(this, event, Snackbar.LENGTH_LONG)
    }

    private fun observeToast(event: LiveData<SingleEvent<Any>>) {
        binding.root.showToast(this, event, Snackbar.LENGTH_LONG)
    }


    private fun showDataView() {
        binding.pbLoading.toGone()
    }

    private fun showLoadingView() {
        binding.pbLoading.toVisible()
    }


    private fun handleMovies(status: SingleEvent<Resource<Movies>>) {
        status.getContentIfNotHandled()?.let {
            when (it) {
                is Resource.Loading -> showLoadingView()
                is Resource.Success -> it.data?.let {
                    bindMovieData(it)
                }
                is Resource.DataError -> {
                    showDataView()
                    it.errorCode?.let { movieViewModel.showToastMessage(it) }
                }
            }
        }

    }

    override fun observeViewModel() {
        observeEvent(movieViewModel.moviesLiveData, ::handleMovies)
        observeSnackBarMessages(movieViewModel.showSnackBar)
        observeToast(movieViewModel.showToast)

    }

    override fun onMovieSelected(movie: MovieItem) {
        val actions = MovieFragmentDirections.navigateToDetails(movie)
        Navigation.findNavController(binding.root).navigate(actions)

    }


}
package com.task.ui.component.details

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.task.IMAGE_URL
import com.task.databinding.FragmentMovieDetailsBinding
import com.task.ui.base.BaseFragment
import com.task.utils.loadImage

class MovieDetailsFragment : BaseFragment<FragmentMovieDetailsBinding>() {

    private val args :MovieDetailsFragmentArgs by navArgs()

    override fun initViewBinding() = FragmentMovieDetailsBinding.inflate(layoutInflater)

    override fun observeViewModel() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movieItem =args.movieArg
        binding.ivPoster.loadImage(IMAGE_URL+movieItem.posterPath)
        binding.tvTitle.text=movieItem.name
        binding.tvOverview.text=movieItem.overview
        binding.rbVote.rating=movieItem.vote
    }
}
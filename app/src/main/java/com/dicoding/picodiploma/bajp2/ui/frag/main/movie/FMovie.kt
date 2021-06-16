package com.dicoding.picodiploma.bajp2.ui.frag.main.movie

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.picodiploma.bajp2.VMFactory
import com.dicoding.picodiploma.bajp2.data.source.local.entity.MovieEntity
import com.dicoding.picodiploma.bajp2.databinding.FragmentFMovieBinding
import com.dicoding.picodiploma.bajp2.ov.Resource
import com.dicoding.picodiploma.bajp2.ov.Status
import com.dicoding.picodiploma.bajp2.ui.detail.DetailActivity
import com.dicoding.picodiploma.bajp2.ui.frag.ContentAdapter
import com.dicoding.picodiploma.bajp2.ui.frag.ContentCallback
import com.dicoding.picodiploma.bajp2.ui.frag.ContentVM
import com.dicoding.picodiploma.bajp2.ui.frag.main.FragmentVM

class FMovie : Fragment(), MovieAdapter.OnItemClickCallback {

    companion object {
        const val TYPE_MOVIE = "type_movie"
    }

    private lateinit var binding: FragmentFMovieBinding
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            showProgressBar(true)

            val factory = VMFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[FragmentVM::class.java]
            val movie = viewModel.getMovies()
            movieAdapter = MovieAdapter()

            movie.observe(viewLifecycleOwner, movieObserver)

            with(binding.rvMovie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = movieAdapter
            }
        }
    }

    private val movieObserver = Observer<Resource<PagedList<MovieEntity>>> { movies ->
        if (movies != null) {
            when (movies.status) {
                Status.LOADING -> showProgressBar(true)
                Status.SUCCESS -> {
                    showProgressBar(false)
                    movieAdapter.submitList(movies.data)
                    movieAdapter.setOnItemClickCallback(this)
                    movieAdapter.notifyDataSetChanged()
                }
                Status.ERROR -> {
                    showProgressBar(false)
                    Toast.makeText(context, "Ada yang salah", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun showProgressBar(state: Boolean) {
        binding.progressBar.isVisible = state
    }

    override fun onItemClicked(id: String) {
        startActivity(
            Intent(context, DetailActivity::class.java)
                .putExtra(DetailActivity.EXTRA_DATA, id)
                .putExtra(DetailActivity.EXTRA_TYPE, TYPE_MOVIE)
        )
    }
}
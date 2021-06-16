package com.dicoding.picodiploma.bajp2.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.picodiploma.bajp2.R
import com.dicoding.picodiploma.bajp2.VMFactory
import com.dicoding.picodiploma.bajp2.data.source.local.entity.MovieEntity
import com.dicoding.picodiploma.bajp2.data.source.local.entity.TvShowEntity
import com.dicoding.picodiploma.bajp2.databinding.ActivityDetailBinding
import com.dicoding.picodiploma.bajp2.etc.ApiInfo.IMAGE_URL
import com.dicoding.picodiploma.bajp2.ov.Status
import com.dicoding.picodiploma.bajp2.ui.frag.main.movie.FMovie.Companion.TYPE_MOVIE
import com.dicoding.picodiploma.bajp2.ui.frag.main.tv.FTv.Companion.TYPE_TVSHOW

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_DATA = "extra_data"
        const val EXTRA_TYPE = "extra_type"
    }

    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: DetailVM

    private var type: String? = null
    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        showProgressBar(true)

        val factory = VMFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailVM::class.java]

        val id = intent.getStringExtra(EXTRA_DATA)
        type = intent.getStringExtra(EXTRA_TYPE)

        if (id != null && type != null){
            viewModel.setFilm(id, type.toString())
            setupState()
            if (type == TYPE_MOVIE) {
                viewModel.getDataDetailMovie().observe(this, { detail ->
                    when (detail.status) {
                        Status.LOADING -> showProgressBar(true)
                        Status.SUCCESS -> {
                            if (detail.data != null) {
                                showProgressBar(false)
                                populateDataDetailMovie(detail.data)
                            }
                        }
                        Status.ERROR -> {
                            showProgressBar(false)
                            Toast.makeText(applicationContext, "Ada yang salah", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                })
            }else if (type == TYPE_TVSHOW){
                viewModel.getDataDetailTvShow().observe(this, { detail ->
                    when(detail.status) {
                        Status.LOADING -> showProgressBar(true)
                        Status.SUCCESS -> {
                            if (detail.data != null){
                                showProgressBar(false)
                                populateDataDetailTvShow(detail.data)
                            }
                        }
                        Status.ERROR -> {
                            showProgressBar(false)
                            Toast.makeText(applicationContext, "Ada yang salah", Toast.LENGTH_SHORT).show()
                        }
                    }
                })
            }
        }

    }

    private fun setupState() {
        if (type == TYPE_MOVIE){
            viewModel.getDataDetailMovie().observe(this, { data ->
                when(data.status) {
                    Status.LOADING -> showProgressBar(true)
                    Status.SUCCESS -> {
                        if (data.data != null) {
                            showProgressBar(false)
                            val state = data.data.favorite
                            setFavoriteState(state)
                        }
                    }
                    Status.ERROR -> {
                        showProgressBar(false)
                        Toast.makeText(applicationContext, "Ada yang salah", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }else if (type == TYPE_TVSHOW){
            viewModel.getDataDetailTvShow().observe(this, { data ->
                when(data.status) {
                    Status.LOADING -> showProgressBar(true)
                    Status.SUCCESS -> {
                        if (data.data != null) {
                            showProgressBar(false)
                            val state = data.data.favorite
                            setFavoriteState(state)
                        }
                    }
                    Status.ERROR -> {
                        showProgressBar(false)
                        Toast.makeText(applicationContext, "Ada yang salah", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }

    }

    private fun showProgressBar(state: Boolean) {
        binding.progressBar.isVisible = state
    }

    private fun populateDataDetailMovie(detail: MovieEntity){
        val genre = detail.genre.replace("[", "").replace("]", "")
        val duration = resources.getString(R.string.minutes, detail.duration)

        with(binding){
            tvTitle.text = detail.title
            tvRelease.text = detail.release
            tvDuration.text = duration
            tvGenre.text = genre
            tvRated.text = detail.status
            tvRating.text = detail.rating.toString()
            tvOverview.text = detail.overview

            Glide.with(this@DetailActivity)
                .load(IMAGE_URL + detail.poster)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                .into(imgPoster)
        }

        showProgressBar(false)

    }

    private fun populateDataDetailTvShow(detail: TvShowEntity){
        val genre = detail.genre.replace("[", "").replace("]", "")
        val duration = resources.getString(R.string.minutes, detail.duration)

        with(binding){
            tvTitle.text = detail.title
            tvRelease.text = detail.release
            tvDuration.text = duration
            tvGenre.text = genre
            tvRated.text = detail.status
            tvRating.text = detail.rating.toString()
            tvOverview.text = detail.overview

            Glide.with(this@DetailActivity)
                .load(IMAGE_URL + detail.poster)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                .into(imgPoster)
        }

        showProgressBar(false)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu

        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (type == TYPE_MOVIE){
            if (item.itemId == R.id.action_favorite) {
                viewModel.setFavoriteMovie()
                return true
            }
        }else if (type == TYPE_TVSHOW){
            if (item.itemId == R.id.action_favorite) {
                viewModel.setFavoriteTvShow()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setFavoriteState(state: Boolean) {
        if (menu == null) return
        val menuItem = menu?.findItem(R.id.action_favorite)
        if (state) {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite)
        } else {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_border)
        }
    }
}
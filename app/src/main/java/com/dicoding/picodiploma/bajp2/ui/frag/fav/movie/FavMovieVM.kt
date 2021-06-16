package com.dicoding.picodiploma.bajp2.ui.frag.fav.movie

import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.bajp2.data.Repository
import com.dicoding.picodiploma.bajp2.data.source.local.entity.MovieEntity

class FavMovieVM (private val repository: Repository) : ViewModel() {
    fun getFavMovies() = repository.getFavoriteMovies()

    fun setFavMovie(movieEntity: MovieEntity) {
        val newState = !movieEntity.favorite
        repository.setFavoriteMovie(movieEntity, newState)
    }
}
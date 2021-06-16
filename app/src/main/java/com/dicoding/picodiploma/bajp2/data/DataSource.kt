package com.dicoding.picodiploma.bajp2.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.bumptech.glide.load.engine.Resource
import com.dicoding.picodiploma.bajp2.data.source.local.entity.MovieEntity
import com.dicoding.picodiploma.bajp2.data.source.local.entity.TvShowEntity

interface DataSource {
    fun getMovies(): LiveData<com.dicoding.picodiploma.bajp2.ov.Resource<PagedList<MovieEntity>>>
    fun getDetailMovie(movieId: String): LiveData<com.dicoding.picodiploma.bajp2.ov.Resource<MovieEntity>>
    fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>>
    fun setFavoriteMovie(movie: MovieEntity, state: Boolean)
    fun getTvShows(): LiveData<com.dicoding.picodiploma.bajp2.ov.Resource<PagedList<TvShowEntity>>>
    fun getDetailTvShow(tvShowId: String): LiveData<com.dicoding.picodiploma.bajp2.ov.Resource<TvShowEntity>>
    fun getFavoriteTvShows(): LiveData<PagedList<TvShowEntity>>
    fun setFavoriteTvShow(tvShow: TvShowEntity, state: Boolean)
}
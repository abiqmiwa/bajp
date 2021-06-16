package com.dicoding.picodiploma.bajp2.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.dicoding.picodiploma.bajp2.data.source.local.entity.MovieEntity
import com.dicoding.picodiploma.bajp2.data.source.local.entity.TvShowEntity
import com.dicoding.picodiploma.bajp2.data.source.local.room.DataDao

class LocalDS (private val mDataDao: DataDao) {

    companion object{
        private var INSTANCE: LocalDS? = null

        fun getInstance(dataDao: DataDao): LocalDS =
            INSTANCE ?: LocalDS(dataDao)
    }

    fun getAllMovies(): DataSource.Factory<Int, MovieEntity> = mDataDao.getMovies()

    fun getMovieById(id: String): LiveData<MovieEntity> = mDataDao.getMovieById(id)

    fun getFavoriteMovies(): DataSource.Factory<Int, MovieEntity> = mDataDao.getFavoriteMovies()

    fun getAllTvShows(): DataSource.Factory<Int, TvShowEntity> = mDataDao.getTvShows()

    fun getTvShowById(id: String): LiveData<TvShowEntity> = mDataDao.getTvShowById(id)

    fun getFavoriteTvShows(): DataSource.Factory<Int, TvShowEntity> = mDataDao.getFavoriteTvShows()

    fun insertMovies(movies: List<MovieEntity>) = mDataDao.insertMovies(movies)

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.favorite = newState
        mDataDao.updateMovie(movie)
    }

    fun updateMovie(movie: MovieEntity, newState: Boolean) {
        movie.favorite = newState
        mDataDao.updateMovie(movie)
    }

    fun insertTvShows(tvShows: List<TvShowEntity>) = mDataDao.insertTvShows(tvShows)

    fun setFavoriteTvShow(tvShow: TvShowEntity, newState: Boolean) {
        tvShow.favorite = newState
        mDataDao.updateTvShow(tvShow)
    }

    fun updateTvShow(tvShow: TvShowEntity, newState: Boolean) {
        tvShow.favorite = newState
        mDataDao.updateTvShow(tvShow)
    }
}
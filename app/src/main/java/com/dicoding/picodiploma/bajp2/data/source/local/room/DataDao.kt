package com.dicoding.picodiploma.bajp2.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.dicoding.picodiploma.bajp2.data.source.local.entity.MovieEntity
import com.dicoding.picodiploma.bajp2.data.source.local.entity.TvShowEntity

@Dao
interface DataDao {

    @Query("SELECT * FROM movieentities")
    fun getMovies() : DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM movieentities WHERE id = :id")
    fun getMovieById(id: String): LiveData<MovieEntity>

    @Query("SELECT * FROM movieentities WHERE favorite = 1")
    fun getFavoriteMovies(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM tvshowentities")
    fun getTvShows() : DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * FROM tvshowentities WHERE id = :id")
    fun getTvShowById(id: String) : LiveData<TvShowEntity>

    @Query("SELECT * FROM tvshowentities WHERE favorite = 1")
    fun getFavoriteTvShows(): DataSource.Factory<Int, TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieEntity>)

    @Update
    fun updateMovie(movie: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShows(tvShows: List<TvShowEntity>)

    @Update
    fun updateTvShow(tvShow: TvShowEntity)


}
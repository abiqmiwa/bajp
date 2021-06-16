package com.dicoding.picodiploma.bajp2.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.picodiploma.bajp2.data.Repository
import com.dicoding.picodiploma.bajp2.data.source.local.entity.MovieEntity
import com.dicoding.picodiploma.bajp2.data.source.local.entity.TvShowEntity
import com.dicoding.picodiploma.bajp2.etc.Dummy
import com.dicoding.picodiploma.bajp2.ov.Resource
import com.dicoding.picodiploma.bajp2.ui.frag.main.movie.FMovie.Companion.TYPE_MOVIE
import com.dicoding.picodiploma.bajp2.ui.frag.main.tv.FTv.Companion.TYPE_TVSHOW
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailVMTest{
    private lateinit var viewModel: DetailVM
    private val dummyMovie = Dummy.generateDummyMovies()[0]
    private val dummyTvShow = Dummy.generateDummyTvShows()[0]
    private val movieId = dummyMovie.id
    private val tvShowId = dummyTvShow.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var observerMovie: Observer<Resource<MovieEntity>>

    @Mock
    private lateinit var observerTvShow: Observer<Resource<TvShowEntity>>

    @Before
    fun setUp() {
        viewModel = DetailVM(repository)
    }

    @Test
    fun getMovie() {
        val dummyDetailMovie = Resource.success(Dummy.generateDummyDetailMovie())
        val movie = MutableLiveData<Resource<MovieEntity>>()
        movie.value = dummyDetailMovie

        Mockito.`when`(repository.getDetailMovie(movieId)).thenReturn(movie)
        viewModel.setFilm(movieId, TYPE_MOVIE)

        viewModel.getDataDetailMovie().observeForever(observerMovie)
        verify(observerMovie).onChanged(dummyDetailMovie)
    }

    @Test
    fun setFavoriteMovie() {
        val dummyDetailMovie = Resource.success(Dummy.generateDummyDetailMovie())
        val movie = MutableLiveData<Resource<MovieEntity>>()
        movie.value = dummyDetailMovie

        Mockito.`when`(repository.getDetailMovie(movieId)).thenReturn(movie)

        viewModel.setFilm(movieId, TYPE_MOVIE)
        viewModel.setFavoriteMovie()
        verify(repository).setFavoriteMovie(movie.value!!.data as MovieEntity, true)
        verifyNoMoreInteractions(observerMovie)
    }

    @Test
    fun getTvShow() {
        val dummyDetailTvShow = Resource.success(Dummy.generateDummyDetailTvShow())
        val tvShow = MutableLiveData<Resource<TvShowEntity>>()
        tvShow.value = dummyDetailTvShow

        Mockito.`when`(repository.getDetailTvShow(tvShowId)).thenReturn(tvShow)
        viewModel.setFilm(tvShowId, TYPE_TVSHOW)

        viewModel.getDataDetailTvShow().observeForever(observerTvShow)
        verify(observerTvShow).onChanged(dummyDetailTvShow)
    }
}
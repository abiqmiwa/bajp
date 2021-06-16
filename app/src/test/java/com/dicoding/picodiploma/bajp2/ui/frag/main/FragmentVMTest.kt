package com.dicoding.picodiploma.bajp2.ui.frag.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.dicoding.picodiploma.bajp2.data.Repository
import com.dicoding.picodiploma.bajp2.data.source.local.entity.MovieEntity
import com.dicoding.picodiploma.bajp2.data.source.local.entity.TvShowEntity
import com.dicoding.picodiploma.bajp2.ov.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FragmentVMTest{
    private lateinit var viewModel: FragmentVM

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var observerMovies: Observer<Resource<PagedList<MovieEntity>>>

    @Mock
    private lateinit var observerTvShows: Observer<Resource<PagedList<TvShowEntity>>>

    @Mock
    private lateinit var pagedListMovies: PagedList<MovieEntity>

    @Mock
    private lateinit var pagedListTvShows: PagedList<TvShowEntity>

    @Before
    fun setUp() {
        viewModel= FragmentVM(repository)
    }

    @Test
    fun getMovies() {
        val dummyMovie = Resource.success(pagedListMovies)
        val movies = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        movies.value = dummyMovie

        Mockito.`when`(repository.getMovies()).thenReturn(movies)
        val movie = viewModel.getMovies().value?.data
        assertNotNull(movie)
        verify(repository).getMovies()

        viewModel.getMovies().observeForever(observerMovies)
        verify(observerMovies).onChanged(dummyMovie)
    }

    @Test
    fun getTvShows() {
        val dummyTvShow = Resource.success(pagedListTvShows)
        val tvShows = MutableLiveData<Resource<PagedList<TvShowEntity>>>()
        tvShows.value = dummyTvShow

        Mockito.`when`(repository.getTvShows()).thenReturn(tvShows)
        val tvShow = viewModel.getTvShows().value
        assertNotNull(tvShow)
        verify(repository).getTvShows()

        viewModel.getTvShows().observeForever(observerTvShows)
        verify(observerTvShows).onChanged(dummyTvShow)
    }
}
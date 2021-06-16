package com.dicoding.picodiploma.bajp2.ui.frag.fav.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.dicoding.picodiploma.bajp2.data.Repository
import com.dicoding.picodiploma.bajp2.data.source.local.entity.MovieEntity
import com.dicoding.picodiploma.bajp2.etc.Dummy
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
class FavMovieVMTest{
    private lateinit var viewModel: FavMovieVM

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var observer: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var pagedList: PagedList<MovieEntity>

    @Before
    fun setUp() {
        viewModel = FavMovieVM(repository)
    }

    @Test
    fun getFavMovies() {
        val dummyMovie = pagedList
        Mockito.`when`(dummyMovie.size).thenReturn(3)
        val movies = MutableLiveData<PagedList<MovieEntity>>()
        movies.value = dummyMovie

        Mockito.`when`(repository.getFavoriteMovies()).thenReturn(movies)
        val movie = viewModel.getFavMovies().value
        verify(repository).getFavoriteMovies()
        assertNotNull(movie)
        assertEquals(3, movie?.size)

        viewModel.getFavMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }

    @Test
    fun setFavMovie() {
        viewModel.setFavMovie(Dummy.generateDummyDetailMovie())
        verify(repository).setFavoriteMovie(Dummy.generateDummyDetailMovie(), true)
        verifyNoMoreInteractions(repository)
    }
}
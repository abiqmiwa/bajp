package com.dicoding.picodiploma.bajp2.ui.frag.fav.tv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.dicoding.picodiploma.bajp2.data.Repository
import com.dicoding.picodiploma.bajp2.data.source.local.entity.TvShowEntity
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
class FavTvVMTest{
    private lateinit var viewModel: FavTvVM

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var observer: Observer<PagedList<TvShowEntity>>

    @Mock
    private lateinit var pagedList: PagedList<TvShowEntity>

    @Before
    fun setUp() {
        viewModel = FavTvVM(repository)
    }

    @Test
    fun getFavTvShows() {
        val dummyTvShow = pagedList
        Mockito.`when`(dummyTvShow.size).thenReturn(3)
        val tvShows = MutableLiveData<PagedList<TvShowEntity>>()
        tvShows.value = dummyTvShow

        Mockito.`when`(repository.getFavoriteTvShows()).thenReturn(tvShows)
        val tvShow = viewModel.getFavTvShow().value
        verify(repository).getFavoriteTvShows()
        assertNotNull(tvShow)
        assertEquals(3, tvShow?.size)

        viewModel.getFavTvShow().observeForever(observer)
        verify(observer).onChanged(dummyTvShow)
    }

    @Test
    fun setFavMovie() {
        viewModel.setFavTvShow(Dummy.generateDummyDetailTvShow())
        verify(repository).setFavoriteTvShow(Dummy.generateDummyDetailTvShow(), true)
        verifyNoMoreInteractions(repository)
    }
}
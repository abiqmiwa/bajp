package com.dicoding.picodiploma.bajp2.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.dicoding.picodiploma.bajp2.data.source.local.LocalDS
import com.dicoding.picodiploma.bajp2.data.source.local.entity.MovieEntity
import com.dicoding.picodiploma.bajp2.data.source.local.entity.TvShowEntity
import com.dicoding.picodiploma.bajp2.data.source.remote.RemoteDataSource
import com.dicoding.picodiploma.bajp2.etc.AppExecutors
import com.dicoding.picodiploma.bajp2.etc.Dummy
import com.dicoding.picodiploma.bajp2.etc.LiveDataTestUtil
import com.dicoding.picodiploma.bajp2.etc.PagedListUtil
import com.dicoding.picodiploma.bajp2.ov.Resource
import com.nhaarman.mockitokotlin2.*
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class RepositoryTest{
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val local = Mockito.mock(LocalDS::class.java)
    private val appExecutors = Mockito.mock(AppExecutors::class.java)
    private val repository = FakeRepository(remote, local, appExecutors)

    private val moviesResponse = Dummy.generateRemoteDummyMovies()
    private val movieId = moviesResponse[0].id.toString()
    private val movieDetail = Dummy.generateRemoteDummyDetailMovie()

    private val tvShowsReponse = Dummy.generateRemoteDummyTvShows()
    private val tvShowId = tvShowsReponse[0].id.toString()
    private val tvShowDetail = Dummy.generateRemoteDummyDetailTvShow()

    @Test
    fun getMovies() {
        val dataSourceFactory = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        Mockito.`when`(local.getAllMovies()).thenReturn(dataSourceFactory)
        repository.getMovies()

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(Dummy.generateDummyMovies()))
        verify(local).getAllMovies()
        assertNotNull(movieEntities)
        assertEquals(moviesResponse.size, movieEntities.data?.size)
    }

    @Test
    fun getDetailMovie() {
        val dummyDetail = MutableLiveData<MovieEntity>()
        dummyDetail.value = Dummy.generateDummyDetailMovie()
        Mockito.`when`(local.getMovieById(movieId)).thenReturn(dummyDetail)

        val movieDetailEntity = LiveDataTestUtil.getValue(repository.getDetailMovie(movieId))
        verify(local).getMovieById(movieId)
        assertNotNull(movieDetailEntity)
        assertEquals(movieDetail.id.toString(), movieDetailEntity.data?.id)
    }

    @Test
    fun getFavoriteMovie() {
        val dataSourceFactory = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        Mockito.`when`(local.getFavoriteMovies()).thenReturn(dataSourceFactory)
        repository.getFavoriteMovies()

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(Dummy.generateDummyMovies()))
        verify(local).getFavoriteMovies()
        assertNotNull(movieEntities)
        assertEquals(moviesResponse.size, movieEntities.data?.size)
    }

    @Test
    fun setFavoriteMovie() {
        repository.setFavoriteMovie(Dummy.generateDummyDetailMovie(), true)
        verify(local).setFavoriteMovie(Dummy.generateDummyDetailMovie(), true)
        verifyNoMoreInteractions(local)
    }

    @Test
    fun getTvShows() {
        val dataSourceFactory = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        Mockito.`when`(local.getAllTvShows()).thenReturn(dataSourceFactory)
        repository.getTvShows()

        val tvShowEntities = Resource.success(PagedListUtil.mockPagedList(Dummy.generateDummyTvShows()))
        verify(local).getAllTvShows()
        assertNotNull(tvShowEntities)
        assertEquals(tvShowsReponse.size, tvShowEntities.data?.size)
    }

    @Test
    fun getDetailTvShow() {
        val dummyDetail = MutableLiveData<TvShowEntity>()
        dummyDetail.value = Dummy.generateDummyDetailTvShow()
        Mockito.`when`(local.getTvShowById(tvShowId)).thenReturn(dummyDetail)

        val tvShowDetailEntity = LiveDataTestUtil.getValue(repository.getDetailTvShow(tvShowId))
        verify(local).getTvShowById(tvShowId)
        assertNotNull(tvShowDetailEntity)
        assertEquals(tvShowDetail.id.toString(), tvShowDetailEntity.data?.id)
    }

    @Test
    fun getFavoriteTvShow() {
        val dataSourceFactory = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        Mockito.`when`(local.getFavoriteTvShows()).thenReturn(dataSourceFactory)
        repository.getFavoriteTvShows()

        val tvShowEntities = Resource.success(PagedListUtil.mockPagedList(Dummy.generateDummyTvShows()))
        verify(local).getFavoriteTvShows()
        assertNotNull(tvShowEntities)
        assertEquals(tvShowsReponse.size, tvShowEntities.data?.size)
    }

    @Test
    fun setFavoriteTvShow() {
        repository.setFavoriteTvShow(Dummy.generateDummyDetailTvShow(), true)
        verify(local).setFavoriteTvShow(Dummy.generateDummyDetailTvShow(), true)
        verifyNoMoreInteractions(local)
    }
}
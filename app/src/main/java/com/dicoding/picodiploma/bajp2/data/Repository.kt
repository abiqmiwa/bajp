package com.dicoding.picodiploma.bajp2.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dicoding.picodiploma.bajp2.data.source.local.LocalDS
import com.dicoding.picodiploma.bajp2.data.source.local.entity.MovieEntity
import com.dicoding.picodiploma.bajp2.data.source.local.entity.TvShowEntity
import com.dicoding.picodiploma.bajp2.data.source.remote.ApiResponse
import com.dicoding.picodiploma.bajp2.data.source.remote.RemoteDataSource
import com.dicoding.picodiploma.bajp2.data.source.remote.response.movie.Movie
import com.dicoding.picodiploma.bajp2.data.source.remote.response.movie.MovieDR
import com.dicoding.picodiploma.bajp2.data.source.remote.response.tv.TvDR
import com.dicoding.picodiploma.bajp2.data.source.remote.response.tv.TvShow
import com.dicoding.picodiploma.bajp2.etc.AppExecutors
import com.dicoding.picodiploma.bajp2.ov.Resource

class Repository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDS,
    private val appExecutors: AppExecutors
): DataSource{

    companion object {
        @Volatile
        private var instance: Repository? = null
        fun getInstance(remoteData: RemoteDataSource, localDataSource: LocalDS, appExecutors: AppExecutors): Repository =
            instance ?: synchronized(this) {
                instance ?: Repository(remoteData, localDataSource, appExecutors)
            }
    }

    override fun getMovies(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, List<Movie>>(appExecutors) {
            override fun loadFromDb(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()

                return LivePagedListBuilder(localDataSource.getAllMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<Movie>>> =
                remoteDataSource.getMovies()

            override fun saveCallResult(data: List<Movie>) {
                val movieList = ArrayList<MovieEntity>()

                for (response in data) {
                    with(response){
                        val movie = MovieEntity(id.toString(), title, releaseDate, 0, posterPath, "", null, overview, false, voteAverage)
                        movieList.add(movie)
                    }
                }
                localDataSource.insertMovies(movieList)
            }
        }.asLiveData()
    }

    override fun getDetailMovie(movieId: String): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, MovieDR>(appExecutors) {
            override fun loadFromDb(): LiveData<MovieEntity> = localDataSource.getMovieById(movieId)

            override fun shouldFetch(data: MovieEntity?): Boolean =
                data != null && data.genre == "" && data.duration == 0 && data.status == null

            override fun createCall(): LiveData<ApiResponse<MovieDR>> =
                remoteDataSource.getDetailMovie(movieId)

            override fun saveCallResult(data: MovieDR) {
                val genres = StringBuilder().append("")

                for (i in data.genres.indices) {
                    if (i < data.genres.size - 1) {
                        genres.append("${data.genres[i].name}, ")
                    } else {
                        genres.append(data.genres[i].name)
                    }
                }

                with(data){
                    val detailMovie = MovieEntity(id.toString(), title, releaseDate, runtime, posterPath, genres.toString(), status, overview, false, voteAverage)
                    localDataSource.updateMovie(detailMovie, false)
                }

            }
        }.asLiveData()

    }

    override fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()

        return LivePagedListBuilder(localDataSource.getFavoriteMovies(), config).build()
    }

    override fun setFavoriteMovie(movie: MovieEntity, state: Boolean) {
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteMovie(movie, state)
        }
    }

    override fun getTvShows(): LiveData<Resource<PagedList<TvShowEntity>>>{
        return object : NetworkBoundResource<PagedList<TvShowEntity>, List<TvShow>>(appExecutors) {
            override fun loadFromDb(): LiveData<PagedList<TvShowEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()

                return LivePagedListBuilder(localDataSource.getAllTvShows(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TvShow>>> =
                remoteDataSource.getTvShows()

            override fun saveCallResult(data: List<TvShow>) {
                val tvShowList = ArrayList<TvShowEntity>()
                for (response in data) {
                    with(response) {
                        val tvShow = TvShowEntity(id.toString(), name, firstAirDate, 0, posterPath, "", null, overview, false, voteAverage)
                        tvShowList.add(tvShow)
                    }
                }
                localDataSource.insertTvShows(tvShowList)
            }
        }.asLiveData()

    }

    override fun getDetailTvShow(tvShowId: String): LiveData<Resource<TvShowEntity>> {
        return object : NetworkBoundResource<TvShowEntity, TvDR>(appExecutors) {
            override fun loadFromDb(): LiveData<TvShowEntity> = localDataSource.getTvShowById(tvShowId)

            override fun shouldFetch(data: TvShowEntity?): Boolean =
                data != null && data.duration == 0 && data.genre == "" && data.status == null

            override fun createCall(): LiveData<ApiResponse<TvDR>> =
                remoteDataSource.getDetailTvShow(tvShowId)

            override fun saveCallResult(data: TvDR) {
                val genres = StringBuilder().append("")

                for (i in data.genres.indices) {
                    if (i < data.genres.size - 1) {
                        genres.append("${data.genres[i].name}, ")
                    } else {
                        genres.append(data.genres[i].name)
                    }
                }

                with(data){
                    val detailTvShow = TvShowEntity(id.toString(), name, firstAirDate, episodeRunTime[0], posterPath, genres.toString(), status, overview, false, voteAverage)
                    localDataSource.updateTvShow(detailTvShow, false)
                }
            }
        }.asLiveData()
    }

    override fun getFavoriteTvShows(): LiveData<PagedList<TvShowEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()

        return LivePagedListBuilder(localDataSource.getFavoriteTvShows(), config).build()
    }

    override fun setFavoriteTvShow(tvShow: TvShowEntity, state: Boolean) {
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteTvShow(tvShow, state)
        }
    }
}
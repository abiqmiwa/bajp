package com.dicoding.picodiploma.bajp2.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.picodiploma.bajp2.api.ApiConfig
import com.dicoding.picodiploma.bajp2.data.source.remote.response.movie.Movie
import com.dicoding.picodiploma.bajp2.data.source.remote.response.movie.MovieDR
import com.dicoding.picodiploma.bajp2.data.source.remote.response.movie.MovieR
import com.dicoding.picodiploma.bajp2.data.source.remote.response.tv.TvDR
import com.dicoding.picodiploma.bajp2.data.source.remote.response.tv.TvR
import com.dicoding.picodiploma.bajp2.data.source.remote.response.tv.TvShow
import com.dicoding.picodiploma.bajp2.etc.ApiInfo.API_KEY
import com.dicoding.picodiploma.bajp2.etc.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {
    companion object {
        private const val TAG = "RemoteDataSource"

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }
    }

    fun getMovies(): LiveData<ApiResponse<List<Movie>>> {
        EspressoIdlingResource.increment()
        val resultMovies = MutableLiveData<ApiResponse<List<Movie>>>()
        val client = ApiConfig.getApiService().getMovies(API_KEY)

        client.enqueue(object : Callback<MovieR> {
            override fun onResponse(call: Call<MovieR>, response: Response<MovieR>) {
                resultMovies.value = ApiResponse.success(response.body()?.results as List<Movie>)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<MovieR>, t: Throwable) {
                Log.e(TAG, "getMovies onFailure: ${t.message.toString()}")
                EspressoIdlingResource.decrement()
            }
        })
        return resultMovies
    }

    fun getDetailMovie(movieId: String): LiveData<ApiResponse<MovieDR>> {
        EspressoIdlingResource.increment()
        val resultDetailMovie = MutableLiveData<ApiResponse<MovieDR>>()
        val client = ApiConfig.getApiService().getMovieDetail(movieId, API_KEY)

        client.enqueue(object : Callback<MovieDR>{
            override fun onResponse(
                call: Call<MovieDR>,
                response: Response<MovieDR>
            ) {
                resultDetailMovie.value = ApiResponse.success(response.body() as MovieDR)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<MovieDR>, t: Throwable) {
                Log.e(TAG, "getDetailMovie onFailure: ${t.message.toString()}")
                EspressoIdlingResource.decrement()
            }
        })
        return resultDetailMovie
    }

    fun getTvShows(): LiveData<ApiResponse<List<TvShow>>> {
        EspressoIdlingResource.increment()
        val resultTvShows = MutableLiveData<ApiResponse<List<TvShow>>>()
        val client = ApiConfig.getApiService().getTvShows(API_KEY)

        client.enqueue(object : Callback<TvR>{
            override fun onResponse(
                call: Call<TvR>,
                response: Response<TvR>
            ) {
                resultTvShows.value = ApiResponse.success(response.body()?.results as List<TvShow>)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<TvR>, t: Throwable) {
                Log.e(TAG, "getTvShows onFailure: ${t.message.toString()}")
                EspressoIdlingResource.decrement()
            }
        })
        return resultTvShows
    }

    fun getDetailTvShow(tvId: String): LiveData<ApiResponse<TvDR>> {
        EspressoIdlingResource.increment()
        val resultDetailTvShow = MutableLiveData<ApiResponse<TvDR>>()
        val client = ApiConfig.getApiService().getTvShowDetail(tvId, API_KEY)

        client.enqueue(object : Callback<TvDR>{
            override fun onResponse(
                call: Call<TvDR>,
                response: Response<TvDR>
            ) {
                resultDetailTvShow.value = ApiResponse.success(response.body() as TvDR)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<TvDR>, t: Throwable) {
                Log.e(TAG, "getDetailTvShow onFailure: ${t.message.toString()}")
                EspressoIdlingResource.decrement()
            }

        })
        return resultDetailTvShow
    }
}
package com.dicoding.picodiploma.bajp2

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.picodiploma.bajp2.data.Repository
import com.dicoding.picodiploma.bajp2.ui.detail.DetailVM
import com.dicoding.picodiploma.bajp2.ui.frag.ContentVM
import com.dicoding.picodiploma.bajp2.ui.frag.fav.movie.FavMovieVM
import com.dicoding.picodiploma.bajp2.ui.frag.fav.tv.FavTvVM
import com.dicoding.picodiploma.bajp2.ui.frag.main.FragmentVM

class VMFactory private constructor(private val repository: Repository) : ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var instance: VMFactory? = null

        fun getInstance(context: Context): VMFactory =
            instance ?: synchronized(this) {
                instance ?: VMFactory(Injection.provideRepository(context))
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(FragmentVM::class.java) -> {
                FragmentVM(repository) as T
            }
            modelClass.isAssignableFrom(DetailVM::class.java) -> {
                DetailVM(repository) as T
            }
            modelClass.isAssignableFrom(FavMovieVM::class.java) -> {
                FavMovieVM(repository) as T
            }
            modelClass.isAssignableFrom(FavTvVM::class.java) -> {
                FavTvVM(repository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}
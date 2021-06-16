package com.dicoding.picodiploma.bajp2.ui.frag.main

import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.bajp2.data.Repository

class FragmentVM (private val repository: Repository) : ViewModel() {
    fun getMovies() = repository.getMovies()
    fun getTvShows() = repository.getTvShows()
}
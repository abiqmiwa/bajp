package com.dicoding.picodiploma.bajp2.ui.frag.fav.tv

import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.bajp2.data.Repository
import com.dicoding.picodiploma.bajp2.data.source.local.entity.TvShowEntity

class FavTvVM (private val repository: Repository) : ViewModel() {
    fun getFavTvShow() = repository.getFavoriteTvShows()
    fun setFavTvShow(tvShowEntity: TvShowEntity) {
        val newState = !tvShowEntity.favorite
        repository.setFavoriteTvShow(tvShowEntity, newState)
    }
}
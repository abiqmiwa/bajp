package com.dicoding.picodiploma.bajp2.ui.frag

import com.dicoding.picodiploma.bajp2.data.source.local.entity.MovieEntity

interface ContentCallback {
    fun onItemClicked(data: MovieEntity)
}
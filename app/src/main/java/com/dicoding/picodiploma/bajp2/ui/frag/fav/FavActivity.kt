package com.dicoding.picodiploma.bajp2.ui.frag.fav

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.picodiploma.bajp2.databinding.ActivityFavBinding

class FavActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Favorite"

        val sectionsPagerAdapter = AdapterFav(this, supportFragmentManager)
        binding.viewPager.adapter = sectionsPagerAdapter
        binding.tabs.setupWithViewPager(binding.viewPager)

        supportActionBar?.elevation = 0f

    }
}
package com.dicoding.picodiploma.bajp2.ui.frag.fav

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.dicoding.picodiploma.bajp2.R
import com.dicoding.picodiploma.bajp2.ui.frag.fav.movie.FavMovie
import com.dicoding.picodiploma.bajp2.ui.frag.fav.tv.FavTv

class AdapterFav (private val mContext: Context, fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.movies, R.string.tv_show)
    }

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> FavMovie()
            1 -> FavTv()
            else -> Fragment()
        }

    override fun getPageTitle(position: Int): CharSequence = mContext.resources.getString(TAB_TITLES[position])

    override fun getCount(): Int = TAB_TITLES.size

}
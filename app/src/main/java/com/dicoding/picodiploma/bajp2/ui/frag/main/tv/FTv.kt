package com.dicoding.picodiploma.bajp2.ui.frag.main.tv

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.picodiploma.bajp2.VMFactory
import com.dicoding.picodiploma.bajp2.data.source.local.entity.TvShowEntity
import com.dicoding.picodiploma.bajp2.databinding.FragmentFTvBinding
import com.dicoding.picodiploma.bajp2.ov.Resource
import com.dicoding.picodiploma.bajp2.ov.Status
import com.dicoding.picodiploma.bajp2.ui.detail.DetailActivity
import com.dicoding.picodiploma.bajp2.ui.frag.main.FragmentVM

class FTv : Fragment(), TvAdapter.OnItemClickCallback {

    companion object {
        const val TYPE_TVSHOW = "type_tvshow"
    }

    private lateinit var binding: FragmentFTvBinding
    private lateinit var tvShowAdapter: TvAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFTvBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            showProgressBar(true)

            val factory = VMFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[FragmentVM::class.java]
            tvShowAdapter = TvAdapter()
            val tvShow = viewModel.getTvShows()

            tvShow.observe(viewLifecycleOwner, tvShowObserver)

            with(binding.rvTvShow) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = tvShowAdapter
            }
        }
    }

    private val tvShowObserver = Observer<Resource<PagedList<TvShowEntity>>> { tvShows ->
        if (tvShows != null) {
            when (tvShows.status) {
                Status.LOADING -> showProgressBar(true)
                Status.SUCCESS -> {
                    showProgressBar(false)
                    tvShowAdapter.submitList(tvShows.data)
                    tvShowAdapter.setOnItemClickCallback(this)
                    tvShowAdapter.notifyDataSetChanged()
                }
                Status.ERROR -> {
                    showProgressBar(false)
                    Toast.makeText(context, "Ada yang salah", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun showProgressBar(state: Boolean) {
        binding.progressBar.isVisible = state
    }

    override fun onItemClicked(id: String) {
        startActivity(
            Intent(context, DetailActivity::class.java)
                .putExtra(DetailActivity.EXTRA_DATA, id)
                .putExtra(DetailActivity.EXTRA_TYPE, TYPE_TVSHOW)
        )
    }
}
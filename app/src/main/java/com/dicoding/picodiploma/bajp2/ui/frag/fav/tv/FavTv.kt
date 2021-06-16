package com.dicoding.picodiploma.bajp2.ui.frag.fav.tv

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.bajp2.R
import com.dicoding.picodiploma.bajp2.VMFactory
import com.dicoding.picodiploma.bajp2.databinding.FragmentFavTvBinding
import com.dicoding.picodiploma.bajp2.ui.detail.DetailActivity
import com.dicoding.picodiploma.bajp2.ui.frag.main.tv.FTv
import com.google.android.material.snackbar.Snackbar

class FavTv : Fragment(), FavTvAdapter.OnItemClickCallback {

    private var binding: FragmentFavTvBinding? = null

    private lateinit var viewModel: FavTvVM
    private lateinit var adapter: FavTvAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavTvBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFavTvShow().observe(viewLifecycleOwner, { favTvShow ->
            if (favTvShow != null) {
                adapter.submitList(favTvShow)
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemTouchHelper.attachToRecyclerView(binding?.rvFavTvShow)

        if (activity != null){
            val factory = VMFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[FavTvVM::class.java]

            adapter = FavTvAdapter()
            adapter.setOnItemClickCallback(this)

            viewModel.getFavTvShow().observe(viewLifecycleOwner, { favorite ->
                if (favorite != null){
                    adapter.submitList(favorite)
                }
            })

            with(binding?.rvFavTvShow) {
                this?.layoutManager = LinearLayoutManager(context)
                this?.setHasFixedSize(true)
                this?.adapter = adapter
            }

        }
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int =
            makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean = true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val swipedPosition = viewHolder.adapterPosition
                val tvShowEntity = adapter.getSwipedData(swipedPosition)
                tvShowEntity?.let { viewModel.setFavTvShow(it) }

                val snackBar = Snackbar.make(requireView(), R.string.ok, Snackbar.LENGTH_LONG)
                snackBar.setAction(R.string.undo) { _ ->
                    tvShowEntity?.let { viewModel.setFavTvShow(it) }
                }
                snackBar.show()
            }
        }
    })

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun onItemClicked(id: String) {
        startActivity(
            Intent(context, DetailActivity::class.java)
                .putExtra(DetailActivity.EXTRA_DATA, id)
                .putExtra(DetailActivity.EXTRA_TYPE, FTv.TYPE_TVSHOW)
        )
    }
}
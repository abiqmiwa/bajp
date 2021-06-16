package com.dicoding.picodiploma.bajp2.ui.frag.main.tv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.picodiploma.bajp2.R
import com.dicoding.picodiploma.bajp2.data.source.local.entity.TvShowEntity
import com.dicoding.picodiploma.bajp2.databinding.ItemsContentBinding
import com.dicoding.picodiploma.bajp2.etc.ApiInfo.IMAGE_URL

class TvAdapter : PagedListAdapter<TvShowEntity, TvAdapter.ContentViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowEntity>() {
            override fun areItemsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val itemsContentBinding = ItemsContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContentViewHolder(itemsContentBinding)
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        val content = getItem(position)
        if (content != null){
            holder.bind(content)
        }
    }

    inner class ContentViewHolder (private val binding: ItemsContentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(content: TvShowEntity){
            with(binding){
                tvTitle.text = content.title
                tvDate.text = content.release
                tvRating.text = content.rating.toString()
                tvOverview.text = content.overview

                Glide.with(itemView.context)
                    .load(IMAGE_URL + content.poster)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                    .into(imgPoster)

                itemView.setOnClickListener {
                    onItemClickCallback.onItemClicked(content.id)
                }
            }
        }

    }
    interface OnItemClickCallback {
        fun onItemClicked(id: String)
    }
}
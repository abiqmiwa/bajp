package com.dicoding.picodiploma.bajp2.ui.frag

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.picodiploma.bajp2.R
import com.dicoding.picodiploma.bajp2.data.source.local.entity.MovieEntity
import com.dicoding.picodiploma.bajp2.databinding.ItemsContentBinding
import com.dicoding.picodiploma.bajp2.etc.ApiInfo.IMAGE_URL

class ContentAdapter (private val callback: ContentCallback) : RecyclerView.Adapter<ContentAdapter.ContentViewHolder>() {

    private val listContent = ArrayList<MovieEntity>()

    fun setContent(content: List<MovieEntity>?){
        if (content == null) return
        listContent.clear()
        listContent.addAll(content)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val itemsContentBinding = ItemsContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContentViewHolder(itemsContentBinding)
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        val content = listContent[position]
        holder.bind(content)
    }

    override fun getItemCount(): Int = listContent.size

    inner class ContentViewHolder (private val binding: ItemsContentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(content: MovieEntity){
            with(binding){
                tvTitle.text = content.title
                tvDate.text = content.release
                tvRating.text = content.rating.toString()
                tvOverview.text = content.overview

                Glide.with(itemView.context)
                    .load(IMAGE_URL + content.poster)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                    .into(imgPoster)

                itemView.setOnClickListener {
                    callback.onItemClicked(content)
                }
            }
        }
    }
}
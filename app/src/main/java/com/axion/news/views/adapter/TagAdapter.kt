package com.axion.news.views.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.axion.news.databinding.RecycleViewItemTagsBinding
import com.axion.news.services.AppExecutors
import com.axion.news.vo.Genres

class TagAdapter(appExecutors: AppExecutors)
    : DataBoundListAdapter<Genres, RecycleViewItemTagsBinding>(
    appExecutors = appExecutors,
    diffCallback = object : DiffUtil.ItemCallback<Genres>() {
        override fun areContentsTheSame(oldItem: Genres, newItem: Genres): Boolean = oldItem == newItem
        override fun areItemsTheSame(oldItem: Genres, newItem: Genres): Boolean = oldItem == newItem
    }) {

    override fun createBinding(parent: ViewGroup): RecycleViewItemTagsBinding = RecycleViewItemTagsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    override fun bind(binding: RecycleViewItemTagsBinding, item: Genres, position: Int, isLast: Boolean) {
        binding.genres = item
    }

}
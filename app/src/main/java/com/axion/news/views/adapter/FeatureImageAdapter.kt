package com.axion.news.views.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.axion.news.databinding.RecycleViewItemFeatureBinding
import com.axion.news.network.responses.Content
import com.axion.news.services.AppExecutors

class FeatureImageAdapter(appExecutors: AppExecutors) :DataBoundListAdapter<Content, RecycleViewItemFeatureBinding>(appExecutors,
    diffCallback = object : DiffUtil.ItemCallback<Content>() {
        override fun areItemsTheSame(oldItem: Content, newItem: Content): Boolean = oldItem == newItem
        override fun areContentsTheSame(oldItem: Content, newItem: Content): Boolean = oldItem == newItem

    }){
    override fun createBinding(parent: ViewGroup): RecycleViewItemFeatureBinding = RecycleViewItemFeatureBinding.inflate(LayoutInflater.from(parent.context), parent, false)

    override fun bind(binding: RecycleViewItemFeatureBinding, item: Content, position: Int, isLast: Boolean) {
        binding.content = item
    }
}
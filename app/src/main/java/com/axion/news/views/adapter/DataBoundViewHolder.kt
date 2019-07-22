package com.axion.news.views.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class DataBoundViewHolder<T : ViewDataBinding> internal constructor(val binding: T) : RecyclerView.ViewHolder(binding.root)
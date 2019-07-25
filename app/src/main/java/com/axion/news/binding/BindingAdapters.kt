package com.axion.news.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("setImage")
fun ImageView.setImage(url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(this.context)
            .load(url)
            .into(this)
    }
}



@BindingAdapter("setUserImage")
fun ImageView.setUserImage(url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(this.context)
            .load(url)
            .apply(RequestOptions().override(1024, 1024))
            .apply(RequestOptions.circleCropTransform())
            .into(this)
    }
}
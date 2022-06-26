package com.xsims.common.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.xsims.common.R

@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(url: String) {
  this.load(url) {
    placeholder(R.drawable.placeholder)
    crossfade(true)
  }
}
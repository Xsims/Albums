package com.xsims.common.extensions

import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.activity.ComponentActivity
import androidx.databinding.ViewDataBinding
import androidx.viewbinding.ViewBinding

inline fun <T : ViewDataBinding> ComponentActivity.viewBinding(
  crossinline bindingInflater: (LayoutInflater) -> T
) = lazy(LazyThreadSafetyMode.NONE) {
  bindingInflater.invoke(layoutInflater).also {
    it.lifecycleOwner = this
    setContentView(it.root)
  }
}

inline fun <T : ViewBinding> FrameLayout.viewBinding(
  crossinline bindingInflater: (LayoutInflater) -> T
) = lazy(LazyThreadSafetyMode.NONE) {
  bindingInflater.invoke(LayoutInflater.from(context)).also {
    addView(it.root)
  }
}
package com.xsims.common.base_ui

import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView

class BindingViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
  fun setVm(vm: Any) {
    binding.setVariable(BR.vm, vm)
  }
}
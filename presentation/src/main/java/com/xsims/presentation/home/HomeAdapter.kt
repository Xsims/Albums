package com.xsims.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.xsims.common.base_ui.BindingViewHolder
import com.xsims.common.base_ui.RecyclerViewBindingAdapter
import com.xsims.presentation.databinding.ItemMusicBinding
import com.xsims.presentation.models.MusicUi

class HomeAdapter(
  val onMusicItemClickListener: () -> (Unit)
) : RecyclerViewBindingAdapter(useDiffUtil = true) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    // TODO check type of item before binding
    return BindingViewHolder(ItemMusicBinding.inflate(inflater, parent, false))
  }

  override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
    super.onBindViewHolder(holder, position)
    // TODO check type of item before applying listener
    holder.binding.root.setOnClickListener { onMusicItemClickListener() }
  }

  override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean = when {
    oldItem is MusicUi && newItem is MusicUi -> oldItem.id == newItem.id
    else -> false
  }

  override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean = when {
    oldItem is MusicUi && newItem is MusicUi -> oldItem == newItem
    else -> false
  }
}
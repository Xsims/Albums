package com.xsims.common.base_ui

import androidx.annotation.CallSuper
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class RecyclerViewBindingAdapter(private val useDiffUtil: Boolean = false) :
// TODO : Upgrade to PagingDataAdapter and try to make it compatible with generic types
  RecyclerView.Adapter<BindingViewHolder>() {

  // TODO : Improve setter to avoid calling notify data set changed
  var dataSet: List<Any> = mutableListOf()
    get() {
      return if (useDiffUtil) {
        listDiffer.currentList
      } else {
        field
      }
    }
    set(value) {
      if (useDiffUtil) {
        submitList(value)
      } else {
        field = value
        notifyDataSetChanged()
      }
    }

  private val diffCallback = object : DiffUtil.ItemCallback<Any>() {
    override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
      return this@RecyclerViewBindingAdapter.areItemsTheSame(oldItem, newItem)
    }

    override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
      return this@RecyclerViewBindingAdapter.areContentsTheSame(oldItem, newItem)
    }
  }

  private val listDiffer = AsyncListDiffer(this, diffCallback)

  private fun submitList(list: List<Any>) {
    listDiffer.submitList(list)
  }

  override fun getItemCount(): Int {
    return dataSet.size
  }

  @CallSuper
  override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
    dataSet.let {
      holder.setVm(it[position])
    }
  }

  protected open fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
    return false
  }

  protected open fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
    return false
  }

}
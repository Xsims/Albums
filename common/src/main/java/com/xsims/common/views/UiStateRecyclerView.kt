package com.xsims.common.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.xsims.common.R
import com.xsims.common.databinding.RecyclerEmptyLayoutBinding
import com.xsims.common.databinding.RecyclerErrorLayoutBinding
import com.xsims.common.databinding.RecyclerLoadingLayoutBinding
import com.xsims.common.databinding.UiStateRecyclerLayoutBinding

class UiStateRecyclerView constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

  constructor(context: Context) : this(context, null, 0)
  constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0)

  private val binding: UiStateRecyclerLayoutBinding =
    UiStateRecyclerLayoutBinding.inflate(LayoutInflater.from(context), this)

  private val errorBinding: RecyclerErrorLayoutBinding = binding.customErrorView
  private val emptyBinding: RecyclerEmptyLayoutBinding = binding.customEmptyView
  private val loadingBinding: RecyclerLoadingLayoutBinding = binding.customLoadingView

  // expose the recycler view
  val recyclerView: RecyclerView
    get() = binding.customRecyclerView

  var errorText: String = ""
    set(value) {
      field = value
      errorBinding.errorMsgText.text = value
    }

  var emptyText: String = ""
    set(value) {
      field = value
      emptyBinding.emptyMessage.text = value
    }

  @DrawableRes
  var errorIcon = 0
    set(value) {
      field = value
      errorBinding.errorImage.setImageResource(value)
    }

  @DrawableRes
  var emptyIcon = 0
    set(value) {
      field = value
      emptyBinding.emptyImage.setImageResource(value)
    }

  init {
    context.theme.obtainStyledAttributes(
      attrs,
      R.styleable.UiStateRecyclerView,
      0,
      0
    ).apply {
      try {
        errorText = getString(R.styleable.UiStateRecyclerView_errorText) ?: "Something went wrong"
        emptyText =
          getString(R.styleable.UiStateRecyclerView_emptyText) ?: "Nothing to show"
        errorIcon = getResourceId(
          R.styleable.UiStateRecyclerView_errorIcon,
          android.R.drawable.ic_menu_close_clear_cancel
        )
        emptyIcon =
          getResourceId(R.styleable.UiStateRecyclerView_emptyIcon, android.R.drawable.ic_menu_help)
      } finally {
        recycle()
      }
    }
  }

  fun showEmptyView(msg: String? = null) {
    emptyText = msg ?: emptyText
    loadingBinding.root.visibility = View.GONE
    errorBinding.root.visibility = View.GONE
    emptyBinding.root.visibility = View.VISIBLE
  }

  fun showErrorView(msg: String? = null, callback: () -> Unit) {
    errorBinding.retryButton.setOnClickListener {
      callback()
    }
    errorText = msg ?: errorText
    loadingBinding.root.visibility = View.GONE
    emptyBinding.root.visibility = View.GONE
    errorBinding.root.visibility = View.VISIBLE
  }

  fun showLoadingView() {
    emptyBinding.root.visibility = View.GONE
    errorBinding.root.visibility = View.GONE
    loadingBinding.root.visibility = View.VISIBLE
  }

  fun hideAllViews() {
    loadingBinding.root.visibility = View.GONE
    errorBinding.root.visibility = View.GONE
    emptyBinding.root.visibility = View.GONE
  }
}
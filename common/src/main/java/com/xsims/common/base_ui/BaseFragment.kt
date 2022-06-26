package com.xsims.common.base_ui

import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<T : ViewDataBinding> : Fragment() {
  private var _binding: T? = null
  protected var binding: T
    get() = _binding!!
    set(value) {
      _binding = value
    }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}
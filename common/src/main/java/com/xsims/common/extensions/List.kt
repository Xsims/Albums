package com.xsims.common.extensions

inline fun <reified R> List<*>.filterIsInstanceOrException(): List<R> {
  return this.filterIsInstance<R>()
    .apply {
      if (this@filterIsInstanceOrException.size != this.size)
        throw ClassCastException("The list cannot be cast to ${R::class.java.simpleName}")
    }
}
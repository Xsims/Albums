package com.xsims.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xsims.common.base_ui.BaseFragment
import com.xsims.common.models.UiState.Error
import com.xsims.common.models.UiState.Loading
import com.xsims.common.models.UiState.Success
import com.xsims.presentation.databinding.FragmentHomeBinding
import com.xsims.presentation.models.MusicUi
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

  private val vm: HomeViewModel by viewModel()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = FragmentHomeBinding.inflate(inflater, container, false)
    binding.lifecycleOwner = viewLifecycleOwner
    binding.vm = vm
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.uiStateRecyclerView.recyclerView.apply {
      layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
      adapter = HomeAdapter {

      }
    }

    binding.swipeRefreshLayout.setOnRefreshListener {
      binding.uiStateRecyclerView.hideAllViews()
      vm.reloadMusics()
      binding.swipeRefreshLayout.isRefreshing = false
    }

    vm.musics.observe(viewLifecycleOwner) { uiState ->
      when (uiState) {
        is Success -> binding.uiStateRecyclerView.showDataView(uiState.data.map { MusicUi.mapFrom(it) })
        is Loading -> binding.uiStateRecyclerView.showLoadingView()
        is Error -> binding.uiStateRecyclerView.showErrorView(uiState.errorMessage) {
          vm.reloadMusics()
        }
      }
    }
  }
}
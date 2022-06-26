package com.xsims.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
  private val homeAdapter by lazy { HomeAdapter { } }

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

    binding.musicRecyclerView.apply {
      layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
      adapter = homeAdapter
    }

    vm.musics.observe(viewLifecycleOwner) {
      when (it) {
        is Success -> homeAdapter.dataSet = it.data.map { music ->
          // TODO : Improve mapping with interface or simply creating mapper
          MusicUi(
            albumId = music.id,
            id = music.albumId,
            title = music.title,
            url = music.url,
            thumbnailUrl = music.thumbnailUrl,
          )
        }
        // TODO : Extract text in string resource app module (be careful of circular dependency)
        is Loading -> Toast.makeText(context, "Loading", Toast.LENGTH_LONG).show()
        is Error -> Toast.makeText(context, it.errorMessage, Toast.LENGTH_LONG).show()
      }
    }
  }
}
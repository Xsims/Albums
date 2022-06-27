package com.xsims.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.xsims.common.base_ui.BaseFragment
import com.xsims.common.binding.setImageUrl
import com.xsims.presentation.databinding.FragmentDetailsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {

  private val args: DetailsFragmentArgs by navArgs()
  private val vm: DetailsViewModel by viewModel()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = FragmentDetailsBinding.inflate(inflater, container, false)
    binding.lifecycleOwner = viewLifecycleOwner
    binding.vm = vm

    vm.getMusic(args.musicId)

    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    vm.music.observe(viewLifecycleOwner) {
      binding.imageViewMusic.setImageUrl(it.url)
      binding.textViewMusicId.text = it.id.toString()
      binding.textViewAlbumTitle.text = it.title
    }
  }
}
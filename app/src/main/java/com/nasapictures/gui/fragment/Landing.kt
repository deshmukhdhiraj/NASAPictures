package com.android.base.com.nasapictures.gui.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewbinding.ViewBinding
import com.nasapictures.R
import com.nasapictures.base.BaseFragment
import com.nasapictures.databinding.LandingBinding
import com.sensibol.android.base.com.nasapictures.gui.adapter.ImageAdapter
import com.sensibol.android.base.com.nasapictures.gui.viewModels.LandingViewModel
import com.sensibol.android.base.observe
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class Landing : BaseFragment() {
    override val layoutResId: Int get() = R.layout.landing
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> ViewBinding
        get() = LandingBinding::inflate
    override val binding: LandingBinding get() = super.binding as LandingBinding


    internal lateinit var imageAdapter: ImageAdapter
    private val viewModel: LandingViewModel by viewModels()


    override fun onInitView() {
        viewModel.apply {
            observe(getNasaImages) {
                imageAdapter.ResponseData = it
            }
            getImages()
        }
        binding.apply {
            imageAdapter = ImageAdapter()
            rvImages.apply {

                layoutManager = GridLayoutManager(context, 3)
                adapter = imageAdapter

            }
            imageAdapter.apply {
                onImageClickListener = {
                    //findNavController().navigate(R.id.action_landingFrag_to_imageDetailFrag)

                    findNavController().navigate(
                        LandingDirections.actionLandingFragToImageDetailFrag(
                            it
                        )
                    )
                }
            }

        }
    }

}
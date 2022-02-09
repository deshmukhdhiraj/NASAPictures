package com.android.base.com.nasapictures.gui.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.viewbinding.ViewBinding
import com.nasapictures.R
import com.nasapictures.base.BaseFragment
import com.nasapictures.databinding.ImageDetailBinding
import com.nasapictures.gui.model.ResponseDataItem
import com.sensibol.android.base.com.nasapictures.gui.adapter.CustomPagerAdapter
import com.sensibol.android.base.com.nasapictures.gui.viewModels.LandingViewModel
import com.sensibol.android.base.observe

class ImageDetail : BaseFragment() {
    override val layoutResId: Int = R.layout.image_detail
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> ViewBinding =
        ImageDetailBinding::inflate
    val args: ImageDetailArgs by navArgs()

    companion object {
        const val ARG_OBJECT = "object"
    }

    override val binding: ImageDetailBinding get() = super.binding as ImageDetailBinding
    private lateinit var demoCollectionAdapter: CustomPagerAdapter
    private val viewModel: LandingViewModel by viewModels()
    override fun onInitView() {

        viewModel.apply {
            observe(getNasaImages) {
                demoCollectionAdapter.ResponseData = it
            }
            getSortedImages(args.imagedata)
        }
        demoCollectionAdapter = CustomPagerAdapter(this)
        binding.apply {

            pager.apply {
                pager.adapter = demoCollectionAdapter


            }


        }
    }

}
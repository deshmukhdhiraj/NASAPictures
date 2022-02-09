package com.android.base.com.nasapictures.gui.fragment

import android.R.attr.left
import android.R.attr.right
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.viewbinding.ViewBinding
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.google.android.material.tabs.TabLayoutMediator
import com.nasapictures.R
import com.nasapictures.base.BaseFragment
import com.nasapictures.databinding.ImageDetailBinding
import com.sensibol.android.base.com.nasapictures.gui.adapter.CustomPagerAdapter
import com.sensibol.android.base.com.nasapictures.gui.viewModels.LandingViewModel
import com.sensibol.android.base.observe


class SwipableImageDetail : BaseFragment() {
    override val layoutResId: Int = R.layout.image_detail
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> ViewBinding =
        ImageDetailBinding::inflate
    val args: SwipableImageDetailArgs by navArgs()

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
                binding.apply {
                    pager.setCurrentItem(it.indexOf(args.imagedata))
                } }
            getImages()
        }
        demoCollectionAdapter = CustomPagerAdapter(this)
        binding.apply {

            pager.apply {
                this.adapter = demoCollectionAdapter
            }


            leftNav.setOnClickListener {
                var tab: Int = pager.getCurrentItem()
                if (tab > 0) {
                    tab--
                    pager.setCurrentItem(tab)
                } else if (tab == 0) {
                    pager.setCurrentItem(tab)
                }
            }

            rightNav.setOnClickListener {
                var tab: Int = pager.getCurrentItem()
                tab++
                pager.setCurrentItem(tab)
            }

            pager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    val currentItem: Int = position
                    if (currentItem == demoCollectionAdapter.itemCount - 1)
                        rightNav.visibility = View.INVISIBLE
                    else
                        rightNav.visibility = View.VISIBLE

                    if (currentItem == 0)
                        leftNav.visibility = View.INVISIBLE
                    else
                        leftNav.visibility = View.VISIBLE

                }


            })

        }
    }

}
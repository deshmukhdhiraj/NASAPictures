package com.sensibol.android.base.com.nasapictures.gui.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.android.base.com.nasapictures.gui.fragment.ImageDetail.Companion.ARG_OBJECT
import com.nasapictures.R
import com.nasapictures.base.BaseFragment
import com.nasapictures.databinding.SwipeBinding
import com.nasapictures.gui.model.ResponseDataItem
import com.sensibol.android.base.loadUrl

class SwipeViewFragment : BaseFragment() {
    override val layoutResId: Int = R.layout.swipe
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> ViewBinding
        get() = SwipeBinding::inflate
    override val binding: SwipeBinding
        get() = super.binding as SwipeBinding

    override fun onInitView() {
        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {

            binding.apply {
                getParcelable<ResponseDataItem>(ARG_OBJECT)?.let { ivCoverImageswipe.loadUrl(it.url) }
                getParcelable<ResponseDataItem>(ARG_OBJECT)?.let { title.text = it.title }
                getParcelable<ResponseDataItem>(ARG_OBJECT)?.let {
                    explanation.text = it.explanation
                }
            }

        }
    }


}
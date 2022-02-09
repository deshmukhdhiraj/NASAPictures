package com.sensibol.android.base.com.nasapictures.gui.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.android.base.com.nasapictures.gui.fragment.ImageDetail
import com.nasapictures.gui.model.ResponseDataItem
import com.sensibol.android.base.com.nasapictures.gui.fragment.SwipeViewFragment
import kotlin.properties.Delegates

class CustomPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    internal var ResponseData: List<ResponseDataItem> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = ResponseData.size

    override fun createFragment(position: Int): Fragment {
        val fragment = SwipeViewFragment()
        fragment.arguments = Bundle().apply {
            putParcelable(ImageDetail.ARG_OBJECT, ResponseData[position])
        }
        return fragment
    }
}


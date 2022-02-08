package com.nasapictures.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.annotation.LayoutRes
import androidx.fragment.app.FragmentActivity
import androidx.viewbinding.ViewBinding
import com.sensibol.android.base.displayName

import timber.log.Timber

abstract class BaseActivity : FragmentActivity() {

    @get:LayoutRes
    protected abstract val layoutResId: Int

    protected abstract val bindingInflater: (LayoutInflater) -> ViewBinding

    private var _binding: ViewBinding? = null

    protected open val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.v("onCreate $displayName")
        super.onCreate(savedInstanceState)
        _binding = bindingInflater(layoutInflater)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        Timber.v("onDestroy $displayName")
    }

}
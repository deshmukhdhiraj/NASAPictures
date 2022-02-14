package com.android.base.com.nasapictures.gui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import com.nasapictures.R
import com.nasapictures.base.BaseActivity
import com.nasapictures.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class MainActivity : BaseActivity() {
    override val layoutResId: Int = R.layout.activity_main
    override val bindingInflater: (LayoutInflater) -> ViewBinding = ActivityMainBinding::inflate
    override val binding: ActivityMainBinding get() = super.binding as ActivityMainBinding

}
package com.sensibol.android.base.com.nasapictures.gui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nasapictures.databinding.ImageBinding
import com.nasapictures.gui.model.ResponseDataItem
import com.sensibol.android.base.loadUrl
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject
import kotlin.properties.Delegates

@FragmentScoped
class ImageAdapter @Inject constructor() : RecyclerView.Adapter<ImageAdapter.ImageVH>() {

    internal var onImageClickListener: (ResponseDataItem) -> Unit = {}
    internal var ResponseData: List<ResponseDataItem> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    inner class ImageVH(private val binding: ImageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(responseItem: ResponseDataItem) {
            binding.ivCoverImage.loadUrl(responseItem.url)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ImageVH(
        ImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    ).apply {
        itemView.setOnClickListener {
            onImageClickListener(ResponseData[adapterPosition])
        }
    }

    override fun onBindViewHolder(holder: ImageVH, position: Int) =
        holder.bind(ResponseData[position])

    override fun getItemCount(): Int {
        return ResponseData.size
    }

}
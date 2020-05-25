package com.inconsciente.colectiv.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.inconsciente.colectiv.databinding.GridViewItemBinding
import com.inconsciente.colectiv.network.MarketingProperty

class PhotoGridAdapter : ListAdapter<MarketingProperty,
        PhotoGridAdapter.MarketingPropertyViewHolder>(DiffCallback) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PhotoGridAdapter.MarketingPropertyViewHolder {
        return MarketingPropertyViewHolder(GridViewItemBinding.inflate(
            LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(
        holder: PhotoGridAdapter.MarketingPropertyViewHolder,
        position: Int
    ) {
        val marketingProperty = getItem(position)
        holder.bind(marketingProperty)
    }
    companion object DiffCallback : DiffUtil.ItemCallback<MarketingProperty>() {
        override fun areItemsTheSame(
            oldItem: MarketingProperty,
            newItem: MarketingProperty
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: MarketingProperty,
            newItem: MarketingProperty
        ): Boolean {
            return oldItem.title == newItem.title
        }
    }
    class MarketingPropertyViewHolder(private var binding:
                                      GridViewItemBinding
    ):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(marketingProperty: MarketingProperty) {
            binding.property = marketingProperty
            binding.executePendingBindings()
        }
    }

}
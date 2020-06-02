package com.inconsciente.colectiv.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.inconsciente.colectiv.databinding.GridViewItemBinding
import com.inconsciente.colectiv.network.MessageProperty

class PhotoGridAdapter : ListAdapter<MessageProperty,
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
    companion object DiffCallback : DiffUtil.ItemCallback<MessageProperty>() {
        override fun areItemsTheSame(
            oldItem: MessageProperty,
            newItem: MessageProperty
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: MessageProperty,
            newItem: MessageProperty
        ): Boolean {
            return oldItem.title == newItem.title
        }
    }
    class MarketingPropertyViewHolder(private var binding:
                                      GridViewItemBinding
    ):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(messageProperty: MessageProperty) {
            binding.property = messageProperty
            binding.executePendingBindings()
        }
    }

}
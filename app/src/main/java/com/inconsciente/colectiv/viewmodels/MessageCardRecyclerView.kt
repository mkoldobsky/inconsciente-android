package com.inconsciente.colectiv.viewmodels

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.inconsciente.colectiv.R
import com.inconsciente.colectiv.network.ImageRequester
import com.inconsciente.colectiv.network.MessageProperty


/**
 * Adapter used to show a simple grid of products.
 */
class MessageCardRecyclerViewAdapter(private val messageList: List<MessageProperty>) :
    RecyclerView.Adapter<MessageCardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageCardViewHolder {
        val layoutView =
            LayoutInflater.from(parent.context).inflate(R.layout.message_grid_card, parent, false)
        return MessageCardViewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: MessageCardViewHolder, position: Int) {
        if (position < messageList.size) {
            val message = messageList[position]
            holder.messageTitle.text = message.title
            holder.messageDescription.text = message.description
            ImageRequester.setImageFromUrl(holder.messageImage, message.imageUrl)
        }
    }

    override fun getItemCount(): Int {
        if (messageList != null) {
            return messageList.size
        }
        return 0
    }
}
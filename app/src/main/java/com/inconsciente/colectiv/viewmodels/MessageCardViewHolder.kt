package com.inconsciente.colectiv.viewmodels


import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.toolbox.NetworkImageView
import com.inconsciente.colectiv.R

class MessageCardViewHolder(itemView: View)
    : RecyclerView.ViewHolder(itemView) {

    var messageImage: NetworkImageView = itemView.findViewById(R.id.message_image)
    var messageTitle: TextView = itemView.findViewById(R.id.message_title)
    var messageDescription: TextView = itemView.findViewById(R.id.message_description)
}
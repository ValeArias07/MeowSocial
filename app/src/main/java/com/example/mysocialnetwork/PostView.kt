package com.example.mysocialnetwork

import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mysocialnetwork.databinding.PostBinding

class PostView(itemView: View) : RecyclerView.ViewHolder(itemView) {
    ///State
    var post: Post? = null

    /// UI Components
    var nameText: TextView = itemView.findViewById(R.id.nameText)
    var descriptionText: TextView = itemView.findViewById(R.id.descriptionText)
    var namePost: TextView = itemView.findViewById(R.id.namePost)
}

package com.example.mysocialnetwork.recyclerModel

import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mysocialnetwork.R
import com.example.mysocialnetwork.model.Post

class PostView(itemView: View) : RecyclerView.ViewHolder(itemView) {
    ///State
    var post: Post? = null
    var uri: Uri?= null
    /// UI Components
    var nameText: TextView = itemView.findViewById(R.id.nameText)
    var descriptionText: TextView = itemView.findViewById(R.id.descriptionText)
    var namePost: TextView = itemView.findViewById(R.id.namePost)
    var datePost: TextView = itemView.findViewById(R.id.dateText)
    var locationText: TextView = itemView.findViewById(R.id.locationText)
    var postImageView: ImageView = itemView.findViewById(R.id.postImage)
}

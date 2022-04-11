package com.example.mysocialnetwork.recyclerModel

import android.gesture.GestureLibraries.fromFile
import android.graphics.drawable.Drawable
import android.graphics.drawable.Icon
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.mysocialnetwork.R
import com.example.mysocialnetwork.databinding.PostBinding
import com.example.mysocialnetwork.model.Post
import edu.co.icesi.MySocialNetwork.UtilDomi
import java.io.File

class PostAdapter : RecyclerView.Adapter<PostView>() {

    private lateinit var binding: PostBinding
    private val posts = ArrayList<Post>()

    fun addPost(post: Post){
        posts.add(post)
    }

    fun setArray(post: ArrayList<Post>){
        for(num in 0..post.size-1){
            posts.add(post.get(num))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostView {
        var inflater = LayoutInflater.from(parent.context)
        binding = PostBinding.inflate(inflater)
        val row = inflater.inflate(R.layout.post, parent, false)
        val postView = PostView(row)
        return postView
    }

    override fun onBindViewHolder(skeleton: PostView, position: Int) {
        val post = posts[position]
        skeleton.post = post
        skeleton.nameText.text = post.userName

        if(post.default == true){
            skeleton.userPic.setImageResource(R.drawable.profile)
        }else{
            skeleton.userPic.setImageURI(Uri.fromFile(File(post.userPic)))
        }
        skeleton.descriptionText.text = post.description
        skeleton.datePost.text = post.postDate
        skeleton.locationText.text = post.postCity
        skeleton.postImageView.setImageURI(Uri.fromFile(File(post.postImg)))
    }


    override fun getItemCount(): Int {
        return posts.size
    }
}
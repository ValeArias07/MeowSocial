package com.example.mysocialnetwork.recyclerModel

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.mysocialnetwork.R
import com.example.mysocialnetwork.databinding.PostBinding
import com.example.mysocialnetwork.model.Post

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
        //postView.listener = this
        return postView
    }

    override fun onBindViewHolder(skeleton: PostView, position: Int) {
        val post = posts[position]
        skeleton.post = post
        skeleton.nameText.text = post.userName
        skeleton.descriptionText.text = post.description
        //skeleton.namePost.text = post.postName
        skeleton.datePost.text = post.postDate
        skeleton.locationText.text = post.postCity

        //skeleton.uri = post.postImage
        //skeleton.postImageView = ImageView.setImageURI(post.postImage)
    }

    override fun getItemCount(): Int {
        return posts.size
    }
}
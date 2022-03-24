package com.example.mysocialnetwork

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mysocialnetwork.databinding.PostBinding

class PostAdapter : RecyclerView.Adapter<PostView>() {

    private lateinit var binding: PostBinding
    private val posts = ArrayList<Post>()

    fun addPost(post: Post){
        posts.add(post)
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
        skeleton.namePost.text = post.postName
    }

    override fun getItemCount(): Int {
        return posts.size
    }
}
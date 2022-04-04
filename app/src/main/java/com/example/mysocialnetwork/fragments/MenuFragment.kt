package com.example.mysocialnetwork.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mysocialnetwork.databinding.FragmentMenuBinding
import com.example.mysocialnetwork.model.Post
import com.example.mysocialnetwork.recyclerModel.PostAdapter


class MenuFragment : Fragment(), PostFragment.OnNewPostListener {
    private var _binding:  FragmentMenuBinding? = null
    private val binding get() = _binding!!

    private val adapter = PostAdapter()
    lateinit var postRecycler: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        _binding = FragmentMenuBinding.inflate(inflater, container, false)

        // Recrear el estado
        postRecycler = binding.postRecyclerView
        postRecycler.layoutManager = LinearLayoutManager(activity)
        postRecycler.adapter = adapter

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = MenuFragment()
    }

    override fun onDestroyView(){
        super.onDestroyView()
        _binding = null
    }

    override fun addPost(post: Post) {
        adapter.addPost(post)
    }

    fun setArray(posts: ArrayList<Post>){
        adapter.setArray(posts)
    }

}
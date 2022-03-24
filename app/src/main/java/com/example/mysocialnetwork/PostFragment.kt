package com.example.mysocialnetwork


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mysocialnetwork.databinding.FragmentPostBinding

class PostFragment(): Fragment() {

    var listener: OnNewPostListener? = null
    private var _binding: FragmentPostBinding? = null
    private val binding get() = _binding!!
    private lateinit var post: Post

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPostBinding.inflate(inflater, container, false)

        binding.pustItButton.setOnClickListener {

            post = Post(
                "Lorem Ipsum",
                binding.dscrptnText.text.toString(),
                binding.videoNameText.text.toString()
            )

            listener?.let {
                it.addPost(post)
            }
        }
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = PostFragment()
    }

    interface OnNewPostListener {
        fun addPost(post: Post)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null

    }
}
package com.example.mysocialnetwork.fragments

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.mysocialnetwork.R
import com.example.mysocialnetwork.databinding.FragmentPostBinding
import com.example.mysocialnetwork.databinding.SpinnerBinding
import com.example.mysocialnetwork.model.Post
import com.example.mysocialnetwork.model.User
import com.google.gson.Gson
import edu.co.icesi.MySocialNetwork.UtilDomi
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

private const val ARG_PARAM1 = "param1"
class PostFragment() : Fragment() {

    var listenerAdapter: OnNewPostListener? = null
    var listenerActivity: OnNewPostListener? = null
    private var _binding: FragmentPostBinding? = null
    private val binding get() = _binding!!
    private lateinit var currentUser: User
    private lateinit var file: File
    private lateinit var uriImg: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val gson = Gson()
            var json = it.getString(ARG_PARAM1).toString()
            currentUser = gson.fromJson(json, User::class.java)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        _binding = FragmentPostBinding.inflate(inflater, container, false)
        val bindingSpinner = SpinnerBinding.inflate(inflater, container, false)
        createSpinner()

        val galLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(), ::onGalleryResult)
        binding.galeryButton.setOnClickListener {
            val i = Intent(Intent.ACTION_GET_CONTENT)
            i.type = "image/*"
            galLauncher.launch(i)
        }

        binding.pustItButton.setOnClickListener {
            val dateLabel = configDateLabel()
            val city =binding.spinner.selectedItem.toString()+", Colombia"
            var post = Post(
                currentUser.name, binding.dscrptnText.text.toString(), binding.videoNameText.text.toString(), dateLabel, "", city
            )
            listenerAdapter?.let {
                it.addPost(post)
            }
            listenerActivity?.let{
                it.addPost(post)
            }
        }
        return binding.root
    }

    private fun createSpinner() {
        var list = resources.getStringArray(R.array.cities)
        val adapterSpinner = ArrayAdapter(requireContext(),
            android.R.layout.simple_spinner_item,
            list)

        adapterSpinner.setDropDownViewResource(
            android.R.layout.simple_dropdown_item_1line
        )
        binding.spinner.adapter = adapterSpinner
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun configDateLabel(): String {
        val formato = SimpleDateFormat(" d 'de' MMMM 'de' yyyy", Locale("es", "COL"))
        return formato.format(Date())
    }

    companion object {
        @JvmStatic
        fun newInstance(user: String?) =
            PostFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_PARAM1, user)
            }
        }
    }

    interface OnNewPostListener {
        fun addPost(post: Post)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun onGalleryResult(activityResult: ActivityResult) {
        val uri = activityResult.data?.data
        val path = UtilDomi.getPath(requireContext(), uri!!)
        val bitmap = BitmapFactory.decodeFile(path)
        binding.postImageView.setImageURI(uri)

    }
}


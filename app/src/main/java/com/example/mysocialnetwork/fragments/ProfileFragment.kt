package com.example.mysocialnetwork.fragments
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.mysocialnetwork.activities.MainActivity
import com.example.mysocialnetwork.activities.MenuActivity
import com.example.mysocialnetwork.databinding.FragmentProfileBinding
import com.example.mysocialnetwork.model.User
import com.google.gson.Gson

private const val ARG_PARAM1 = "param1"
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var currentUser: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val gson = Gson()
            var json = it.getString(ARG_PARAM1).toString()
            currentUser = gson.fromJson(json, User::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        binding.nameBoxProfile.setText(currentUser.name)

        binding.editProfileButton.setOnClickListener {
            currentUser.name = binding.nameBoxProfile.toString()
        }

        val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(), ::onResult)
        binding.closeSesionButton.setOnClickListener{
        val intent = Intent(context, MainActivity::class.java).apply {
        putExtra("currentUser", "404")
        }
        launcher.launch(intent)
        }

        return binding.root
    }

    private fun onResult(activityResult: ActivityResult?) {

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

    companion object {
        @JvmStatic
        fun newInstance(user: String?) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, user)
                }
            }
    }
}


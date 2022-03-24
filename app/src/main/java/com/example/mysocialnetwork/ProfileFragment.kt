package com.example.mysocialnetwork
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mysocialnetwork.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        binding.closeSesionButton.setOnClickListener{
            //val intent = Intent(, Login::class.java)
            //startActivity(intent)
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

    companion object {
        @JvmStatic
        fun newInstance() = ProfileFragment()
    }
}

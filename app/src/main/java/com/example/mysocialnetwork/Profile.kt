package com.example.mysocialnetwork

import android.app.Instrumentation
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.appcompat.app.AppCompatActivity
import com.example.mysocialnetwork.databinding.FragmentProfileBinding
import com.example.mysocialnetwork.databinding.MenuBinding

class Profile : AppCompatActivity() {

    private lateinit var binding: FragmentProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

    private fun onResult(activityResult: ActivityResult) {

    }

}
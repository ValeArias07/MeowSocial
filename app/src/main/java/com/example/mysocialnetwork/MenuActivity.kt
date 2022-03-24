package com.example.mysocialnetwork

import android.app.Instrumentation
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.fragment.app.Fragment
import com.example.mysocialnetwork.databinding.MenuBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: MenuBinding
    private lateinit var postFragment: PostFragment
    private lateinit var profileFragment: ProfileFragment
    private lateinit var menuFragment: MenuFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MenuBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        //// INVESTIGAR COMO PASAR INFORMACION ENTRE ACTIVIDAD Y FRAGMENTO
        var username = intent.extras?.getString("username")


        postFragment = PostFragment.newInstance()
        profileFragment = ProfileFragment.newInstance()
        menuFragment = MenuFragment.newInstance()

        postFragment.listener = menuFragment

        binding.mppNavigationView.setOnItemSelectedListener{ menuItem->
            if(menuItem.itemId== R.id.menuButton){
                showFragment(menuFragment)
            }else if(menuItem.itemId== R.id.postButton){
                showFragment(postFragment)
            }else if(menuItem.itemId== R.id.profileButton){
                showFragment(profileFragment)
            }
            true
        }
    }



     fun showFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentsLayout, fragment)
        transaction.commit()
    }
}
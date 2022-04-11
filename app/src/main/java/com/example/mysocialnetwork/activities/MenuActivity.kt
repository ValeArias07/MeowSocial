package com.example.mysocialnetwork.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.mysocialnetwork.R
import com.example.mysocialnetwork.databinding.MenuBinding

import com.example.mysocialnetwork.fragments.MenuFragment
import com.example.mysocialnetwork.fragments.PostFragment
import com.example.mysocialnetwork.fragments.ProfileFragment
import com.example.mysocialnetwork.fragments.ProfileFragment.OnUserChanges
import com.example.mysocialnetwork.model.Post
import com.example.mysocialnetwork.model.Posts
import com.example.mysocialnetwork.model.User
import com.example.mysocialnetwork.model.Users
import com.google.gson.Gson

class MenuActivity : AppCompatActivity(), PostFragment.OnNewPostListener, ProfileFragment.OnUserChanges  {

    private lateinit var binding: MenuBinding
    private lateinit var postFragment: PostFragment
    private lateinit var profileFragment: ProfileFragment
    private lateinit var menuFragment: MenuFragment
    private lateinit var posts: Posts
    private lateinit var user: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MenuBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        posts = Posts(ArrayList<Post>())
        user = intent.extras?.getString("currentUser").toString()

        menuFragment = MenuFragment.newInstance()
        postFragment = PostFragment.newInstance(user)
        profileFragment = ProfileFragment.newInstance(user)


        postFragment.listenerAdapter = menuFragment
        postFragment.listenerActivity = this
        profileFragment.listenerMenu = this
        profileFragment.listenerPost = postFragment
        loadPosts()
        showFragment(menuFragment)

        binding.mppNavigationView.setOnItemSelectedListener{ menuItem->
            if(menuItem.itemId== R.id.menuButton){
                showFragment(menuFragment)
                binding.postButton.show()
            }else if(menuItem.itemId== R.id.profileButton){
                showFragment(profileFragment)
                binding.postButton.hide()
            }
            true
        }

        binding.postButton.setOnClickListener{
            showFragment(postFragment)
            binding.postButton.hide()
        }
    }

    private fun loadPosts() {
        val gson = Gson()
        val sharedPref = getPreferences(MODE_PRIVATE)
        val postList= sharedPref.getString("posts", "404")

        if(postList!="404"){
            posts = gson.fromJson(sharedPref.getString("posts", "404"), Posts::class.java)
            menuFragment.setArray(posts.postList)
        }

    }

    fun showFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentsLayout, fragment)
        transaction.commit()
    }

    override fun addPost(post: Post) {
        val gson = Gson()
        val sharedPref = getPreferences(MODE_PRIVATE)
        posts.postList.add(post)
        sharedPref.edit()
            .putString("posts", gson.toJson(posts))
            .apply()
    }

    override fun changeUser(name: String) {
        val gson = Gson()
        val currentUser = gson.fromJson(user, User::class.java)
        currentUser.setingName(name)
        Log.e(">>>>", currentUser.name)
        user = gson.toJson(currentUser)
    }
}
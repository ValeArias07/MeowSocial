package com.example.mysocialnetwork.activities

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.mysocialnetwork.databinding.MainActivityBinding
import com.example.mysocialnetwork.model.User
import com.example.mysocialnetwork.model.Users
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainActivityBinding
    private lateinit var users: Users

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPref = getPreferences(MODE_PRIVATE)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(), ::onResult)

        var currentUser = intent.extras?.getString("currentUser")

        if(currentUser!="404" && isCurrentUser(sharedPref)){
            val intent = Intent(this, MenuActivity::class.java).apply{
                putExtra("currentUser", sharedPref.getString("currentUser",""))
            }
            launcher.launch(intent)

        }else{
            setCredentials(sharedPref);
            removeUser()
            binding.loginButton.setOnClickListener{
                val user = User(binding.userNameText.text.toString(), binding.passwordText.text.toString(),"")

                if(checkIdentity(sharedPref, user)) {
                    val intent = Intent(this, MenuActivity::class.java).apply{
                        putExtra("currentUser", sharedPref.getString("currentUser",""))
                    }
                    launcher.launch(intent)
                }
            }
        }
    }

    private fun removeUser(){
        val sharedPref = getPreferences(MODE_PRIVATE)
        sharedPref.edit()
            .remove("currentUser")
            .apply()
    }

    private fun isCurrentUser(sharedPref: SharedPreferences): Boolean{
        val current= sharedPref.getString("currentUser", "404")
        return current!="404"
    }

    private fun setCredentials(sharedPref: SharedPreferences) {
        var userList = ArrayList<User>()
        users = Users(userList)
        val gson = Gson()
        val userRaw= sharedPref.getString("users", "404")

        if(userRaw=="404"){
            users.userList.add(User("alfa@gmail.com","aplicacionesmoviles","Alfa Arias" ))
            users.userList.add(User("beta@gmail.com","aplicacionesmoviles","Beta Gomez" ))
            users.userList.add(User("vale","vale","Vale Gomez" ))
            sharedPref.edit()
                .putString("users", gson.toJson(users))
                .apply()
        }else{
            users = gson.fromJson(sharedPref.getString("users", "404"), Users::class.java)
            Log.e(" >>>>", "Si se anadio")
        }
    }

    private fun checkIdentity(sharedPref: SharedPreferences, user: User): Boolean {
        var state= false;
        val gson = Gson()

            for(num in 0..users.userList.size-1) {
                val userTest= users.userList.get(num)

                if(user.email == userTest.email && user.password == userTest.password){
                    sharedPref.edit().
                    putString("currentUser" , gson.toJson(userTest)).apply()
                    state = true
                }
            }
        if(!state){
            Toast.makeText(applicationContext, "Contrasena o correo erroneo", Toast.LENGTH_LONG).show()
        }
        return state;
    }

    private fun onResult(result: ActivityResult) {

    }
}





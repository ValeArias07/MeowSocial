package com.example.mysocialnetwork

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.mysocialnetwork.databinding.LoginBinding
import com.google.gson.Gson


class MainActivity : AppCompatActivity() {
    private lateinit var binding: LoginBinding
    private lateinit var username: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginBinding.inflate(layoutInflater)
        val sharedPref = getPreferences(MODE_PRIVATE)
        setContentView(binding.root)

        setCredentials(sharedPref);
        val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(), ::onResult)
        binding.loginButton.setOnClickListener{
            val user = User(binding.userNameText.text.toString(), binding.passwordText.text.toString(),"")
            val check=checkIdentity(sharedPref, user);

            if(check) {
                username = sharedPref.getString("currentUser","").toString()
                val intent = Intent(this, MenuActivity::class.java).apply{
                    putExtra("username", username)
                }
                launcher.launch(intent)
            }
        }
    }

    private fun setCredentials(sharedPref: SharedPreferences) {
        val gson = Gson()
        val userRaw= sharedPref.getString("user1", "404")

        if(userRaw=="404"){
            sharedPref.edit()
                .putString("user1" , gson.toJson(User("alfa@gmail.com","aplicacionesmoviles", "Alfa Arias")))
                .putString("user2" , gson.toJson(User("beta@gmail.com","aplicacionesmoviles", "Beta Gomez")))
                .apply()
        }else{
            Log.e(" >>>>", "Si se anadio")
        }

        }
    }

    private fun checkIdentity(sharedPref: SharedPreferences, user:User): Boolean {
        var state= false;
        val gson = Gson()
        val user1= gson.fromJson(sharedPref.getString("user1", "404"), User::class.java)
        val user2= gson.fromJson(sharedPref.getString("user2", "404"), User::class.java)

        if(user.email == user1.email && user.password == user1.password){
            sharedPref.edit().
            putString("currentUser" , user1.name).apply()

            state = true
        }else if( user.email == user2.email && user.password == user2.password){
            sharedPref.edit().
            putString("currentUser" , user2.name).apply()
            state = true
        } else {
            Log.e(">>>>", "ERROR EN EL USUARIO")
            ///Toast.makeText(this, "Error en el usuario", Toast.LENGTH_LONG).show()
        }
        return state;
    }

    private fun onResult(activityResult: ActivityResult) {

    }




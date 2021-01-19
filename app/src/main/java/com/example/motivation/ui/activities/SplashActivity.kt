package com.example.motivation.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.motivation.R
import com.example.motivation.infra.MotivationConstants
import com.example.motivation.infra.SecurityPrefs
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var securityPrefs: SecurityPrefs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        securityPrefs = SecurityPrefs(this)

        redirectIfUserLoggedIn()

        supportActionBar?.hide()
        bt_save.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id){
            bt_save.id -> handleSave()

            else -> return
        }
    }

    private fun navigateToMain(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun handleSave(){
        val name = et_username.text.toString()

        if(name.isEmpty()){
            val toast = Toast.makeText(this, "Informe seu nome", Toast.LENGTH_SHORT)
            toast.show()
            return
        }

        securityPrefs.storeString(MotivationConstants.KEY.usernameKey, name)
        navigateToMain()
    }

    private fun redirectIfUserLoggedIn(){
        val userExists = securityPrefs.getString("@motivation:username")

        if(userExists.isNotEmpty()){
            navigateToMain()
        }
    }
}
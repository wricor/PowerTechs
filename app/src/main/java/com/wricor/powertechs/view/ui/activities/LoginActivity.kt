package com.wricor.powertechs.view.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.wricor.powertechs.R

class LoginActivity : AppCompatActivity() {
    lateinit var loginbutton: Button
    lateinit var signupbutton: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginbutton = findViewById(R.id.btn_login_ingresar)
        signupbutton = findViewById(R.id.txt_login_registro)
        loginbutton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        signupbutton.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }
}
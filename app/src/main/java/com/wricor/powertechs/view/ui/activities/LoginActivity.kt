package com.wricor.powertechs.view.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.wricor.powertechs.R

class LoginActivity : AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth

    lateinit var btn_login_ingresar: Button
    lateinit var txt_login_registro: TextView
    lateinit var txt_login_recuperar: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //Firebase
        firebaseAuth = Firebase.auth

        val txt_login_correo: EditText = findViewById(R.id.txt_login_correo)
        val txt_login_contrasena: EditText = findViewById(R.id.txt_login_contrasena)

        // Funcionalidad botones
        btn_login_ingresar = findViewById(R.id.btn_recovery_restore)
        txt_login_registro = findViewById(R.id.txt_login_registro)
        txt_login_recuperar = findViewById(R.id.txt_login_recuperar)
        btn_login_ingresar.setOnClickListener {
            login(txt_login_correo.text.toString(), txt_login_contrasena.text.toString())
            //startActivity(Intent(this, MainActivity::class.java))
        }
        txt_login_registro.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
        txt_login_recuperar.setOnClickListener {
            startActivity(Intent(this, RecoverActivity::class.java))
        }
    }

    private fun login(txt_login_correo: String, txt_login_contrasena: String) {
        firebaseAuth.signInWithEmailAndPassword(txt_login_correo, txt_login_contrasena)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = firebaseAuth.currentUser
                    Toast.makeText(baseContext, /*user?.uid.toString()*/"Ingreso exitoso " + user?.email.toString(), Toast.LENGTH_SHORT).show()
                    val i = Intent(this, MainActivity::class.java)
                    startActivity(i)
                } else {
                    Toast.makeText(baseContext, "Error en el ingreso", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
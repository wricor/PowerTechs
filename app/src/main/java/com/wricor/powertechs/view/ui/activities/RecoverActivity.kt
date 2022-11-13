package com.wricor.powertechs.view.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.wricor.powertechs.R

class RecoverActivity : AppCompatActivity() {
    lateinit var btn_recovery_restore: Button
    // Firebase
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recover)

        // Firebase
        firebaseAuth = Firebase.auth

        // Funcionalidad de los botones
        btn_recovery_restore = findViewById(R.id.btn_recovery_restore)
        val txt_recover_correo = findViewById<TextView>(R.id.txt_recover_correo)
        btn_recovery_restore.setOnClickListener {
            cambiocontrasena(txt_recover_correo.text.toString())
            //startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun cambiocontrasena(txt_recover_correo: String) {
        firebaseAuth.sendPasswordResetEmail(txt_recover_correo)
            .addOnCompleteListener(this) {
                task -> if (task.isSuccessful) {
                    Toast.makeText(baseContext, "Se envió un correo para cambiar su contraseña", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, LoginActivity::class.java))
            } else {
                Toast.makeText(baseContext, "Error al tratar de realizar el envío del correo", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
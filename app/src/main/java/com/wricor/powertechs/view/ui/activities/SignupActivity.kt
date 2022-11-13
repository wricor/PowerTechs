package com.wricor.powertechs.view.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.wricor.powertechs.R

class SignupActivity : AppCompatActivity() {
    lateinit var btn_registro_enviar: Button
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        //Firebase
        firebaseAuth = Firebase.auth

        btn_registro_enviar = findViewById(R.id.btn_registro_enviar)
        val txt_registro_nombres = findViewById<EditText>(R.id.txt_registro_nombres)
        val txt_registro_apellidos = findViewById<EditText>(R.id.txt_registro_apellidos)
        val spn_registro_tipodoc = findViewById<Spinner>(R.id.spn_registro_tipodoc)
        val txt_registro_documento = findViewById<EditText>(R.id.txt_registro_documento)
        val txt_registro_correo = findViewById<EditText>(R.id.txt_registro_correo)
        val txt_registro_direccion = findViewById<EditText>(R.id.txt_registro_direccion)
        val txt_registro_celular = findViewById<EditText>(R.id.txt_registro_celular)
        val txt_registro_contrasena1 = findViewById<EditText>(R.id.txt_registro_contrasena1)
        val txt_registro_contrasena2 = findViewById<EditText>(R.id.txt_registro_contrasena2)
        btn_registro_enviar.setOnClickListener {
            // Verificar los campos
            //if (!txt_registro_contrasena1.equals(txt_registro_contrasena2)) {
            //    Toast.makeText(baseContext, "Contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            //}
            crearcuenta(txt_registro_correo.text.toString(), txt_registro_contrasena1.text.toString())
        }
    }

    private fun crearcuenta(email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                Task -> if (Task.isSuccessful) {
                    Toast.makeText(baseContext, "Cuenta creada", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity::class.java))
                } else {
                    Toast.makeText(baseContext, "Error en la creación", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
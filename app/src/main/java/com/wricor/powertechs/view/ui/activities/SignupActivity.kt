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
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.wricor.powertechs.R

class SignupActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    // Referencia para otros datos de registro
    private lateinit var bdReference: DatabaseReference
    private lateinit var database: FirebaseDatabase

    private lateinit var txtRegistroNombres: EditText
    private lateinit var txtRegistroApellidos: EditText
    private lateinit var spnRegistroTipodoc: Spinner
    private lateinit var txtRegistroDocumento: EditText
    private lateinit var txtRegistroCorreo: EditText
    private lateinit var txtRegistroDireccion: EditText
    private lateinit var txtRegistroCelular: EditText
    private lateinit var txtRegistroContrasena1: EditText
    private lateinit var txtRegistroContrasena2: EditText
    private lateinit var btnRegistroEnviar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        //Firebase
        firebaseAuth = Firebase.auth
        database = FirebaseDatabase.getInstance()
        bdReference = database.reference.child("UserInfo")

        txtRegistroNombres = findViewById(R.id.txt_registro_nombres)
        txtRegistroApellidos = findViewById(R.id.txt_registro_apellidos)
        spnRegistroTipodoc = findViewById(R.id.spn_registro_tipodoc)
        txtRegistroDocumento = findViewById(R.id.txt_registro_documento)
        txtRegistroCorreo = findViewById(R.id.txt_registro_correo)
        txtRegistroDireccion = findViewById(R.id.txt_registro_direccion)
        txtRegistroCelular = findViewById(R.id.txt_registro_celular)
        txtRegistroContrasena1 = findViewById(R.id.txt_registro_contrasena1)
        txtRegistroContrasena2 = findViewById(R.id.txt_registro_contrasena2)
        btnRegistroEnviar = findViewById(R.id.btn_registro_enviar)
        btnRegistroEnviar.setOnClickListener {
            // Verificar los campos
            val pass1 = txtRegistroContrasena1.text.toString()
            val pass2 = txtRegistroContrasena2.text.toString()
            if (pass1.equals(pass2)) {
                crearcuenta(txtRegistroCorreo.text.toString(), txtRegistroContrasena1.text.toString())
            } else {
                Toast.makeText(baseContext, "Error: Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun crearcuenta(email: String, password: String) {
        val txtRegistroNombres: String = txtRegistroNombres.text.toString()
        val txtRegistroApellidos: String = txtRegistroApellidos.text.toString()
        val spnRegistroTipodoc: String = spnRegistroTipodoc.selectedItem.toString()
        val txtRegistroDocumento: String = txtRegistroDocumento.text.toString()
        val txtRegistroDireccion: String = txtRegistroDireccion.text.toString()
        val txtRegistroCelular: String = txtRegistroCelular.text.toString()
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                Task -> if (Task.isSuccessful) {
                    val user = firebaseAuth.currentUser
                    val userdb = bdReference.child(user?.uid.toString())
                    userdb.child("nombres").setValue(txtRegistroNombres)
                    userdb.child("apellidos").setValue(txtRegistroApellidos)
                    userdb.child("tipodoc").setValue(spnRegistroTipodoc)
                    userdb.child("documento").setValue(txtRegistroDocumento)
                    userdb.child("direccion").setValue(txtRegistroDireccion)
                    userdb.child("celular").setValue(txtRegistroCelular)
                    Toast.makeText(baseContext, "Cuenta creada", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity::class.java))
                } else {
                    Toast.makeText(baseContext, "Error en la creación", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
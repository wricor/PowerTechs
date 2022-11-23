package com.wricor.powertechs.view.ui.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.wricor.powertechs.R
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

class CommentFragment : Fragment() {
    lateinit var firebaseAuth: FirebaseAuth
    val database: FirebaseFirestore = FirebaseFirestore.getInstance()

    lateinit var txtComentarioEmail: EditText
    lateinit var txtComentarioTexto: EditText
    lateinit var btnComentarioEnviar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAuth = Firebase.auth
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_comment, container, false)

        txtComentarioEmail = view.findViewById(R.id.txt_comentario_email)
        txtComentarioTexto = view.findViewById(R.id.txt_comentario_texto)

        btnComentarioEnviar = view.findViewById(R.id.btn_comentario_enviar)
        btnComentarioEnviar.setOnClickListener {
            val currentDate = LocalDateTime.now(ZoneId.of("America/Bogota"))
            val date: String = currentDate.toString()
            val email: String = txtComentarioEmail.text.toString()
            val comment: String = txtComentarioTexto.text.toString()
            val dataComment = hashMapOf(
                "date" to date,
                "email" to email,
                "comment" to comment
            )
            database.collection("comentarios")
                .document(date)
                .set(dataComment)
                .addOnSuccessListener {
                    Toast.makeText(context, "Comentario enviado", Toast.LENGTH_SHORT).show()
                    txtComentarioEmail.setText("")
                    txtComentarioTexto.setText("")
                }
        }
        return view
    }
}
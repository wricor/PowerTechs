package com.wricor.powertechs.view.ui.fragments

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.wricor.powertechs.R
import de.hdodenhof.circleimageview.CircleImageView

class ProfileFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val btnProfileCamera = view.findViewById<Button>(R.id.btn_profile_camera)
        val btnProfileGalery = view.findViewById<Button>(R.id.btn_profile_galery)
        // Edición de perfil
        val txtProfileNombres = view.findViewById<EditText>(R.id.txt_profile_nombres)
        val txtProfileApellidos = view.findViewById<EditText>(R.id.txt_profile_apellidos)
        //val spnProfileTipodoc = view.findViewById<EditText>(R.id.spn_profile_tipodoc)
        val txtProfileDocumento = view.findViewById<EditText>(R.id.txt_profile_documento)
        val txtProfileCorreo = view.findViewById<EditText>(R.id.txt_profile_correo)
        val txtProfileDireccion = view.findViewById<EditText>(R.id.txt_profile_direccion)
        val txtProfileCelular = view.findViewById<EditText>(R.id.txt_profile_celular)
        val btnProfileEditar = view.findViewById<Button>(R.id.btn_profile_editar)

        btnProfileCamera.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, 123)
        }
        btnProfileGalery.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 456)
        }

        // Edición de perfil
        txtProfileNombres.isEnabled = false
        txtProfileApellidos.isEnabled = false
        //spnProfileTipodoc.isEnabled = false
        txtProfileDocumento.isEnabled = false
        txtProfileCorreo.isEnabled = false
        txtProfileDireccion.isEnabled = false
        txtProfileCelular.isEnabled = false
        //Toast.makeText(requireContext(), "Enabled false", Toast.LENGTH_SHORT).show()
        btnProfileEditar.setOnClickListener {
            if (txtProfileNombres.isEnabled == false) {
                txtProfileNombres.isEnabled = true
                txtProfileApellidos.isEnabled = true
                //spnProfileTipodoc.isEnabled = true
                txtProfileDocumento.isEnabled = true
                txtProfileCorreo.isEnabled = true
                txtProfileDireccion.isEnabled = true
                txtProfileCelular.isEnabled = true
                //Toast.makeText(requireContext(), "Enabled true", Toast.LENGTH_SHORT).show()
            } else if (txtProfileNombres.isEnabled == true) {
                txtProfileNombres.isEnabled = false
                txtProfileApellidos.isEnabled = false
                //spnProfileTipodoc.isEnabled = false
                txtProfileDocumento.isEnabled = false
                txtProfileCorreo.isEnabled = false
                txtProfileDireccion.isEnabled = false
                txtProfileCelular.isEnabled = false
                //Toast.makeText(requireContext(), "Enabled false", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val imageView = view?.findViewById<CircleImageView>(R.id.img_profile)
        if (requestCode == 123) {
            var bitmap = data?.extras?.get("data") as Bitmap
            imageView?.setImageBitmap(bitmap)
        } else if (requestCode == 456) {
            imageView?.setImageURI(data?.data)
        }
    }
}
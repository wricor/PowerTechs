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
import com.wricor.powertechs.R
import de.hdodenhof.circleimageview.CircleImageView

class ProfileFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val btn_profile_camera = view.findViewById<Button>(R.id.btn_profile_camera)
        val btn_profile_galery = view.findViewById<Button>(R.id.btn_profile_galery)

        btn_profile_camera.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, 123)
        }
        btn_profile_galery.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 456)
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
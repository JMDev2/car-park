package com.example.main.ui.profile

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.ekenya.rnd.common.Constants.REQUEST_IMAGE_PICK
import com.ekenya.rnd.common.abstractions.BaseDaggerFragment
import com.ekenya.rnd.common.model.ImageData
import com.example.main.R
import com.example.main.databinding.FragmentOpenCameraBinding
import java.util.jar.Manifest

class OpenCameraFragment : BaseDaggerFragment() {
    private lateinit var binding: FragmentOpenCameraBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOpenCameraBinding.inflate(layoutInflater, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.selectCameraImg.setOnClickListener {
            //openCamera()
        }
        binding.galeryImg.setOnClickListener {
            openGlarely()
        }
    }

//    private fun openCamera() {
//        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
//            // Check if there's a camera app to handle the intent
//            takePictureIntent.resolveActivity(requireContext().packageManager)?.also {
//                startActivityForResult(takePictureIntent, ProfileFragment.REQUEST_IMAGE_CAPTURE)
//            }
//        }
//    }


    private fun openGlarely() {
        // Create an intent to pick an image from the gallery
//        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
//        startActivityForResult(intent, REQUEST_IMAGE_PICK)
    }


//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == REQUEST_IMAGE_PICK && resultCode == Activity.RESULT_OK) {
//            // Image picked successfully, get the image URI
//            val imageUri: Uri? = data?.data
//
//            if (imageUri != null) {
//                // Create a bundle to pass the image URI
//                val bundle = Bundle()
//                bundle.putString("imageUri", imageUri.toString())
//
//                // Navigate to the ProfileFragment and pass the bundle as arguments
//                findNavController().navigate(R.id.profileFragment, bundle)
//            }
//        }
//    }


}
package com.example.cameraactivity.ui.activities

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.cameraactivity.databinding.ActivityFullPhotoBinding
import java.io.File

class FullPhotoActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityFullPhotoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityFullPhotoBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        supportActionBar?.hide()

        val imageLocation = intent.getStringExtra("album_location")
        imageLocation?.let {
            val imageFile = File(it)
            val uri = Uri.fromFile(imageFile)
            Glide.with(this)
                .load(uri)
                .into(viewBinding.fullImage)
        }
    }
}
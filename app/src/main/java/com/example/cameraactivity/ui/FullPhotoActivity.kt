package com.example.cameraactivity.ui

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.cameraactivity.R
import java.io.File

class FullPhotoActivity : AppCompatActivity() {

    private lateinit var fullPhoto: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_photo)

        supportActionBar?.hide()
        initViews()

        val imageLocation = intent.getStringExtra("album_location")
        imageLocation?.let {
            val imageFile = File(it)
            val uri = Uri.fromFile(imageFile)
            Glide.with(this)
                .load(uri)
                .into(fullPhoto)
        }
    }

    private fun initViews() {
        fullPhoto = findViewById(R.id.full_image)
    }
}
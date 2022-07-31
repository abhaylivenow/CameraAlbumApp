package com.example.cameraactivity.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.cameraactivity.R

class MainActivity : AppCompatActivity() {

    private lateinit var btnCamera: Button
    private lateinit var btnAlbum: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

        btnCamera.setOnClickListener {
            startActivity(
                Intent(this, CameraActivity::class.java)
            )
        }

        btnAlbum.setOnClickListener {
            startActivity(
                Intent(this, AlbumActivity::class.java)
            )
        }
    }

    private fun initViews() {
        btnCamera = findViewById(R.id.btn_camera)
        btnAlbum = findViewById(R.id.btn_album)
    }
}
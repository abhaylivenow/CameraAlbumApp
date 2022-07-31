package com.example.cameraactivity.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cameraactivity.R
import com.example.cameraactivity.adapters.PhotoAdapter
import com.example.cameraactivity.db.PhotoDatabase
import com.example.cameraactivity.model.PhotoModel
import com.example.cameraactivity.repo.PhotoRepo
import com.example.cameraactivity.ui.vm.AlbumActivityViewModel
import com.example.cameraactivity.ui.vm.PhotoActivityViewModel
import com.example.cameraactivity.ui.vmFactory.AlbumActivityVMFactory
import com.example.cameraactivity.ui.vmFactory.PhotoActivityVMFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PhotoActivity : AppCompatActivity() {

    private lateinit var rv: RecyclerView
    private var listOfPhoto = mutableListOf<PhotoModel>()
    private lateinit var photoAdapter: PhotoAdapter

    private lateinit var viewModel: PhotoActivityViewModel
    private lateinit var photoDatabase: PhotoDatabase
    private lateinit var repo: PhotoRepo
    private lateinit var vmFactory: PhotoActivityVMFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)

        rv = findViewById(R.id.photo_rv)
        supportActionBar?.hide()
        initResources()

        val currentAlbumNumber = intent.getStringExtra("album_number")

        Toast.makeText(this,currentAlbumNumber,Toast.LENGTH_SHORT).show()

        currentAlbumNumber?.let {
            viewModel.getAllPhoto(it)
        }

        viewModel.getPhotos.observe(this) {
            listOfPhoto.addAll(it)
            photoAdapter = PhotoAdapter(listOfPhoto,this)
            rv.layoutManager = GridLayoutManager(this@PhotoActivity,3)
            rv.adapter = photoAdapter
        }
    }

    private fun initResources() {
        photoDatabase = PhotoDatabase(this)
        repo = PhotoRepo(photoDatabase)
        vmFactory = PhotoActivityVMFactory(repo)
        viewModel = ViewModelProvider(this,vmFactory)[PhotoActivityViewModel::class.java]
    }
}
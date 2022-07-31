package com.example.cameraactivity.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cameraactivity.adapters.PhotoAdapter
import com.example.cameraactivity.databinding.ActivityPhotoBinding
import com.example.cameraactivity.db.PhotoDatabase
import com.example.cameraactivity.model.PhotoModel
import com.example.cameraactivity.repo.PhotoRepo
import com.example.cameraactivity.ui.vm.PhotoActivityViewModel
import com.example.cameraactivity.ui.vmFactory.PhotoActivityVMFactory

class PhotoActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityPhotoBinding
    private var listOfPhoto = mutableListOf<PhotoModel>()
    private lateinit var photoAdapter: PhotoAdapter

    private lateinit var viewModel: PhotoActivityViewModel
    private lateinit var photoDatabase: PhotoDatabase
    private lateinit var repo: PhotoRepo
    private lateinit var vmFactory: PhotoActivityVMFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityPhotoBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        supportActionBar?.hide()

        val currentAlbumNumber = intent.getStringExtra("album_number")
        currentAlbumNumber?.let {
            initResources(it)
        }

        viewModel.photoLiveData.observe(this) {
            listOfPhoto.addAll(it)
            photoAdapter = PhotoAdapter(listOfPhoto,this)
            viewBinding.apply {
                photoRv.layoutManager = GridLayoutManager(this@PhotoActivity,3)
                photoRv.adapter = photoAdapter
            }
        }
    }

    private fun initResources(currentAlbumNumber: String) {
        photoDatabase = PhotoDatabase(this)
        repo = PhotoRepo(photoDatabase)
        vmFactory = PhotoActivityVMFactory(repo, currentAlbumNumber)
        viewModel = ViewModelProvider(this,vmFactory)[PhotoActivityViewModel::class.java]
    }
}
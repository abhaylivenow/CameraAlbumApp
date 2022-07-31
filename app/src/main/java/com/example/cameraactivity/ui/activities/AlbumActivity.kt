package com.example.cameraactivity.ui.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cameraactivity.adapters.AlbumAdapter
import com.example.cameraactivity.databinding.ActivityAlbumBinding
import com.example.cameraactivity.db.PhotoDatabase
import com.example.cameraactivity.model.AlbumModel
import com.example.cameraactivity.repo.PhotoRepo
import com.example.cameraactivity.ui.vm.AlbumActivityViewModel
import com.example.cameraactivity.ui.vmFactory.AlbumActivityVMFactory

class AlbumActivity : AppCompatActivity() {

    private lateinit var viewModel: AlbumActivityViewModel
    private lateinit var photoDatabase: PhotoDatabase
    private lateinit var repo: PhotoRepo
    private lateinit var vmFactory: AlbumActivityVMFactory
    private lateinit var viewBinding: ActivityAlbumBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityAlbumBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        photoDatabase = PhotoDatabase(this)
        repo = PhotoRepo(photoDatabase)
        vmFactory = AlbumActivityVMFactory(repo)
        viewModel = ViewModelProvider(this, vmFactory)[AlbumActivityViewModel::class.java]

        val totalAlbum = getCurrentAlbumNumber() - 1
        val listOfAlbum = mutableListOf<AlbumModel>()

        for (i in 1..totalAlbum) {
            listOfAlbum.add(AlbumModel(i.toString()))
        }

        viewBinding.apply {
            albumRv.adapter = AlbumAdapter(listOfAlbum, this@AlbumActivity)
            albumRv.layoutManager = GridLayoutManager(this@AlbumActivity, 2)
        }
    }

    private fun getCurrentAlbumNumber(): Int {
        val pref = getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        return pref.getInt("album_number", 0)
    }
}
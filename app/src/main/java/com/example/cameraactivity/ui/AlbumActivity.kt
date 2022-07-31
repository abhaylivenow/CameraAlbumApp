package com.example.cameraactivity.ui

import android.app.ActionBar
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cameraactivity.R
import com.example.cameraactivity.adapters.AlbumAdapter
import com.example.cameraactivity.db.PhotoDatabase
import com.example.cameraactivity.model.AlbumModel
import com.example.cameraactivity.repo.PhotoRepo
import com.example.cameraactivity.ui.vm.AlbumActivityViewModel
import com.example.cameraactivity.ui.vmFactory.AlbumActivityVMFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AlbumActivity : AppCompatActivity() {

    private lateinit var viewModel: AlbumActivityViewModel
    private lateinit var photoDatabase: PhotoDatabase
    private lateinit var repo: PhotoRepo
    private lateinit var vmFactory: AlbumActivityVMFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)

        photoDatabase = PhotoDatabase(this)
        repo = PhotoRepo(photoDatabase)
        vmFactory = AlbumActivityVMFactory(repo)
        viewModel = ViewModelProvider(this,vmFactory)[AlbumActivityViewModel::class.java]
        val rv = findViewById<RecyclerView>(R.id.album_rv)

        val totalAlbum = getCurrentAlbumNumber()-1
        val listOfAlbum = mutableListOf<AlbumModel>()

        for(i in 1..totalAlbum) {
            listOfAlbum.add(AlbumModel(i.toString()))
        }

        rv.adapter = AlbumAdapter(listOfAlbum,this)
        rv.layoutManager = GridLayoutManager(this,2)

//        CoroutineScope(Dispatchers.Main).launch {
//            viewModel.getAllPhoto().observe(this@AlbumActivity) {
//                Log.i("here11", "$it")
//            }
//        }
//        CoroutineScope(Dispatchers.Main).launch {
//            viewModel.deleteAllPhotos()
//        }
    }
    private fun getCurrentAlbumNumber(): Int {
        val pref = getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        return pref.getInt("album_number",0)
    }
}
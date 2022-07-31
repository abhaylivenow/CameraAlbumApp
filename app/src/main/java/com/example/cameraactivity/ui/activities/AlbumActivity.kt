package com.example.cameraactivity.ui.activities

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.IntentCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cameraactivity.adapters.AlbumAdapter
import com.example.cameraactivity.databinding.ActivityAlbumBinding
import com.example.cameraactivity.model.AlbumModel


class AlbumActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityAlbumBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityAlbumBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        supportActionBar?.hide()

        val totalAlbum = getCurrentAlbumNumber() - 1

        val listOfAlbum = mutableListOf<AlbumModel>()

        for (i in 1..totalAlbum) {
            listOfAlbum.add(AlbumModel(i.toString()))
        }

        if (listOfAlbum.size == 0) {
            viewBinding.emptyAlbumText.visibility = View.VISIBLE
        } else {
            viewBinding.emptyAlbumText.visibility = View.GONE
        }

        viewBinding.apply {
            albumRv.adapter = AlbumAdapter(listOfAlbum, this@AlbumActivity)
            albumRv.layoutManager = GridLayoutManager(this@AlbumActivity, 3)
        }

        viewBinding.btnGoToCamera.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            intent.addFlags(FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(
                intent
            )
        }
    }

    private fun getCurrentAlbumNumber(): Int {
        val pref = getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        return pref.getInt("album_number", 0)
    }
}
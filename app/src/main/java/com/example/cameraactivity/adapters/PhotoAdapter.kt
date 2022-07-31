package com.example.cameraactivity.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cameraactivity.R
import com.example.cameraactivity.model.PhotoModel
import com.example.cameraactivity.ui.activities.FullPhotoActivity
import java.io.File

class PhotoAdapter(
    private val listOfPhoto: List<PhotoModel>,
    private val context: Context
) : RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val photo = itemView.findViewById<ImageView>(R.id.image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.photo_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val photo = listOfPhoto[position]
        // need to remove first few char from location string to make it useful
        val actualLocation = photo.location.substring(7)
        val imageFile = File(actualLocation)
        val uri = Uri.fromFile(imageFile)
        Glide.with(context)
            .load(uri)
            .into(holder.photo)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, FullPhotoActivity::class.java)
            intent.putExtra("album_location", actualLocation)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return listOfPhoto.size
    }
}
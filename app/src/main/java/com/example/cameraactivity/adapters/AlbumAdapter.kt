package com.example.cameraactivity.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cameraactivity.R
import com.example.cameraactivity.model.AlbumModel
import com.example.cameraactivity.ui.PhotoActivity

class AlbumAdapter(
    private val listOfAlbum: List<AlbumModel>,
    private val context: Context
) : RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val albumName = itemView.findViewById<TextView>(R.id.text_album_number)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.album_layout,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val album = listOfAlbum[position]
        holder.albumName.text = "Album: ${album.albumNumber}"

        holder.itemView.setOnClickListener {
            val intent = Intent(context,PhotoActivity::class.java)
            intent.putExtra("album_number",album.albumNumber)
            context.startActivity(
                intent
            )
        }
    }

    override fun getItemCount(): Int {
        return listOfAlbum.size
    }
}
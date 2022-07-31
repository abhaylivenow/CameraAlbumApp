package com.example.cameraactivity.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photo_table")
data class PhotoModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val location: String,
    val timeStamp: String,
    val albumName: String
)
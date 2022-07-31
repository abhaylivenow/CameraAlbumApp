package com.example.cameraactivity.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photo_table")
data class PhotoModel(
    val location: String,
    @PrimaryKey
    val timeStamp: String,
    val albumName: String
)
package com.example.cameraactivity.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.cameraactivity.model.PhotoModel

@Dao
interface PhotoDao {
    @Insert
    suspend fun insertPhoto(photo: PhotoModel)

    @Query("SELECT * FROM photo_table WHERE albumName = :albumName")
    fun getPhotoByAlbum(albumName: String) : List<PhotoModel>

}
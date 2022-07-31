package com.example.cameraactivity.repo

import androidx.lifecycle.LiveData
import com.example.cameraactivity.db.PhotoDatabase
import com.example.cameraactivity.model.PhotoModel

class PhotoRepo(
    private val photoDatabase: PhotoDatabase
) {
    suspend fun insertPhoto(photoModel: PhotoModel) = photoDatabase.getPhotoDao().insertPhoto(photoModel)

    suspend fun getAllPhotos() : List<PhotoModel> = photoDatabase.getPhotoDao().getAllPhotos()

    suspend fun deleteAllPhoto() = photoDatabase.getPhotoDao().deleteAllPhoto()

    suspend fun getPhotoByAlbum(albumName: String) : List<PhotoModel> = photoDatabase.getPhotoDao().getPhotoByAlbum(albumName)
}
package com.example.cameraactivity.ui.vm

import androidx.lifecycle.ViewModel
import com.example.cameraactivity.repo.PhotoRepo

class AlbumActivityViewModel(
    private val repo: PhotoRepo
) : ViewModel() {
    suspend fun getAllPhoto() = repo.getAllPhotos()

    suspend fun deleteAllPhotos() = repo.getAllPhotos()

//    private fun getData() {
//        viewModelScope.launch(Dispatchers.IO) {
//            _liveData.postValue(
//                repo.getAllPhotos()
//            )
//        }
//    }

}
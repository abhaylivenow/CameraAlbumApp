package com.example.cameraactivity.ui.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cameraactivity.model.PhotoModel
import com.example.cameraactivity.repo.PhotoRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PhotoActivityViewModel(
    private val repo: PhotoRepo
) : ViewModel() {
    //suspend fun getAllPhoto() = repo.getAllPhotos()
    val getPhotos: MutableLiveData<List<PhotoModel>> = MutableLiveData()

//    init {
//        getAllPhoto()
//    }

    fun getAllPhoto(albumNumber: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getPhotos.postValue(repo.getPhotoByAlbum(albumNumber))
        }
    }
}
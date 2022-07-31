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
    private val repo: PhotoRepo,
    private val albumNumber: String
) : ViewModel() {
    private val _photoLiveData: MutableLiveData<List<PhotoModel>> = MutableLiveData()
    val photoLiveData: LiveData<List<PhotoModel>> = _photoLiveData

    init {
        getAllPhoto()
    }

    private fun getAllPhoto() {
        viewModelScope.launch(Dispatchers.IO) {
            _photoLiveData.postValue(repo.getPhotoByAlbum(albumNumber))
        }
    }
}
package com.example.cameraactivity.ui.vm

import androidx.lifecycle.ViewModel
import com.example.cameraactivity.model.PhotoModel
import com.example.cameraactivity.repo.PhotoRepo

class CameraActivityViewModel(
    private val repo: PhotoRepo
): ViewModel() {
    suspend fun insertPhoto(photo: PhotoModel) = repo.insertPhoto(photo)
}
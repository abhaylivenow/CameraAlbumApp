package com.example.cameraactivity.ui.vmFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cameraactivity.repo.PhotoRepo
import com.example.cameraactivity.ui.vm.AlbumActivityViewModel
import com.example.cameraactivity.ui.vm.CameraActivityViewModel

class AlbumActivityVMFactory(
    private val repo: PhotoRepo
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AlbumActivityViewModel(repo) as T
    }
}
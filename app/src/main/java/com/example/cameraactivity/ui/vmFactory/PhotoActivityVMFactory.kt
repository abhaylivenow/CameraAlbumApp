package com.example.cameraactivity.ui.vmFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cameraactivity.repo.PhotoRepo
import com.example.cameraactivity.ui.vm.PhotoActivityViewModel

class PhotoActivityVMFactory(
    private val repo: PhotoRepo
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PhotoActivityViewModel(repo) as T
    }
}
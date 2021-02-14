package com.example.ejercicioapigatos

import kotlinx.coroutines.*
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    suspend fun getApiResults() : MutableList<CatsFacts>? {
        delay(5000)
        return DownloadManager.downloadApiResults()
    }

}
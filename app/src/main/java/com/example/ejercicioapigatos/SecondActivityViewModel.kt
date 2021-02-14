package com.example.ejercicioapigatos

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay

class SecondActivityViewModel : ViewModel() {

    suspend fun getSingleItem(userChoice : String) : String{
        delay(5000)
        return DownloadManager.downloadApiSingleResult(userChoice)
    }

}
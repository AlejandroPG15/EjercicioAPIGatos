package com.example.ejercicioapigatos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ejercicioapigatos.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var model : MainActivityViewModel

    private var adapter = AdapterString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        model = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        createRecyclerView()
        downloadAll()
    }

    private fun createRecyclerView(){
        binding.RecyclerView.layoutManager = LinearLayoutManager(this)
        binding.RecyclerView.adapter = adapter
    }

    private fun downloadAll(){
        CoroutineScope(Dispatchers.IO).launch {
            val list = loadFilmInBackground()
            setAdapterOnMainThread(list)
        }
    }
    private suspend fun loadFilmInBackground() : MutableList<CatsFacts>{
        return withContext(Dispatchers.IO) {
            return@withContext model.getApiResults()!!
        }
    }

    private suspend fun setAdapterOnMainThread(booksList: MutableList<CatsFacts>) {
        withContext(Dispatchers.Main) {
            adapter.setData(booksList)
            pbLoading.visibility = View.GONE
        }
    }
}
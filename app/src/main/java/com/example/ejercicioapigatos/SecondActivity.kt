package com.example.ejercicioapigatos

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.ejercicioapigatos.databinding.ActivitySecondBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private lateinit var model : DetailsActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        model = ViewModelProvider(this).get(SecondActivityViewModel::class.java)
        var valor =  intent.getStringExtra(TAG)
        GlobalScope.launch(Dispatchers.IO) {
            val resultado = valor?.let { item1 -> model.getSingleItem(item1) }
            withContext(Dispatchers.Main){
                binding.textViewResultado.text =  resultado
            }
        }


    }

    companion object{
        private const val TAG = "TAG"
        fun createSecondActivity(context: Context, valor: String){
            val intent = Intent(context, SecondActivity::class.java)
            intent.putExtra(TAG, valor)
            context.startActivity(intent)
        }
    }

}
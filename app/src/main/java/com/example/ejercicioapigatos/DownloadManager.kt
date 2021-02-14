package com.example.ejercicioapigatos

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.delay
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray

class DownloadManager {

    companion object {
        var listFacts = mutableListOf<CatsFacts>()
        lateinit var fact : String

        suspend fun downloadApiResults() : MutableList<CatsFacts> {
            val client = OkHttpClient()
            val url = "https://cat-fact.herokuapp.com/facts"
            val request = Request.Builder()
                .url(url)
                .build()
            val call = client.newCall(request)
            val exc = call.execute()


            val bodyInString = exc.body?.string()
            bodyInString?.let {
                Log.w("GetAllFilms", bodyInString)
                val results = JSONArray(bodyInString)

                results.let {
                    Log.w("GetAllFilms", results.toString())
                    val gson = Gson()

                    val itemType = object : TypeToken<List<CatsFacts>>() {}.type

                    val list = gson.fromJson<List<CatsFacts>>(results.toString(), itemType)
                    delay(3000)
                    listFacts = list as MutableList<CatsFacts>
                }
            }
            return listFacts
        }


        suspend fun downloadApiSingleResult(userChoice : String) : String {
            val client = OkHttpClient()
            var url = "https://cat-fact.herokuapp.com/facts"
            delay(3000)
            when (userChoice) {
                "1" -> url += "/1"
                "2" -> url += "/2"
                "3" -> url += "/3"
                "4" -> url += "/4"
                "5" -> url += "/5"
                else -> return "No corresponde a ningun fact."
            }
            val request = Request.Builder()
                .url(url)
                .build()
            val call = client.newCall(request)
            val exc = call.execute()

            delay(3000)
            val bodyInString = exc.body?.string()
            bodyInString?.let {
                Log.w("GetOneBook", bodyInString)
                delay(3000)
                fact = bodyInString
            }
            return fact
        }
    }

}
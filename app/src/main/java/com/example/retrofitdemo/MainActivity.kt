package com.example.retrofitdemo

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    private val BASE_URL = "https://reqres.in/api/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(Methods::class.java)

        val repos = retrofit.getAllData()

        repos.enqueue(object: Callback<Model> {
            override fun onResponse(call: Call<Model>, response: Response<Model>) {
                val responseData = response.body()

                // Log.e("responseData", responseData?.data.toString())

                val stringBuilder = StringBuilder()
                for(item in responseData?.data!!) {
                    stringBuilder.append(item.email)
                    stringBuilder.append(", ")
                }

                val textView: TextView = findViewById(R.id.textView)
                textView.text = stringBuilder

            }

            override fun onFailure(call: Call<Model>, t: Throwable) {
                Log.e("responseData.error", t.toString())
            }
        })
    }
}
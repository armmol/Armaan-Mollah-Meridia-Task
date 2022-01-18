package com.example.armaan_mollah_task

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient  {

    companion object {

        private var retrofit: Retrofit? = null

        val client: Retrofit
            get() {
                if (retrofit == null) {
                    val BaseURL = "https://releases-f89f5.firebaseio.com/"
                    retrofit = Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(BaseURL)
                        .build()
                }
                return retrofit!!
            }
    }

}
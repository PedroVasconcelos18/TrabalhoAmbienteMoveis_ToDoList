package br.com.cotemig.trabalho.todolist.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {

    private val URL = "https://api.fluo.work/v1/"

    private val retrofit =
        Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun serviceUsuario(): ServiceUsuario {
        return retrofit.create(ServiceUsuario::class.java)
    }

    fun serviceTask(): ServiceTask {
        return retrofit.create(ServiceTask::class.java)
    }

}
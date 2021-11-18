package br.com.cotemig.trabalho.todolist.services

import br.com.cotemig.trabalho.todolist.models.Usuario
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ServiceUsuario {

    @POST("account/auth")
    fun auth(@Body account: Usuario): Call<Usuario>

    @POST("account")
    fun register(@Body account: Usuario): Call<Usuario>

}
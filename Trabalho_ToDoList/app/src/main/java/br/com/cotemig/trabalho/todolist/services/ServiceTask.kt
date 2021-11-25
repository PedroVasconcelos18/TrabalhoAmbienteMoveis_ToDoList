package br.com.cotemig.trabalho.todolist.services

import br.com.cotemig.trabalho.todolist.models.Task
import retrofit2.Call
import retrofit2.http.*

interface ServiceTask {
    @POST("task")
    fun register(@Body task: Task, @Header("token") token: String?): Call<Void>

    @GET("project/7a5125t9oe8211fla6i3v6/tasks")
    fun getAllTasks(@Header("token") token: String?): Call<List<Task>>


}
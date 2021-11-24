package br.com.cotemig.trabalho.todolist.services

import br.com.cotemig.trabalho.todolist.models.Task
import retrofit2.Call
import retrofit2.http.*

interface ServiceTask {
    @POST("task")
    fun register(@Body task: Task, @Header("token") token: String?): Call<Void>

    @GET("project/7ajvo18rco5jc1fkt9lmba/tasks")
    fun getAllTasks(@Header("token") token: String?): Call<List<Task>>


}
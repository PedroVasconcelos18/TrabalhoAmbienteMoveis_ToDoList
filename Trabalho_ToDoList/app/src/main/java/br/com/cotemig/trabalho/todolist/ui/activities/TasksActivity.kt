package br.com.cotemig.trabalho.todolist.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.cotemig.trabalho.todolist.R
import br.com.cotemig.trabalho.todolist.models.Task
import br.com.cotemig.trabalho.todolist.services.RetrofitInitializer
import br.com.cotemig.trabalho.todolist.ui.adapters.TasksAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Response

class TasksActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasks)

        var tokenUsuario = intent.getStringExtra("tokenUsuario")
        var nomeUsuario = intent.getStringExtra("nomeUsuario")
        var idUsuario = intent.getStringExtra("idUsuario")

        getTasks(tokenUsuario)

        var addTaskButton = findViewById<FloatingActionButton>(R.id.taskAddButton)

        addTaskButton.setOnClickListener {
            addTasks(tokenUsuario, idUsuario);
        }

    }

    fun getTasks(tokenUsuario: String? ) {
        val s = RetrofitInitializer().serviceTask()
        var call = s.getAllTasks(tokenUsuario);

        call.enqueue(object : retrofit2.Callback<List<Task>> {
            override fun onResponse(call: Call<List<Task>>, response: Response<List<Task>>) {
                if (response.code() == 200) {
                    response.body()?.let { tasks ->
                        showTasks(tasks);
                    }
                }
            }

            override fun onFailure(call: Call<List<Task>>, t: Throwable) {
                Toast.makeText(this@TasksActivity, "Erro ao visualizar tasks, favor contatar o adminstrador do sistema", Toast.LENGTH_LONG).show()
            }
            
        })
    }

    fun showTasks(list: List<Task>) {
        var tasksList = findViewById<RecyclerView>(R.id.tasksList)
        tasksList.adapter = TasksAdapter(this, list) { task ->
            var intent = Intent(this, TaskSelectedActivity::class.java)
            intent.putExtra("nomeUsuario", task.name)
            intent.putExtra("descricaoTask", task.description)
            intent.putExtra("tagsTask", task.tags)
            startActivity(intent)
        }
        tasksList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    fun addTasks(tokenUsuario: String?, idUsuario: String?) {
        var intent = Intent(this, RegisterTaskActivity::class.java)
        intent.putExtra("token", tokenUsuario)
        intent.putExtra("idUsuario", idUsuario)
        startActivity(intent)
    }

}
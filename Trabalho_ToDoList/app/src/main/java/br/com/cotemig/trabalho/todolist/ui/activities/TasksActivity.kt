package br.com.cotemig.trabalho.todolist.ui.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
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
        var tokenPersistence = ""

        getTasks(tokenUsuario)

        var addTaskButton = findViewById<FloatingActionButton>(R.id.taskAddButton)
        addTaskButton.setOnClickListener {
            addTasks(tokenUsuario, idUsuario);
        }

        var swipeContainer = findViewById<SwipeRefreshLayout>(R.id.swipeContainer)

        //Evitar o loop infinito no inicio da aplicação
        swipeContainer.setOnRefreshListener {
            swipeContainer.setRefreshing(false)
            Toast.makeText(
                this@TasksActivity,
                "Sua lista já está atualizada",
                Toast.LENGTH_LONG
            ).show()

        }
        //coloca corzinha do refresh
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light);

    }
    // retorno de um startActivity declarado no addTasks
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                val result = data?.getStringExtra("resultado")
                var cont = 0;
                var swipeContainer = findViewById<SwipeRefreshLayout>(R.id.swipeContainer)
                if(cont <= 0) {
                    swipeContainer.setOnRefreshListener {
                        cont++
                        Toast.makeText(this@TasksActivity, "Atualizada", Toast.LENGTH_LONG).show()
                        getTasks(result)
                        swipeContainer.setRefreshing(false)
                        }

                    swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light);
                } else {
                    swipeContainer.setOnRefreshListener {
                        swipeContainer.setRefreshing(false)
                        Toast.makeText(this@TasksActivity, "Sua lista já está atualizada", Toast.LENGTH_LONG).show()
                    }
                    swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light);
                }
            }
        } else {
            var swipeContainer = findViewById<SwipeRefreshLayout>(R.id.swipeContainer)
            swipeContainer.setOnRefreshListener {
                swipeContainer.setRefreshing(false)
                Toast.makeText(
                    this@TasksActivity,
                    "Sua lista já está atualizada",
                    Toast.LENGTH_LONG
                ).show()
            }
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

        //starta uma activity esperando um retorno
        startActivityForResult(intent, 1);
    }

}
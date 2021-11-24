package br.com.cotemig.trabalho.todolist.ui.activities

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.com.cotemig.trabalho.todolist.R

class TaskSelectedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_selected)

        var nomeTask = intent.getStringExtra("nomeUsuario")
        var descricaoTask = intent.getStringExtra("descricaoTask")
        var tagsTask =  intent.getStringExtra("tagsTask")

        var voltarButton = findViewById<Button>(R.id.voltarButton)

        voltarButton.setOnClickListener {
            voltarAllTasks();
        }

        getInfos(nomeTask, descricaoTask, tagsTask)
    }

    fun getInfos(nomeTask: String?, descricaoTask: String?, tagsTask: String?) {
        var nomeTaskView = findViewById<TextView>(R.id.task_name)
        nomeTaskView.text = nomeTask.toString()

        var descricaoTaskView = findViewById<TextView>(R.id.task_description)
        descricaoTaskView.text = descricaoTask.toString()

        var tagsTaskView = findViewById<TextView>(R.id.task_tags)
        tagsTaskView.text = tagsTask.toString()
    }

    fun voltarAllTasks() {
        finish();
    }
}
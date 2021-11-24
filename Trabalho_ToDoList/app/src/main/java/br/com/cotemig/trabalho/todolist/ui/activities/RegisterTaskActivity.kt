package br.com.cotemig.trabalho.todolist.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.cotemig.trabalho.todolist.R
import br.com.cotemig.trabalho.todolist.models.Task
import br.com.cotemig.trabalho.todolist.services.RetrofitInitializer
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Response

class RegisterTaskActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_task)

        var tokenUsuario = intent.getStringExtra("token")
        var idUsuario = intent.getStringExtra("idUsuario")
        var idProjeto = "7ajvo18rco5jc1fkt9lmba"

        var SalvarButton = findViewById<Button>(R.id.salvarButton)

        SalvarButton.setOnClickListener {
            cadastrarTask(idUsuario, idProjeto, tokenUsuario)
        }
    }

    fun cadastrarTask(idUsuario: String?, idProjeto: String?, tokenUsuario: String?) {
        var nameInput = findViewById<TextInputEditText>(R.id.nameInput);
        var descInput = findViewById<TextInputEditText>(R.id.descInput);
        var tagsInput = findViewById<TextInputEditText>(R.id.tagInput)

        var task = Task()
        task.name = nameInput.text.toString()
        task.description = descInput.text.toString()
        task.tags = tagsInput.text.toString()
        task.idProject = idProjeto
        task.idAccountTo = idUsuario

        val s = RetrofitInitializer().serviceTask()
        val call = s.register(task, tokenUsuario)

        call.enqueue(object : retrofit2.Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.code() == 200 || response.code() == 201) {
                    Toast.makeText(this@RegisterTaskActivity, "Task cadastrada com sucesso", Toast.LENGTH_LONG).show()
                    finish()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(this@RegisterTaskActivity, "Erro ao cadastrar task, favor tentar novamente", Toast.LENGTH_LONG).show()
            }
        })
    }
}
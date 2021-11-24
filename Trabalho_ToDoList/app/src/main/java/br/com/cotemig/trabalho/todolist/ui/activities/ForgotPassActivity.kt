package br.com.cotemig.trabalho.todolist.ui.activities

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import br.com.cotemig.trabalho.todolist.R

class ForgotPassActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var recuperarPassButton = findViewById<Button>(R.id.CadastrarButton);
        recuperarPassButton.setOnClickListener {
            onClickRecuperarPass();
        }

    }

    fun onClickRecuperarPass() {

    }
}
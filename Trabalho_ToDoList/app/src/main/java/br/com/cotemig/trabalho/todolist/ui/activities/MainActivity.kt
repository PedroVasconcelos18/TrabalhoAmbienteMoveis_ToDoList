package br.com.cotemig.trabalho.todolist.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import br.com.cotemig.trabalho.todolist.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var cadastrarButton = findViewById<Button>(R.id.CadastrarButton);
        cadastrarButton.setOnClickListener {
            onClickCadastrar();
        }

        var loginButton = findViewById<Button>(R.id.loginButton)
        loginButton.setOnClickListener {
            onClickLogin();
        }

    }

    fun onClickCadastrar() {
        var intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }

    fun onClickLogin() {
        var intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
    }
}
package br.com.cotemig.trabalho.todolist.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.cotemig.trabalho.todolist.R
import br.com.cotemig.trabalho.todolist.models.Usuario
import br.com.cotemig.trabalho.todolist.services.RetrofitInitializer
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Response

class ForgotPassActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_pass)

        var recuperarPassButton = findViewById<Button>(R.id.recuperarButton);
        recuperarPassButton.setOnClickListener {
            onClickRecuperarPass();
        }

    }

    fun onClickRecuperarPass() {
        var emailInput = findViewById<TextInputEditText>(R.id.emailInput);

        var usuario = Usuario()
        usuario.email = emailInput.text.toString()
        val s = RetrofitInitializer().serviceUsuario()
        val call = s.forgotPass(usuario)

        call.enqueue(object : retrofit2.Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                // Foi posto esse response.code() == 204 pois ele estava alegando que o processo foi um sucesso mas
                // não era possível sair da página
                if (response.code() == 200 || response.code() == 204) {
                    Toast.makeText(this@ForgotPassActivity, "E-mail com sua nova senha enviado", Toast.LENGTH_LONG).show()
                    finish()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(this@ForgotPassActivity, "Erro ao recuperar senha, favor conferir seu email", Toast.LENGTH_LONG).show()
            }
        })
    }
}
package br.com.cotemig.trabalho.todolist.ui.activities

import android.content.Intent
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.cotemig.trabalho.todolist.R
import br.com.cotemig.trabalho.todolist.models.Usuario
import br.com.cotemig.trabalho.todolist.services.RetrofitInitializer
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Response

class SignInActivity() : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.acitivity_sign_in)


        var loginButton = findViewById<Button>(R.id.loginButton)

        loginButton.setOnClickListener {
            loginUsuario();
        }

        var esqueciMinhaSenhaText = findViewById<TextView>(R.id.esqueciMinhaSenha)

        esqueciMinhaSenhaText.setOnClickListener {
            var intent = Intent(this@SignInActivity, ForgotPassActivity::class.java)
            startActivity(intent)
        }
    }

    fun loginUsuario() {
        var emailInput = findViewById<TextInputEditText>(R.id.emailInput);
        var passInput = findViewById<TextInputEditText>(R.id.passInput);

        var usuario = Usuario()
        usuario.email = emailInput.text.toString()
        usuario.password = passInput.text.toString()

        val s = RetrofitInitializer().serviceUsuario()
        val call = s.auth(usuario)

        call.enqueue(object : retrofit2.Callback<Usuario> {
            override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {
                if (response.code() == 200) {
                    response.body()?.let { usuarioInfos ->
                        var intent = Intent(this@SignInActivity, TasksActivity::class.java)
                        intent.putExtra("tokenUsuario", usuarioInfos.token)
                        intent.putExtra("nomeUsuario", usuarioInfos.name)
                        intent.putExtra("idUsuario", usuarioInfos.id)
                        startActivity(intent)
                    }

                } else {
                    Toast.makeText(this@SignInActivity, "Erro ao logar, favor conferir seus dados de acesso", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<Usuario>, t: Throwable) {
                Toast.makeText(this@SignInActivity, "Erro ao logar, favor conferir seus dados de acesso", Toast.LENGTH_LONG).show()
            }
        })
        
    }
}
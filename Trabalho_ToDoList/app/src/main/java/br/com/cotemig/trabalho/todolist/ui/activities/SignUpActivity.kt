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

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


        var cadastrarButton = findViewById<Button>(R.id.cadastrar)

        cadastrarButton.setOnClickListener {
            cadastrarNovoUsuario()
        }
    }

    fun cadastrarNovoUsuario() {
        var nameInput = findViewById<TextInputEditText>(R.id.nameInput)
        var emailInput = findViewById<TextInputEditText>(R.id.emailInput);
        var passInput = findViewById<TextInputEditText>(R.id.passInput);

        var usuario = Usuario()
        usuario.name = nameInput.text.toString()
        usuario.email = emailInput.text.toString()
        usuario.password = passInput.text.toString()

        val s = RetrofitInitializer().serviceUsuario()
        val call = s.register(usuario)

        call.enqueue(object : retrofit2.Callback<Usuario> {
            override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {
                if (response.code() == 200) {
                    var intent = Intent(this@SignUpActivity, SignInActivity::class.java)
                    startActivity(intent)
                }
            }

            override fun onFailure(call: Call<Usuario>, t: Throwable) {
                Toast.makeText(this@SignUpActivity, "Erro ao cadastrar, favor conferir os dados", Toast.LENGTH_LONG).show()
            }
        })
    }

}
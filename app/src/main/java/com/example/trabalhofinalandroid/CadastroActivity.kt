package com.example.trabalhofinalandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_cadastro.*

class CadastroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        bt_cadastrar.setOnClickListener {
            cadastrar();
        }
    }

    fun cadastrar(){
        val email = ti_emailCadastro.text.toString()
        val senha = ti_senhaCadastro.text.toString()
        val confimacaosenha = ti_repeteSenhaCadastro.text.toString()

        val autenticacao = FirebaseAuth.getInstance()
        autenticacao.createUserWithEmailAndPassword(email, senha)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(baseContext, "Authentication CERTO.",
                        Toast.LENGTH_SHORT).show()
                } else {
                    // If sign in fails, display a message to the user.

                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()


                }

                // ...
            }

    }
}
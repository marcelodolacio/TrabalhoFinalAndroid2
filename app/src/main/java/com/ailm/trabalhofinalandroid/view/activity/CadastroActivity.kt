package com.ailm.trabalhofinalandroid.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ailm.trabalhofinalandroid.R
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

        /* ****  VALIDACAO DE CAMPOS DO REGISTER  ****************/
        if (email.isBlank() ){
            val text = getString(R.string.email_required) //buscar texto de validação no arq string
            Toast.makeText( this , text, Toast.LENGTH_LONG).show()
            return
        }
        if (senha.isBlank() ){
            val text = getString(R.string.password_required) //buscar texto validação no arq string
            Toast.makeText( this , text, Toast.LENGTH_LONG).show()
            return
        }
        if (senha.length < 6 ){
            Toast.makeText( this , "Senha com mínimo de 6 caracteres", Toast.LENGTH_LONG).show()
            return
        }
        if (confimacaosenha != senha ){
            Toast.makeText( this , "Confirmação de senha e senha devem ser iguais!", Toast.LENGTH_LONG).show()
            return
        }

        val autenticacao = FirebaseAuth.getInstance()
        autenticacao.createUserWithEmailAndPassword(email, senha)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(baseContext, "Authentication CERTO.",
                        Toast.LENGTH_SHORT).show()
                    val navegaParaLogin = Intent(this, MainActivity::class.java);
                    startActivity(navegaParaLogin);

                } else {
                    // If sign in fails, display a message to the user.

                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()


                }

                // ...
            }

    }
}
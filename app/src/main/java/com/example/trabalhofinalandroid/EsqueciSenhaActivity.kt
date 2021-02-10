package com.example.trabalhofinalandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_esqueci_senha.*

class EsqueciSenhaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_esqueci_senha)


        bt_EsqueciSenha.setOnClickListener {
            resgatarSenha()
        }
    }

    fun resgatarSenha(){
        val email = ti_emailEsqueciSenha.text.toString();

        val autenticacao = FirebaseAuth.getInstance();
        val operacao = autenticacao.sendPasswordResetEmail(email);
        operacao.addOnCompleteListener { resultado ->
            if(resultado.isSuccessful){
                Toast.makeText(this, "CERTO: Senha enviada para usuario cadastrado", Toast.LENGTH_LONG).show();
                val navegaParaLogin = Intent(this, MainActivity::class.java);
                startActivity(navegaParaLogin);
            }else{
                Toast.makeText(this, "Erro: Usuário inexistentes", Toast.LENGTH_LONG).show();
            }
        }

    }
}
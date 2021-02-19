package com.ailm.trabalhofinalandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bt_Login.setOnClickListener {
            login();
        }

        tv_Cadastro.setOnClickListener {
            registro();
        }

        tv_esqueciSenha.setOnClickListener {
            esqueciSenha();
        }
    }

    fun login(){

        val email = ti_Email.text.toString();
        val senha = ti_Senha.text.toString();

        if(email.isNotBlank() && senha.isNotBlank()){
            Toast.makeText(this, "FUNCIONOU",Toast.LENGTH_LONG).show();
        }

        val autenticacao = FirebaseAuth.getInstance();
        val operacao = autenticacao.signInWithEmailAndPassword(email,senha);
        operacao.addOnCompleteListener { resultado ->
            if(resultado.isSuccessful){
                // intents
                val navegaParaTelaPrincipal = Intent(this, HomeActivity::class.java);
                startActivity(navegaParaTelaPrincipal);
            }else{
                Toast.makeText(this, "Erro: Usuário ou senha inexistentes",Toast.LENGTH_LONG).show();
            }
        }
    }

    fun esqueciSenha(){
        val navegaParaEsqueciSenha = Intent(this, EsqueciSenhaActivity::class.java);
        startActivity(navegaParaEsqueciSenha);
    }

    fun registro(){
        val navegaParaTelaCadastro = Intent(this, CadastroActivity::class.java);
        startActivity(navegaParaTelaCadastro);
    }
}
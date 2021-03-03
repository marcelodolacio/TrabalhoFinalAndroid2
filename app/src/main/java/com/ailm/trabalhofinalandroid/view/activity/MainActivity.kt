package com.ailm.trabalhofinalandroid.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ailm.trabalhofinalandroid.R
import com.ailm.trabalhofinalandroid.domain.LoginData
import com.ailm.trabalhofinalandroid.domain.LoginResult
import com.ailm.trabalhofinalandroid.viewmodel.LoginViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var viewmodelLogin: LoginViewModel

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
        bt_ChatBot.setOnClickListener {
            acessoChatBot();
        }

        viewmodelLogin = LoginViewModel(application)
        viewmodelLogin.resultadoParaTela.observe(this) { resultado ->
            processarResultLogin(resultado)
        }
    }

    fun login(){

        val email = ti_Email.text.toString();
        val senha = ti_Senha.text.toString();

        val data = LoginData(email, senha)
        viewmodelLogin.login(data)
    }

    private fun processarResultLogin(res: LoginResult){
        //mensagem de erro para usuario atraves de Toast
        if(res.error != null) {
            Toast.makeText(this, res.error, Toast.LENGTH_LONG).show()
            return
        }

        val intentHome = Intent(this, HomeActivity::class.java)
        startActivity(intentHome)
        finish() //finalizar pilha! Evitar que, ao usuario clicar em voltar no aparelho, ele retorne para tela de login novamente
    }


    private fun esqueciSenha(){
        val navegaParaEsqueciSenha = Intent(this, EsqueciSenhaActivity::class.java);
        startActivity(navegaParaEsqueciSenha);
    }

    private fun registro(){
        val navegaParaTelaCadastro = Intent(this, CadastroActivity::class.java);
        startActivity(navegaParaTelaCadastro);
    }
    private fun acessoChatBot(){
        val navegaParaTelaChatBot = Intent(this, ChatBotActivity::class.java);
        startActivity(navegaParaTelaChatBot);
    }
}
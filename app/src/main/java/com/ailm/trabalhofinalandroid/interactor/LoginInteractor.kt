package com.ailm.trabalhofinalandroid.interactor

import com.ailm.trabalhofinalandroid.domain.LoginData
import com.ailm.trabalhofinalandroid.domain.LoginResult
import com.ailm.trabalhofinalandroid.repository.LoginRepository

class LoginInteractor {
    // variavel que vincula a interactor com a repository
    var repo = LoginRepository()

    // data class Login:classe de dados p/ concentrar os atributos para login e retorno de criticas/sucesso
    suspend fun login(data: LoginData): LoginResult {

        val resultado = LoginResult()

        /* ****  VALIDACAO DE CAMPOS DO LOGIN  ****************/
        if (data.email.isBlank() ){
            resultado.error = "ERRO_EMAIL_VAZIO"
            return resultado
        }
        if (data.pass.isBlank() ){
            resultado.error = "ERRO_SENHA_VAZIA"
            return resultado
        }
        if (data.pass.length < 6 ){
            resultado.error = "ERRO_SENHA_MENOR_6"
            return resultado
        }
        //após cricitas passar dados para repository
        val resultadoRepo =  repo.login(data)

        return resultadoRepo
    }
}
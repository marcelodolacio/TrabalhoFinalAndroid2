package com.ailm.trabalhofinalandroid.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.room.*
import com.ailm.trabalhofinalandroid.R
import com.ailm.trabalhofinalandroid.domain.LoginData
import com.ailm.trabalhofinalandroid.domain.LoginResult
import com.ailm.trabalhofinalandroid.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var viewmodelLogin: LoginViewModel

    /* variaveis necessarias para BD */
    //private lateinit var database: AppDataBase
    //private lateinit var dao: PontosFavoritosDao
    //private var favoritos: PontosFavoritosBD = PontosFavoritosBD()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* BD no celular
        database = Room
            .databaseBuilder(applicationContext, AppDataBase::class.java, "meubanco")
            .build()

        dao = database.getFavoritosDao()
        insertPontosFavoritos()
        Log.d("LOG", " passou insertPontosFavoritos()")

        searchPontosFavoritos()
        Log.d("LOG", " passou searchPontosFavoritos()")
         */

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


//    @Query("select count(*) from PontosTuristicosBD where id > 0 ")
//    suspend fun getPontosTuristicos(): PontosTuristicosBD

    /*
    private fun searchPontosFavoritos() {
        GlobalScope.launch{
            favoritos = dao.getPontosFavoritos(1)

            Log.d("LOG", " id: ${favoritos.id}")
            Log.d("LOG", " name: ${favoritos.nome}")
        }
    }


    private fun insertPontosFavoritos(){
        GlobalScope.launch {
            val p = PontosFavoritosBD(
//                id = 1 ,
                nome = "Torre de TV"
            )
            dao.insertPontosFavoritos(p)
        }
    }
    */

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

/*
* entidade PontosTuristicos do banco de dados
* */
@Entity
data class PontosFavoritosBD(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val nome: String? = null
)

/*
* Data Access Object (DAO) de PontosTuristicos
* */
@Dao
interface PontosFavoritosDao {
    @Query("select * from PontosFavoritosBD where id = :id ")
    suspend fun getPontosFavoritos(id: Int): PontosFavoritosBD

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPontosFavoritos (p: PontosFavoritosBD)
}

/*
*  Bando de dados do APP
* */
@Database(entities = [PontosFavoritosBD::class], version = 1)
abstract class AppDataBase: RoomDatabase(){
    abstract fun getFavoritosDao(): PontosFavoritosDao
}


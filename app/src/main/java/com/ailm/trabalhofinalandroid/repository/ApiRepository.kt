package com.ailm.trabalhofinalandroid.repository

import android.util.Log
import com.ailm.trabalhofinalandroid.domain.PontosTuristicos
import com.ailm.trabalhofinalandroid.repository.dto.PontosTuristicosDTO
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import com.ailm.trabalhofinalandroid.view.activity.MainActivity


interface PontosTuristicosApi {
    @GET("pontos-turisticos")
    @Headers("Content-Type: application/json")
    suspend fun recuperarPontosTuristicos () : List<PontosTuristicosDTO>
}

class ApiRepository {

    private val connector: Retrofit

    init {
        connector = Retrofit.Builder()
            .baseUrl("https://api-pontos-turisticos-android.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    suspend fun chamarAPI(): List<PontosTuristicos>{
        val service = connector.create(PontosTuristicosApi::class.java)
        val listaPontosTuristicos = service.recuperarPontosTuristicos()
        Log.d("LOG", " ApiRepository - chamarAPI - listaPontosTuristicos: $listaPontosTuristicos")

        return listaPontosTuristicos.map { dto ->
            PontosTuristicos(
                id = dto.id,
                nome = dto.nome,
                bairro = dto.bairro,
                lat = dto.lat,
                long = dto.long,
                image = dto.image,
            )
        }
    }

}
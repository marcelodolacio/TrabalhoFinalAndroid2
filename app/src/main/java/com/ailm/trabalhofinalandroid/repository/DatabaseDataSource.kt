package com.ailm.trabalhofinalandroid.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.ailm.trabalhofinalandroid.bd.PontosDAO
import com.ailm.trabalhofinalandroid.bd.PontosEntity

class DatabaseDataSource(
    private val pontosDAO: PontosDAO
) : PontosRepository {

    override suspend fun insertPonto(idPonto: Long, nome: String): Long {
        Log.d("LOG", " DatabaseDataSource - insertPonto()")

        val pontos = PontosEntity(
            idPonto = idPonto,
            nome = nome
        )
        return pontosDAO.insert(pontos)
    }

    override suspend fun deletePonto(idPonto: Long) {
        Log.d("LOG", " DatabaseDataSource - deletePonto()")
        pontosDAO.delete(idPonto)
    }

    override suspend fun deleteAllPontos() {
        Log.d("LOG", " DatabaseDataSource - deleteAllPontos()")
        pontosDAO.deleteAll()
    }

    override suspend fun getAllPontos(): List<PontosEntity> {
        Log.d("LOG", " DatabaseDataSource - getAllPontos()")
        return pontosDAO.getAllPontos()
    }
}
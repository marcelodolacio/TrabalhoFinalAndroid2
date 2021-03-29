package com.ailm.trabalhofinalandroid.repository

import androidx.lifecycle.LiveData
import com.ailm.trabalhofinalandroid.bd.PontosEntity

interface PontosRepository {

    suspend fun insertPonto(idPonto: Long, nome: String): Long

    suspend fun deletePonto(idPonto: Long)

    suspend fun deleteAllPontos()

    suspend fun getAllPontos(): List<PontosEntity>

}
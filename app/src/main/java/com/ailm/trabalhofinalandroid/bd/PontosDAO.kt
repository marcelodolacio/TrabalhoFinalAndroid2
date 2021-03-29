package com.ailm.trabalhofinalandroid.bd

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PontosDAO {
    @Query("select * from PontosTuristicos where id = :idPonto ")
    suspend fun getPontosBd(idPonto: Long): PontosEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(pontos: PontosEntity): Long

    @Query("DELETE FROM PontosTuristicos WHERE idPonto = :idPonto")
    suspend fun delete(idPonto: Long)

    @Query("DELETE FROM PontosTuristicos")
    suspend fun deleteAll()

    @Query("SELECT * FROM PontosTuristicos")
    suspend fun getAllPontos(): List<PontosEntity>
}
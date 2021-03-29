package com.ailm.trabalhofinalandroid.bd

import androidx.room.Entity
import androidx.room.PrimaryKey

/*
* entidade armazenar pontos no BD do celular
* */
@Entity(tableName = "PontosTuristicos")
data class PontosEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val idPonto: Long? = null,
    val nome: String? = null
)
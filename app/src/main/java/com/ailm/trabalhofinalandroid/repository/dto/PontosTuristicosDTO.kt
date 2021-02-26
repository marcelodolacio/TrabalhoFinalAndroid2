package com.ailm.trabalhofinalandroid.repository.dto

import java.math.BigDecimal

//https://api-pontos-turisticos-android.herokuapp.com/pontos-turisticos
data class PontosTuristicosDTO (
        val id: Integer,
        val nome: String,
        val bairro: String,
        val lat: BigDecimal,
        val long: BigDecimal,
        val image: String
)


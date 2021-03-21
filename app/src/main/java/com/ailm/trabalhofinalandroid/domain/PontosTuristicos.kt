package com.ailm.trabalhofinalandroid.domain

import java.io.Serializable
import java.math.BigDecimal

data class PontosTuristicos (
    val id: Integer,
    val nome: String,
    val bairro: String,
    val lat: BigDecimal,
    val long: BigDecimal,
    val image: String
) : Serializable
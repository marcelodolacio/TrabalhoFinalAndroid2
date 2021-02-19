package com.ailm.trabalhofinalandroid.repository.dto

// https://api-ij.vercel.app/api/pontosTuristicos
data class ApiDTO(
        var id: Int,
        var nome: String,
        var lat: String,
        var long: String,
        var img: String
)


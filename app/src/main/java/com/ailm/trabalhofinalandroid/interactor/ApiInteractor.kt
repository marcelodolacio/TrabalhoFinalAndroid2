package com.ailm.trabalhofinalandroid.interactor

import android.util.Log
import com.ailm.trabalhofinalandroid.domain.PontosTuristicos
import com.ailm.trabalhofinalandroid.repository.ApiRepository

class ApiInteractor {

    private val repo = ApiRepository()

    suspend fun chamarAPI(): List<PontosTuristicos>{
        return repo.chamarAPI()
    }
}
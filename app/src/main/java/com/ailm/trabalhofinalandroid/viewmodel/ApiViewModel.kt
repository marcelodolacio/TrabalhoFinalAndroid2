package com.ailm.trabalhofinalandroid.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ailm.trabalhofinalandroid.domain.PontosTuristicos
import com.ailm.trabalhofinalandroid.interactor.ApiInteractor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ApiViewModel(val app: Application) : AndroidViewModel(app), CoroutineScope {

    override val coroutineContext: CoroutineContext = Dispatchers.Main

    private val interactor = ApiInteractor()
    val resultadoTela = MutableLiveData<List<PontosTuristicos>>()

    fun chamarAPI(){
        launch {
            val listaPontosTuristicos =  interactor.chamarAPI()
            resultadoTela.value = listaPontosTuristicos
        }
    }

}
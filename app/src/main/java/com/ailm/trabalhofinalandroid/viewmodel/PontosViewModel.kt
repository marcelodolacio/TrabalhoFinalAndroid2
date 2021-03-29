package com.ailm.trabalhofinalandroid.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ailm.trabalhofinalandroid.R
import com.ailm.trabalhofinalandroid.repository.PontosRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class PontosViewModel(
    private val repository: PontosRepository
) : ViewModel() {

    private val _pontosStateEventData = MutableLiveData<PontosState>()
    val pontosStateEventData: LiveData<PontosState>
        get() = _pontosStateEventData

    private val _messageEventData = MutableLiveData<Int>()
    val messageEventData: LiveData<Int>
        get() = _messageEventData

    fun addPontos(idPonto: Long, nome:String) = viewModelScope.launch {
        Log.d("LOG", " PontosViewModel - addPontos")
        try {
            val id = repository.insertPonto(idPonto, nome)
            if (id > 0) {
                _pontosStateEventData.value = PontosState.Inserir
                _messageEventData.value = R.string.insert_banco_dados_sucesso
            }
        }catch (ex: Exception){
            _messageEventData.value = R.string.erro_inserir_banco_dados
            Log.e(TAG, ex.toString()  )
        }
    }

    fun deletePontos() = viewModelScope.launch {
        Log.d("LOG", " PontosViewModel - deletePontos")
        try {
            repository.deleteAllPontos()
        }catch (ex: Exception){
            _messageEventData.value = R.string.erro_deleteall_banco_dados
            Log.e(TAG, ex.toString()  )
        }
    }

    sealed class PontosState {
        object Inserir : PontosState()
    }
    companion object {
        private val TAG = PontosViewModel::class.java.simpleName
    }

}
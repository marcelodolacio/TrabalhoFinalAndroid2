package com.ailm.trabalhofinalandroid.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.*
import com.ailm.trabalhofinalandroid.databinding.FragmentListaBinding
import com.ailm.trabalhofinalandroid.domain.PontosTuristicos
import com.ailm.trabalhofinalandroid.view.adapter.AdaptadorPontosTuristicos
import com.ailm.trabalhofinalandroid.viewmodel.ApiViewModel
import java.math.BigDecimal
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.*
import com.ailm.trabalhofinalandroid.bd.BancoDadosApp
import com.ailm.trabalhofinalandroid.bd.PontosDAO
import com.ailm.trabalhofinalandroid.repository.DatabaseDataSource
import com.ailm.trabalhofinalandroid.repository.PontosRepository
import com.ailm.trabalhofinalandroid.viewmodel.PontosViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ListaFragment : Fragment() {

    private lateinit var binding: FragmentListaBinding
    private val viewModel: ApiViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("LOG", " ListaFragment - onCreateView")

        binding = FragmentListaBinding.inflate(inflater, container, false)
        binding.meuFragmento = this
        binding.lifecycleOwner = this
        viewModel.resultadoTela.observe(viewLifecycleOwner) { lista ->
            mostrarResultadoAPI(lista)
        }
        binding.rvPontosTuristicos.layoutManager = LinearLayoutManager(context)

        return binding.root
    }

    private fun mostrarResultadoAPI(lista: List<PontosTuristicos>) {

        val adaptador = AdaptadorPontosTuristicos(lista)
        binding.rvPontosTuristicos.adapter = adaptador

        viewModelPontos.deletePontos()
        for (n in lista.indices){
            Log.d("LOG", " !!!!!!!!! lista [$n]: ${lista[n].id.toLong()} ' - ' ${lista[n].nome}")
            val idPonto = lista[n].id.toLong()
            val nome = lista[n].nome.toString()
            viewModelPontos.addPontos(idPonto = idPonto, nome = nome)
        }
    }

    fun chamarAPI() {
        Log.d("LOG", " ListaFragment - chamarAPI")
        viewModel.chamarAPI()
    }

    private val viewModelPontos: PontosViewModel by viewModels {

        Log.d("LOG", " ListaFragment - viewModelPontos")

        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val subscriberDAO: PontosDAO =
                    BancoDadosApp.getInstance(requireContext()).pontosDAO

                val repository: PontosRepository = DatabaseDataSource(subscriberDAO)
                return PontosViewModel(repository) as T
            }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("LOG", " ListaFragment - onViewCreated")

        observeEvents()
//        viewModelPontos.addPontos(idPonto = 1, nome = "Torre de TV")
    }

    private fun observeEvents() {
        Log.d("LOG", " ListaFragment - observeEvents")

        viewModelPontos.pontosStateEventData.observe(viewLifecycleOwner) { pontosState ->
            when (pontosState) {
                is PontosViewModel.PontosState.Inserir -> {
                    requireView().requestFocus()
                }
            }
        }

        viewModelPontos.messageEventData.observe(viewLifecycleOwner) { stringResId ->
            Snackbar.make(requireView(), stringResId, Snackbar.LENGTH_LONG).show()
        }
    }
}

package com.ailm.trabalhofinalandroid.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ailm.trabalhofinalandroid.databinding.FragmentListaBinding
import com.ailm.trabalhofinalandroid.domain.PontosTuristicos
import com.ailm.trabalhofinalandroid.view.adapter.AdaptadorPontosTuristicos
import com.ailm.trabalhofinalandroid.viewmodel.ApiViewModel

class ListaFragment : Fragment() {

    private lateinit var binding: FragmentListaBinding
    private val viewModel: ApiViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListaBinding.inflate(inflater, container, false)
        binding.meuFragmento = this
        binding.lifecycleOwner = this
        viewModel.resultadoTela.observe(viewLifecycleOwner){ lista ->
            mostrarResultadoAPI(lista)
        }
        binding.rvPontosTuristicos.layoutManager = LinearLayoutManager(context)
        return binding.root
    }

    private fun mostrarResultadoAPI(lista: List<PontosTuristicos>){
        val adaptador = AdaptadorPontosTuristicos(lista)
        binding.rvPontosTuristicos.adapter = adaptador
    }

    fun chamarAPI(){
        viewModel.chamarAPI()
    }
}
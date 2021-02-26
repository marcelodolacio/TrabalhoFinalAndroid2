package com.ailm.trabalhofinalandroid.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ailm.trabalhofinalandroid.R
import com.ailm.trabalhofinalandroid.databinding.ItemPontoTuristicoBinding
import com.ailm.trabalhofinalandroid.domain.PontosTuristicos

class AdaptadorPontosTuristicos(
    val lista: List<PontosTuristicos>
) : RecyclerView.Adapter<AdaptadorPontosTuristicos.GuardadorDeDadosPontosTuristicos>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuardadorDeDadosPontosTuristicos {
        var instanciaDoXML = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_ponto_turistico, parent, false)
        var guardador = GuardadorDeDadosPontosTuristicos(instanciaDoXML)
        return guardador
    }

    override fun onBindViewHolder(holder: GuardadorDeDadosPontosTuristicos, position: Int) {
        val binding = holder.binding
        val p = lista[position]
        binding.ponto = p
        binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    inner class GuardadorDeDadosPontosTuristicos(v: View) : RecyclerView.ViewHolder(v) {
        val binding: ItemPontoTuristicoBinding = ItemPontoTuristicoBinding.bind(v)
    }
}
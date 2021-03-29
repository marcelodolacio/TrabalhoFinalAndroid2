package com.ailm.trabalhofinalandroid.view.adapter

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.ailm.trabalhofinalandroid.R
import com.ailm.trabalhofinalandroid.databinding.ItemPontoTuristicoBinding
import com.ailm.trabalhofinalandroid.domain.PontosTuristicos
import com.ailm.trabalhofinalandroid.view.activity.HomeActivity
import com.ailm.trabalhofinalandroid.view.activity.MapsActivity
import kotlinx.android.synthetic.main.item_ponto_turistico.view.*
import com.ailm.trabalhofinalandroid.view.fragment.ListaFragment

class AdaptadorPontosTuristicos(
    val lista: List<PontosTuristicos>
) : RecyclerView.Adapter<AdaptadorPontosTuristicos.GuardadorDeDadosPontosTuristicos>() {

    //private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuardadorDeDadosPontosTuristicos {
        var instanciaDoXML = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_ponto_turistico, parent, false)
        var guardador = GuardadorDeDadosPontosTuristicos(instanciaDoXML)

        guardador.itemView.setOnClickListener {
            Log.d("LOG", " AdaptadorPontosTuristicos - onCreateViewHolder()")
            val ponto = guardador.binding.ponto
            System.out.println(ponto)
            val navegaParaTelaMapa = Intent(parent.context, MapsActivity::class.java)

            navegaParaTelaMapa.putExtra("pontoTurisico", ponto)

            parent.context.startActivity(navegaParaTelaMapa)
        }

        return guardador
    }

    override fun onBindViewHolder(holder: GuardadorDeDadosPontosTuristicos, position: Int) {
        val binding = holder.binding
        val p = lista[position]
        binding.ponto = p
        binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        Log.d("LOG", " AdaptadorPontosTuristicos - getItemCount - $lista")

        return lista.size
    }

    inner class GuardadorDeDadosPontosTuristicos(v: View) : RecyclerView.ViewHolder(v) {
        val binding: ItemPontoTuristicoBinding = ItemPontoTuristicoBinding.bind(v)
    }
}
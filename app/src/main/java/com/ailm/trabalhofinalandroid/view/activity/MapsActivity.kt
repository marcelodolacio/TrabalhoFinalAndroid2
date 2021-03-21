package com.ailm.trabalhofinalandroid.view.activity

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.snackbar.Snackbar
import com.ailm.trabalhofinalandroid.R
import com.ailm.trabalhofinalandroid.domain.PontosTuristicos
import java.math.BigDecimal


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private val MAP_REQUEST_TICKET = 9999

    private lateinit var googleMap: GoogleMap
    private lateinit var locationManager: LocationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        // Recupera a instância do mapa configurado na atividade
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment

        // Solicita a apresentação do mapa em background
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(mapa: GoogleMap) {
        googleMap = mapa
        val pontoTuristico = intent.getSerializableExtra("pontoTurisico") as PontosTuristicos
        val posicaoPontoTuristico = LatLng(
            pontoTuristico.lat.toDouble(),
            pontoTuristico.long.toDouble()
        )
        val pinoPontoTuristico = MarkerOptions().position(posicaoPontoTuristico).title(
            pontoTuristico.nome
        )
        googleMap.addMarker(pinoPontoTuristico)
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(posicaoPontoTuristico, 13f))
    }

    override fun onStart() {
        super.onStart()
        checkPermission()
    }

    override fun onPause() {
        super.onPause()
        locationManager.removeUpdates(locationListener)
    }

    // Verifica se o usuário concedeu a permissão para acesso à localização
    private fun checkPermission() {
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                MAP_REQUEST_TICKET
            )
        } else {
            setupLocation()
        }
    }

    // Esse método é executado quando o usuário responde à solicitação de alguma
    // permissão do aplicativo. Neste caso estamos solicitando a permissão para
    // acesso à localização do dispositivo.
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == MAP_REQUEST_TICKET) { // VERIFICA SE A RESPOSTA É PARA A SOLICITAÇÃO DO MAPA
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                setupLocation()
            } else {
                showSnackbar("A permissão de localização é obrigatória!") {
                    checkPermission()
                }
            }
        }
    }

    private fun setupLocation() {
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        // Configura um função para ser executada respeitando
        // os critérios de Tempo (3000 milisegundos) entre as atualizações e
        // a distância mínima (1 metro)
        locationManager.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            3000,
            1.0f,
            locationListener
        )
    }

    private fun showSnackbar(msg: String, callback: () -> Unit) {
        val rootView = findViewById<View>(android.R.id.content).rootView
        Snackbar
            .make(rootView, msg, Snackbar.LENGTH_LONG)
            .setAction("Aceito") {
                callback()
            }
            .show()
    }

    private val locationListener = LocationListener { localicacao ->
        //O usuário se moveu.
        Log.d(
            "MAPLOCATION",
            "Latitude: ${localicacao.latitude}    Longitude: ${localicacao.longitude} "
        )
    }

}
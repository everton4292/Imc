package com.everton.startup

import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_main.*


class MainViewModel : ViewModel() {

    fun isNumeric(str: String): Boolean {
        return try {
            str.toDouble()
            true
        } catch (e: NumberFormatException) {
            false
        }

    }


    /*fun validation(altura: String, peso: String): MainActivity {
        if (peso.isEmpty() || altura.isEmpty()) {
            Toast.makeText(MainActivity(), "Peso ou Altura em branco", Toast.LENGTH_LONG)
                .show()

        }*/


        var imc = MutableLiveData<Float>()
        fun calculateIMC(altura: String, peso: String) {
            imc.value = peso.toFloat() / (altura.toFloat() * altura.toFloat())

        }

        var categoria = MutableLiveData<String>()
        fun showCategory(resultado: Float) {
            if (resultado < 18.5) {
                categoria.value = "Abaixo do peso"
            } else if (resultado in 18.5..24.9) {
                categoria.value = "Peso normal"
            } else if (resultado in 25.0..29.9) {
                categoria.value = "Sobrepeso"
            } else if (resultado in 30.0..34.9) {
                categoria.value = "Obesidade grau I"
            } else if (resultado in 35.0..39.9) {
                categoria.value = "Obesidade grau II"
            } else if (resultado > 40.0) {
                categoria.value = "Obesidade grau III"
            }
        }

    }




package com.everton.startup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class MainViewModel : ViewModel() {

    var error = MutableLiveData<Unit>()
    val resultado = MutableLiveData<Resultado>()

    fun calculateIMC(altura: String, peso: String) {
        altura.replace(',', '.')
        peso.replace(',', '.')

        if (validation(altura, peso)) {
            val imc = peso.toFloat() / (altura.toFloat() * altura.toFloat())
            showCategory(imc)
        } else {
            error.value = Unit
        }
    }

    private fun isValidNumber(str: String): Boolean {
        return try {
            str.toDouble()
            true
        } catch (e: NumberFormatException) {
            false
        }
    }

    private fun validation(altura: String, peso: String): Boolean {
        if (!isValidNumber(peso) && !isValidNumber(altura)) {
            return false
        }
        return true
    }

    private fun showCategory(imc: Float) {
        val result = Resultado()
        result.imc = imc.toString()
        when {
            imc < 18.5 -> {
                result.categoria = "Abaixo do peso"
            }
            imc in 18.5..24.9 -> {
                result.categoria = "Peso normal"
            }
            imc in 25.0..29.9 -> {
                result.categoria = "Sobrepeso"
            }
            imc in 30.0..34.9 -> {
                result.categoria = "Obesidade grau I"
            }
            imc in 35.0..39.9 -> {
                result.categoria = "Obesidade grau II"
            }
            imc > 40.0 -> {
                result.categoria = "Obesidade grau III"
            }
        }
        resultado.value = result
    }
}








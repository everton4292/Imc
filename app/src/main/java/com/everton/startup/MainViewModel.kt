package com.everton.startup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class MainViewModel : ViewModel() {

    var error = MutableLiveData<Unit>()


    fun isNumeric(str: String): Boolean {
        return try {
            str.toDouble()
            true
        } catch (e: NumberFormatException) {
            false
        }

    }

    fun validation(altura: String, peso: String): Boolean {
        if (!isNumeric(peso) || !isNumeric(altura)) {
            return true
        } else if (peso.isEmpty() || altura.isEmpty()) {
            return false
        }
        return true
    }


    fun calculateIMC(altura: String, peso: String) {
        if (!validation(altura, peso)) {
            error.value = Unit
        } else {
            val imc = peso.toFloat() / (altura.toFloat() * altura.toFloat())
            showCategory(imc)
        }
    }


    val resultado = MutableLiveData<Resultado>()
    fun showCategory(imc: Float) {
        resultado.value?.imc = imc.toString()
        if (imc < 18.5) {
            this.resultado.value?.categoria = "Abaixo do peso"
        } else if (imc in 18.5..24.9) {
            this.resultado.value?.categoria = "Peso normal"
        } else if (imc in 25.0..29.9) {
            this.resultado.value?.categoria = "Sobrepeso"
        } else if (imc in 30.0..34.9) {
            this.resultado.value?.categoria = "Obesidade grau I"
        } else if (imc in 35.0..39.9) {
            this.resultado.value?.categoria = "Obesidade grau II"
        } else if (imc > 40.0) {
            this.resultado.value?.categoria = "Obesidade grau III"
        }
    }
}








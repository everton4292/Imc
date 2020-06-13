package com.everton.startup

import android.text.Editable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 *  View Model used for the MVVM architecture
 */

class MainViewModel : ViewModel() {

    var error = MutableLiveData<Unit>()
    val resultado = MutableLiveData<Resultado>()
    val to00 = MutableLiveData<Unit>()

    /**
     *  Function used to validate the inputs from the user. If the input starts with "0" or
     *  has only one character, the field resets until a valid input is used. A valid input
     *  is two characters, not starting with "0".
     */
        fun to0 (s: Editable?) {
        if (s.toString().length == 1 && s.toString().startsWith("0")) {
            s?.clear()
            to00.value = Unit
        }
    }

    /**
     * Function used to calculate the Body Mass Index(BMI in english, IMC in portuguese).
     * It is calculated dividing the weight by the square of the height.
     */
        fun calculateIMC(altura: String, peso: String) {

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
        if (!isValidNumber(peso) || !isValidNumber(altura)) {
            return false
        }
        return true
    }

    private fun showCategory(imc: Float) {
        val result = Resultado()
        result.imc = imc.toString()
        when {
            imc < 18.5 -> {
                result.categoria = "Underweight"
            }
            imc in 18.5..24.9 -> {
                result.categoria = "Normal weight"
            }
            imc in 25.0..29.9 -> {
                result.categoria = "Overweight"
            }
            imc in 30.0..34.9 -> {
                result.categoria = "Class 1 obesity"
            }
            imc in 35.0..39.9 -> {
                result.categoria = "Class 2 obesity"
            }
            imc > 40.0 -> {
                result.categoria = "Class 3 obesity"
            }
        }
        resultado.value = result
    }
}








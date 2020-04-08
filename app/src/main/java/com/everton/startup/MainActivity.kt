package com.everton.startup

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DecimalFormat


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        button.setOnClickListener {
            //height and weight inputs
            var altura = altura.text.toString().replace(',', '.')
            var peso = peso.text.toString().replace(',', '.')

            //validation function
            fun isNumeric(str: String): Boolean {
                return try {
                    str.toDouble()
                    true
                } catch (e: NumberFormatException) {
                    false
                }
            }

            //validation
            val validation = isNumeric(altura)
            val validation2 = isNumeric(peso)

            if (peso.isEmpty() || altura.isEmpty()) {
                Toast.makeText(this@MainActivity, "Peso ou Altura em branco", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            } else if (!validation || !validation2) {
                Toast.makeText(this@MainActivity, "Peso ou Altura inválidos", Toast.LENGTH_LONG).show()
                return@setOnClickListener

            }

            //calculation
            var imc = peso.toFloat() / (altura.toFloat() * altura.toFloat())
            resultado.text = imc.toString()

            //categories
            if (imc < 18.5) {
                resultado2.text = "Abaixo do peso"
            } else if (imc in 18.5..24.9) {
                resultado2.text = "Peso normal"
            } else if (imc in 25.0..29.9) {
                resultado2.text = "Sobrepeso"
            } else if (imc in 30.0..34.9) {
                resultado2.text = "Obesidade grau I"
            } else if (imc in 35.0..39.9) {
                resultado2.text = "Obesidade grau II"
            } else if (imc > 40.0) {
                resultado2.text = "Obesidade grau III"
            }
            // É calculado dividindo o peso (em kg) pela altura ao quadrado (em metros).

        }
    }

}



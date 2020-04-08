package com.everton.startup

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DecimalFormat


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {


            var altura = DecimalFormat.getInstance().parse(altura.text.toString().replace(",", "."))
            var peso = DecimalFormat.getInstance().parse(peso.text.toString().replace(",", "."))
            var imc = peso.toFloat() / (altura.toFloat() * altura.toFloat())

            resultado.text = imc.toString()
            // É calculado dividindo o peso (em kg) pela altura ao quadrado (em metros).

        }
    }

}



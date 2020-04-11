package com.everton.startup

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(supportaction)

        button.setOnClickListener {
            val altura = altura.text.toString()
            val peso = peso.text.toString()

            viewModel.calculateIMC(altura, peso)
        }
        viewModel.error.observe(this, Observer {
            Toast.makeText(this, "Peso ou Altura em branco", Toast.LENGTH_LONG)
                .show()
        })
        viewModel.resultado.observe(this, Observer { resultado ->
            resultado2.text = resultado.categoria
            textViewResultado.text = resultado.imc
        })
    }
}

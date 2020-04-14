package com.everton.startup

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.github.rtoshiro.util.format.SimpleMaskFormatter
import com.github.rtoshiro.util.format.text.MaskTextWatcher
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(supportaction)


        var alturaMask = altura
        var pesoMask = peso
        var alt0 = altura
        var peso0 = peso


        val simpleMaskFormatterAltura = SimpleMaskFormatter ("A.AA")
        val simpleMaskFormatterPeso = SimpleMaskFormatter("AAA.AA")
        val maskTextWatcherAltura = MaskTextWatcher(alturaMask, simpleMaskFormatterAltura)
        val maskTextWatcherPeso = MaskTextWatcher(pesoMask, simpleMaskFormatterPeso)
        alturaMask.addTextChangedListener(maskTextWatcherAltura)
        pesoMask.addTextChangedListener(maskTextWatcherPeso)


        alt0.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.to0(s)

                }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }


        })

        peso0.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.to0(s)

                }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })


        button.setOnClickListener {

            val altura = altura.text.toString()
            val peso = peso.text.toString()

            viewModel.calculateIMC(altura, peso)
        }
        viewModel.error.observe(this, Observer {
            Toast.makeText(this, "Invalid height or weight input", Toast.LENGTH_LONG)
                .show()
        })
        viewModel.resultado.observe(this, Observer { resultado ->
            resultado2.text = resultado.categoria
            textViewResultado.text = resultado.imc
        })
        viewModel.to00.observe(this, Observer {
            Toast.makeText(this@MainActivity, "First digit can't be 0", Toast.LENGTH_LONG).show()
        })
    }
}




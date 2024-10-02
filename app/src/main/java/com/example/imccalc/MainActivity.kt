package com.example.imccalc

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.imccalc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.submitButton.setOnClickListener { calcularIMC() } //Chama a função ao clicar no botão


        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }

    }
    private fun calcularIMC(){
        val pesotxt = binding.Peso.text.toString()
        val alturatxt = binding.Altura.text.toString() //converte o valor de View para String

        val peso: Float = pesotxt.toFloatOrNull()?: 00.0f
        val altura: Float = alturatxt.toFloatOrNull()?: 0.00f //Converte o valor de string para Float ou Nulo

        if (altura != 0.00f && peso != 00.0f) { // certifica que o valor não vai ser zero

            val calculo = peso / (altura * altura)

            val saude = obterSaude(calculo) // chama a função que obtem o texto da saude com base no valor do imc

            val textoresultado = "Seu IMC é $calculo, ele está $saude" //formata o texto para 1 decimal e mostra sua saude

            binding.IMCres.text = textoresultado
        }else{
            val textoerro = "Peso ou Altura invalidos."
            binding.IMCres.text = textoerro // mensagem de erro que deveria aparecer no front-end
        }
    }

    private fun obterSaude(imc: Float): String {
        if (imc <= 18.5) { //declara a saúde do usuário
            return "abaixo do peso, regulamente sua alimentação!"
        } else if (imc > 18.5 && imc <= 24.99) {
            return "normal, continue assim!"
        } else if (imc in 25.0..29.99) {
            return "acima do normal, tome cuidado!"
        } else {
            return "alarmante! Procure um nutricionista o quanto antes."
        }
    }
}

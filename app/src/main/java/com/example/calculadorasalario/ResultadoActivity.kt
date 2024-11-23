package com.example.calculadorasalario

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculadorasalario.MainActivity.Companion.DEDUCCIONES
import com.example.calculadorasalario.MainActivity.Companion.RETENCIONES
import com.example.calculadorasalario.MainActivity.Companion.SALARIO_BRUTO
import com.example.calculadorasalario.MainActivity.Companion.SALARIO_NETO

class ResultadoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_resultado)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Recogemos el tv

        val resultTvSalarioBruto = findViewById<TextView>(R.id.tvSalarioBruto)
        val resultTvSalarioNeto = findViewById<TextView>(R.id.tvSalarioNeto)
        val resultTvRetencion = findViewById<TextView>(R.id.tvRetencion)
        val resultTvDeducciones = findViewById<TextView>(R.id.tvDeducciones)

        //Recoger los putExtra de la activity anterior

        val salarioBruto: String = intent.extras?.getString("salarioBruto").orEmpty()
        val salarioNeto: String = intent.extras?.getString(SALARIO_NETO).orEmpty()
        val retenciones: String = intent.extras?.getString(RETENCIONES).orEmpty()
        val deducciones: String = intent.extras?.getString("deducciones").orEmpty()

        //Modificamos el contenido del cvResultado

        resultTvSalarioBruto.text = "El salario bruto es: ${salarioBruto.toString()}"
        resultTvSalarioNeto.text = "El salario neto es: ${salarioNeto.toString()}"
        resultTvRetencion.text = "Las retenciones son: ${retenciones.toString()}"
        resultTvDeducciones.text = "Las deducciones son: ${deducciones.toString()}"



    }
}
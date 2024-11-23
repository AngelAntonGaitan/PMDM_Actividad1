package com.example.calculadorasalario

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.slider.RangeSlider

class  MainActivity : AppCompatActivity() {

    //Creamos variables privadas para recoger los elementos visuales. Inicialización tardía (lazy)
    private lateinit var rsEdad: RangeSlider
    private lateinit var spinnerNumeroPagas: Spinner
    private lateinit var etHijos: EditText
    private lateinit var etsalarioBruto: EditText
    private lateinit var etDiscapacidad: EditText
    private lateinit var spinnerProfesion: Spinner
    private lateinit var etEstadoCivil: EditText
    private lateinit var btnCalcular: Button

    //Creamos los atributos necesarios para lógica de nuestros componentes
    private var currentEdad : Int = 18
    private var currentPagas : Int = 12
    private var currentHijos : Int = 0
    private var currentSalarioBruto : String = "12000"
    private var currentDiscapacidad : Int = 0
    private var currentProfesion  : Int = 0
    private var currentEstadoCivil : Int = 0
    private var currentSalarioNeto = 0
    private var currentRetenciones = 0
    private var currentDeducciones = 0
    var deducciones = 0
    var retenciones = 0
    var salarioNeto = 0

    //Creacción de un companion object que es accesible desde todas las activities
    companion object{
        const val SALARIO_BRUTO = "SalarioBruto_RESULT"
        const val SALARIO_NETO = "salarioNeto_RESULT"
        const val RETENCIONES = "retenciones_RESULT"
        const val DEDUCCIONES = "deducciones_RESULT"
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        this.btnCalcular = findViewById<Button>(R.id.btnCalcular)
        btnCalcular.setOnClickListener{
            rsEdad = findViewById<com.google.android.material.slider.RangeSlider>(R.id.rsEdad)
            spinnerNumeroPagas = findViewById<Spinner>(R.id.spinnerNumeroPagas)
            etHijos = findViewById<EditText>(R.id.etHijos)
            etsalarioBruto = findViewById<EditText>(R.id.etSalarioBruto)
            etDiscapacidad = findViewById<EditText>(R.id.etDiscapacidad)
            spinnerProfesion = findViewById<Spinner>(R.id.spinnerProfesion)
            etEstadoCivil = findViewById<EditText>(R.id.etEstadoCivil)
//            private val salarioNeto = 0
//            private val retenciones = 0
//            private val deducciones = 0

            if(rsEdad!=null && spinnerNumeroPagas!=null && etHijos!=null && etDiscapacidad.text.isNotEmpty() && spinnerProfesion!=null && etEstadoCivil.text.isNotEmpty()){
                currentSalarioBruto=etsalarioBruto.toString()

                //Calcular deducciones
                deducciones = calcularDeducciones(this.currentHijos, this.currentDiscapacidad, this.currentEstadoCivil)


                //Navegar a la siguiente view
                val intent = Intent(this, ResultadoActivity::class.java)

                //Añadimos parametros que queremos pasar a la otra activity
                intent.putExtra("salarioBruto", currentSalarioBruto.toString())
                intent.putExtra("salarioNeto", SALARIO_NETO.toString())
                intent.putExtra("retenciones", RETENCIONES.toString())
                intent.putExtra("deducciones", deducciones.toString())



                //Iniciamos la otra activity
                startActivity(intent)

            }





        }

        val spinner: Spinner = findViewById(R.id.spinnerNumeroPagas)
        val pagas = resources.getStringArray(R.array.NumeroPagasArray)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item,pagas)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner.adapter = adapter

        spinner.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                val selectedItem = pagas[position]
               // Toast.makeText(this@MainActivity,"$selectedItem",Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }






    }

    private fun calcularDeducciones(currentHijos: Int, currentDiscapacidad: Int, currentEstadoCivil: String ): Int {
        if(this.currentHijos > 2){deducciones += 1000}
        if(this.currentDiscapacidad == 1){deducciones += 500}
        else if (this.currentDiscapacidad == 2){deducciones += 1000}
        else if (this.currentDiscapacidad == 3) {deducciones += 1500}
        if (this.etEstadoCivil.equals("soltero")) {
            this.currentEstadoCivil=1
            deducciones += 500
        }
        return deducciones
    }


    //Nuestro código en el OnCreate


        //Para iniciar los componentes visuales
        //initComponents()


        //Para iniciar los listeners de los eventos
       //initListeners()


        //Configuraciones visuales de los componentes
        //initUI()
    }

    private fun initUI(){

    }

//    private fun initListeners(){
//        this.rsEdad.addOnChangeListener{_, value, _ ->
//            this.currentEdad = (value).toInt()
//
//        }




//        this.btnCalcular.setOnClickListener{
//            val salarioNeto = calcularNeto()
//            val retenciones = calcularRetenciones()
//            val deducciones = calcularDeducciones()
//           navigateToResult (salarioNeto,retenciones,deducciones)
//
//
//
//
//
//
//            //Navegación
//        }
//
//
//
//
//
//    }

//    private fun navigateToResult(salarioNeto: Double, retenciones: Double, deducciones: Int) {
//        if(rsEdad !=null && spinnerNumeroPagas!=null && etHijos!=null && etDiscapacidad.text.isNotEmpty() && spinnerProfesion!=null && etEstadoCivil.text.isNotEmpty()){
//
//            //Navegar a la siguiente view
//            val intent = Intent(this, ResultadoActivity::class.java)
//
//            //Añadimos parametros que queremos pasar a la otra activity
//            intent.putExtra(SALARIO_BRUTO, etsalarioBruto.toString())
//            intent.putExtra(SALARIO_NETO, salarioNeto.toString())
//            intent.putExtra(RETENCIONES, retenciones.toString())
//            intent.putExtra(DEDUCCIONES, deducciones.toString())
//
//
//
//            //Iniciamos la otra activity
//            startActivity(intent)
//
//        }
//
//    }
//
//    private fun calcularDeducciones(): Int{
//        if(this.currentHijos > 2){deducciones + 1000}
//        if(this.currentDiscapacidad == 1){deducciones + 500}
//        else if (this.currentDiscapacidad == 2){deducciones + 1000}
//        else if (this.currentDiscapacidad == 3) {deducciones + 1500}
//        if (this.etEstadoCivil.equals("soltero")) {
//            this.currentEstadoCivil=1
//            deducciones + 500
//        }
//        return deducciones
//    }
//
//    private fun calcularRetenciones(): Double {
//
//
//        if (this.currentSalarioBruto <12500){
//            retenciones + (this.currentSalarioBruto*0.19)
//        }
//        if (this.currentSalarioBruto >=12500 &&  this.currentSalarioBruto<=20000){
//            retenciones + (this.currentSalarioBruto*0.24)
//        }
//        if (this.currentSalarioBruto >=20001){
//            retenciones + (this.currentSalarioBruto*0.40)
//        }
//
//            return retenciones.toDouble()
//    }
//
//    private fun calcularNeto(): Double {
//        salarioNeto = this.currentSalarioBruto + deducciones + retenciones
//
//        return salarioNeto.toDouble()
//    }
//
//
//    private fun initComponents(){
//        this.rsEdad = findViewById<com.google.android.material.slider.RangeSlider>(R.id.rsEdad)
//        this.spinnerNumeroPagas = findViewById<Spinner>(R.id.spinnerNumeroPagas)
//        this.etHijos = findViewById<EditText>(R.id.etHijos)
//        this.etsalarioBruto = findViewById<EditText>(R.id.etSalarioBruto)
//        this.etDiscapacidad = findViewById<EditText>(R.id.etDiscapacidad)
//        this.spinnerProfesion  = findViewById<Spinner>(R.id.spinnerProfesion)
//        this.etEstadoCivil = findViewById<EditText>(R.id.etEstadoCivil)
//        this.btnCalcular = findViewById<Button>(R.id.btnCalcular)
//        val Pagas = spinnerNumeroPagas.selectedItem as Int
//
//
//    }






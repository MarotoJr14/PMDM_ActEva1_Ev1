package com.example.miaplicacionapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.miaplicacionapp.databinding.ActivityMainBinding

/**
 * MainActivity: pantalla principal de la aplicación.
 *
 * - Usa ViewBinding (ActivityMainBinding) para acceder a las vistas del layout
 * - Al pulsar el botón btnToPresentation abre PresentationActivity mediante Intent explícito
 */
class MainActivity : AppCompatActivity() {
    // Variable que contendrá la instancia de binding generada para activity_main.xml
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflamos el layout y obtenemos el root view a través de ViewBinding
        // Esto sustituye al uso tradicional de setContentView(R.layout.activity_main) + findViewById.
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configuramos el listener del botón que lleva a PresentationActivity.
        // Intent explícito: se usa la clase de la Activity destino directamente.
        binding.btnToPresentation.setOnClickListener {
            val intent = Intent(this, PresentationActivity::class.java)
            startActivity(intent)
        }
    }
}
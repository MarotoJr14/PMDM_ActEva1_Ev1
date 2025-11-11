package com.example.miaplicacionapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.miaplicacionapp.databinding.ActivityPresentationBinding

/**
 * PresentationActivity: muestra información personal simulada y, opcionalmente,
 * una confirmación si se han pasado datos desde el formulario (ContactActivity).
 *
 * - Usa ViewBinding (ActivityPresentationBinding) para conectar con activity_presentation.xml
 * - Tiene un botón (btnToContact) que lanza ContactActivity
 * - Si recibe datos en el Intent (form_name, form_email, form_message)
 *   las muestra en el TextView tvConfirmation (y lo hace visible)
 */
class PresentationActivity : AppCompatActivity() {
    // Binding para activity_presentation.xml
    private lateinit var binding: ActivityPresentationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflamos el layout mediante ViewBinding y lo asignamos como content view
        binding = ActivityPresentationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Botón que abre el formulario de contacto (ContactActivity)
        binding.btnToContact.setOnClickListener {
            // Intent explícito hacia ContactActivity
            val intent = android.content.Intent(this, ContactActivity::class.java)
            startActivity(intent)
        }

        // Leemos posibles datos enviados desde ContactActivity (Intent extras).
        // Estos extras se nombraron en ContactActivity como "form_name", "form_email", "form_message".
        val fromName = intent.getStringExtra("form_name")
        val fromEmail = intent.getStringExtra("form_email")
        val fromMessage = intent.getStringExtra("form_message")

        // Si al menos uno de los campos no está vacío, mostramos el bloque de confirmación.
        // Construimos un texto con la información recibida y lo asignamos al TextView.
        if (!fromName.isNullOrBlank() || !fromEmail.isNullOrBlank()) {
            val text = StringBuilder()
            text.append("Confirmación de envío:\n")
            text.append("Nombre: ${fromName ?: ""}\n")
            text.append("Email: ${fromEmail ?: ""}\n")
            text.append("Mensaje: ${fromMessage ?: ""}")
            // Usar binding para acceder a la vista y actualizar su contenido
            binding.tvConfirmation.text = text.toString()
            // Por defecto el TextView estaba oculto (visibility="gone"), lo hacemos visible
            binding.tvConfirmation.visibility = android.view.View.VISIBLE
        }
    }
}
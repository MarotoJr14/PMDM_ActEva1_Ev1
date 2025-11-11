package com.example.miaplicacionapp

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.miaplicacionapp.databinding.ActivityContactBinding

/**
 * ContactActivity: formulario de contacto.
 *
 * - Campos de entrada: etName, etEmail, etMessage (definidos en activity_contact.xml)
 * - Validaciones:
 *    * nombre no vacío
 *    * email no vacío y con formato válido (Patterns.EMAIL_ADDRESS)
 *    * mensaje no vacío
 * - Al enviar:
 *    * muestra un Toast de confirmación
 *    * empaqueta los datos en un Intent y vuelve a PresentationActivity
 *    * usa flags para limpiar/back stack opcionalmente y termina la Activity actual
 */
class ContactActivity : AppCompatActivity() {
    // Binding para activity_contact.xml
    private lateinit var binding: ActivityContactBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflamos el layout usando ViewBinding
        binding = ActivityContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Listener del botón de enviar
        binding.btnSend.setOnClickListener {
            // Leemos los valores introducidos por el usuario (trim para quitar espacios)
            val name = binding.etName.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val message = binding.etMessage.text.toString().trim()

            // Validaciones: si falla alguna, marcamos error en el EditText y hacemos focus
            if (name.isEmpty()) {
                binding.etName.error = "El nombre es obligatorio"
                binding.etName.requestFocus()
                return@setOnClickListener
            }

            if (email.isEmpty()) {
                binding.etEmail.error = "El correo es obligatorio"
                binding.etEmail.requestFocus()
                return@setOnClickListener
            }

            // Comprobación del formato de correo usando la utilidad Patterns del SDK
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.etEmail.error = "Formato de correo inválido"
                binding.etEmail.requestFocus()
                return@setOnClickListener
            }

            if (message.isEmpty()) {
                binding.etMessage.error = "Escribe un mensaje"
                binding.etMessage.requestFocus()
                return@setOnClickListener
            }

            // Si llegamos aquí, todas las validaciones han pasado
            // Mostramos un Toast breve como confirmación visual
            Toast.makeText(this, "Enviado: $name — $email", Toast.LENGTH_LONG).show()

            // Preparamos un Intent explícito para volver a PresentationActivity
            val intent = Intent(this, PresentationActivity::class.java).apply {
                putExtra("form_name", name)
                putExtra("form_email", email)
                putExtra("form_message", message)
            }
            // Limpiamos el back stack para que la PresentationActivity que se abra
            // sea la que quede en el tope. Esto evita que al pulsar "atrás" volvamos a este formulario.
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
            // Lanzamos PresentationActivity con los extras
            startActivity(intent)
            // Finalizamos esta activity para liberar recursos y no permitir volver atrás a ella
            finish()
        }
    }
}
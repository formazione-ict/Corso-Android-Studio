package com.example.form_di_login

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.form_di_login.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    // Utilizziamo ViewBinding per un accesso type-safe alle viste (Best Practice)
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            handleLogin()
        }
    }

    private fun handleLogin() {
        val username = binding.etUsername.text.toString()
        val password = binding.etPassword.text.toString()

        // 1. Validazione Form
        if (username.isEmpty()) {
            binding.tilUsername.error = "Inserire username"
            return
        } else {
            binding.tilUsername.error = null
        }

        if (password.length < 6) {
            binding.tilPassword.error = "Password troppo corta (min 6 caratteri)"
            return
        } else {
            binding.tilPassword.error = null
        }

        // 2. Simulazione operazione asincrona (es. chiamata API)
        // Usiamo il lifecycleScope per evitare memory leak se l'activity viene chiusa
        lifecycleScope.launch { 
            // Disabilitiamo il bottone per evitare click multipli durante il "caricamento"
            binding.btnLogin.isEnabled = false

            // Simuliamo una latenza di rete di 1.5 secondi
            delay(1500)

            // 3. Logica di successo
            binding.tvStatusMessage.apply {
                visibility = View.VISIBLE
                text = "Benvenuto $username"
                setTextColor(getColor(android.R.color.holo_green_dark))
            }

            binding.btnLogin.isEnabled = true
        }
    }
}
package com.example.pharmaconnect

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.pharmaconnect.databinding.CommandeClientBinding

class commande_client : AppCompatActivity() {

    private lateinit var binding: CommandeClientBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = CommandeClientBinding.inflate(layoutInflater)

        enableEdgeToEdge()

        setContentView(binding.root)

    }
}
package com.example.pharmaconnect

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.pharmaconnect.databinding.AccueilClientBinding

class accueil_client : AppCompatActivity() {

    private lateinit var binding: AccueilClientBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AccueilClientBinding.inflate(layoutInflater)

        enableEdgeToEdge()

        setContentView(binding.root)

    }
}
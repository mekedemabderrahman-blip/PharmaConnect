package com.example.pharmaconnect

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.pharmaconnect.databinding.MapsClientBinding

class carte_client : AppCompatActivity() {
    private lateinit var binding: MapsClientBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MapsClientBinding.inflate(layoutInflater)

        enableEdgeToEdge()

        setContentView(binding.root)

    }
}
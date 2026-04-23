package com.example.pharmaconnect

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.pharmaconnect.databinding.ProfileClientBinding

class profile_client : AppCompatActivity() {

    private lateinit var binding: ProfileClientBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ProfileClientBinding.inflate(layoutInflater)

        enableEdgeToEdge()

        setContentView(binding.root)

    }
}
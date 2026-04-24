package com.example.pharmaconnect

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.pharmaconnect.databinding.ActivityFourthPageBinding
import com.example.pharmaconnect.databinding.FirstPageBinding
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore

class FourthPage : AppCompatActivity() {

    private lateinit var binding : ActivityFourthPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFourthPageBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(binding.root)


        binding.btnFourthpSignup.setOnClickListener {

            val fullname = binding.etFourthpName.text.toString().trim()
            val email = binding.etFourthpEmail.text.toString().trim()
            val password = binding.etFourthpPassword.text.toString().trim()




            if (fullname.isEmpty()) {
                binding.etFourthpName.error = "Entrez votre nom complet"

            } else if (fullname.length < 3) {
                binding.etFourthpName.error = "Nom trop court"

            } else if (email.isEmpty()) {
                binding.etFourthpEmail.error = "Entrez votre email"

            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.etFourthpEmail.error = "Email invalide"

            } else if (password.isEmpty()) {
                binding.etFourthpPassword.error = "Entrez votre mot de passe"

            } else if (password.length < 6) {
                binding.etFourthpPassword.error = "Minimum 6 caractere"

            } else {
                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(email, password)
                    .addOnSuccessListener { result ->
                        val uid = result.user?.uid ?: return@addOnSuccessListener

                        val client = hashMapOf(
                            "nomComplet" to fullname,
                            "email" to email,
                            "type" to "client"
                        )

                        // 👇 Sauvegarde dans "clients"
                        FirebaseDatabase.getInstance().getReference("clients")
                            .child(uid)
                            .setValue(client)
                            .addOnSuccessListener {
                                Toast.makeText(this, "Compte créé !", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this, accueil_client::class.java)
                                startActivity(intent)
                                finish()
                            }
                            .addOnFailureListener { e ->
                                Toast.makeText(this, "Erreur : ${e.message}", Toast.LENGTH_SHORT).show()
                            }
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(this, "Erreur : ${e.message}", Toast.LENGTH_SHORT).show()
                    }
            }
            binding.ivFourthpageArrow.setOnClickListener {
                finish()
            }


        }
    }
}
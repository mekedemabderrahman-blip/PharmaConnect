package com.example.pharmaconnect

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.pharmaconnect.databinding.ActivityFifthPageBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class FifthPage : AppCompatActivity() {

    private lateinit var binding: ActivityFifthPageBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFifthPageBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance(
            "https://pharmaconnect-4991b-default-rtdb.asia-southeast1.firebasedatabase.app"
        )

        binding.arrowpage5.setOnClickListener {
            finish()
        }

        binding.btnFifthpSignup.setOnClickListener {

            val nomComplet   = binding.nameInputFieldPage5.text.toString().trim()
            val email        = binding.etFifthpEmail.text.toString().trim()
            val nomPharmacie = binding.etFifthpNamepharmacie.text.toString().trim()
            val password     = binding.etFifthpPassword.text.toString().trim()

            if (nomComplet.isEmpty()) {
                binding.nameInputFieldPage5.error = "Entrez votre nom"
                return@setOnClickListener
            }
            if (nomComplet.length < 3) {
                binding.nameInputFieldPage5.error = "Nom trop court"
                return@setOnClickListener
            }
            if (email.isEmpty()) {
                binding.etFifthpEmail.error = "Entrez votre email"
                return@setOnClickListener
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.etFifthpEmail.error = "Email invalide"
                return@setOnClickListener
            }
            if (nomPharmacie.isEmpty()) {
                binding.etFifthpNamepharmacie.error = "Entrez le nom de la pharmacie"
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                binding.etFifthpPassword.error = "Entrez votre mot de passe"
                return@setOnClickListener
            }
            if (password.length < 6) {
                binding.etFifthpPassword.error = "Minimum 6 caractères"
                return@setOnClickListener
            }

            auth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener { result ->
                    val uid = result.user?.uid ?: return@addOnSuccessListener
                    savePharmacieData(uid, nomComplet, email, nomPharmacie)
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Erreur : ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }
    }

    // ✅ Stockage dans la table "pharmacies"
    private fun savePharmacieData(
        uid: String,
        nomComplet: String,
        email: String,
        nomPharmacie: String
    ) {
        val pharmacie = hashMapOf(
            "uid"          to uid,
            "nomComplet"   to nomComplet,
            "email"        to email,
            "nomPharmacie" to nomPharmacie,
            "type"         to "pharmacie"
        )

        database.getReference("pharmacies")
            .child(uid)
            .setValue(pharmacie)
            .addOnSuccessListener {
                Toast.makeText(this, "Compte créé avec succès !", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, SixPage::class.java)
                intent.putExtra("nomPharmacie", nomPharmacie)
                startActivity(intent)
                finish()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Erreur : ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}
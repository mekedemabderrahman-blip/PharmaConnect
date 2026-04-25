package com.example.pharmaconnect

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.pharmaconnect.databinding.ActivityFourthPageBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class FourthPage : AppCompatActivity() {

    private lateinit var binding: ActivityFourthPageBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFourthPageBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance(
            "https://pharmaconnect-4991b-default-rtdb.asia-southeast1.firebasedatabase.app"
        )

        binding.ivFourthpageArrow.setOnClickListener {
            finish()
        }

        binding.btnFourthpSignup.setOnClickListener {

            val fullname = binding.etFourthpName.text.toString().trim()
            val email    = binding.etFourthpEmail.text.toString().trim()
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
                binding.etFourthpPassword.error = "Minimum 6 caractères"
            } else {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnSuccessListener { result ->
                        val uid = result.user?.uid
                        if (uid != null) {
                            saveClientData(uid, fullname, email)
                        }
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(this, "Erreur : ${e.message}", Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }

    private fun saveClientData(uid: String, fullname: String, email: String) {

        // ✅ Données du client
        val client = hashMapOf(
            "uid"        to uid,
            "nomComplet" to fullname,
            "email"      to email,
            "type"       to "client"
        )

        // ✅ Stockage dans la table "clients"
        database.getReference("clients")
            .child(uid)
            .setValue(client)
            .addOnSuccessListener {
                Toast.makeText(this, "Compte créé avec succès !", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, accueil_client::class.java)
                intent.putExtra("name", fullname)
                startActivity(intent)
                finish()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Erreur : ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}
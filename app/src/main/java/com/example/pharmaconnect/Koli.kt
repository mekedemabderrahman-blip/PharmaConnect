package com.example.pharmaconnect

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class koli : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.commande)

        // 📌 UI
        val tvName = findViewById<TextView>(R.id.tvName)
        val tvPrice = findViewById<TextView>(R.id.tvPrice)
        val etQuantity = findViewById<EditText>(R.id.etQuantit)
        val btnCommander = findViewById<Button>(R.id.btnCommande)

        // 📦 récupérer les données du produit (from card click)
        val name = intent.getStringExtra("name")
        val price = intent.getStringExtra("price")

        // 🏷️ afficher les données
        tvName.text = name ?: "Produit inconnu"
        tvPrice.text = price ?: "0 DA"

        // 🛒 bouton commander
        btnCommander.setOnClickListener {

            val qty = etQuantity.text.toString()

            if (qty.isEmpty()) {
                Toast.makeText(this, "Enter quantity", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(
                    this,
                    "Commande: $name x $qty envoyée ✔",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}
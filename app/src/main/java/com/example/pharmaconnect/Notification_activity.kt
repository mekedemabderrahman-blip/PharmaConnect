package com.example.pharmaconnect

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pharmaconnect.databinding.ActivityNotification2Binding

class NotificationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNotification2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNotification2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val list = listOf(
            Notification("Commande confirmée", "Votre commande est validée"),
            Notification("Livraison", "Votre colis est en route"),
            Notification("Promo", "Réduction sur les médicaments"),
            Notification("Stock", "Produit disponible maintenant")
        )

        binding.recyclerNotifications.layoutManager =
            LinearLayoutManager(this)

        binding.recyclerNotifications.adapter =
            NotificationAdapter(list)
    }
}
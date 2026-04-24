package com.example.pharmaconnect

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pharmaconnect.databinding.AccueilVusualisationBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: AccueilVusualisationBinding
    private lateinit var adapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // ViewBinding
        binding = AccueilVusualisationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecycler()
        setupCategories()
        setupSearch()
        binding.notification.setOnClickListener {
            val intent = Intent(this, NotificationActivity::class.java)
            startActivity(intent)
        }
        binding.btnDecouvrir.setOnClickListener {
            adapter.updateList(promoProducts)
        }
    }

    private val allProducts = listOf(
        Product("Doliprane", "500 DA", "Pharmacie Centrale", "SBA", R.drawable.doliprane_background, "medicament", 0),
        Product("Efferalgan", "600 DA", "Pharmacie El Salam", "Oran", R.drawable.efferalgan_background, "medicament", 25),
        Product("Vitamine C", "300 DA", "Pharmacie Ibn Sina", "Alger", R.drawable.vit_background, "vitamine", 0),
        Product("Paracetamol", "400 DA", "Pharmacie El Amal", "Tlemcen", R.drawable.matriel_background, "medicament", 10),
        Product("Smecta", "450 DA", "Pharmacie El Baraka", "Oran", R.drawable.matriel_background, "digestif", 15),
        Product("Augmentin", "900 DA", "Pharmacie Centrale", "Alger", R.drawable.matriel_background, "antibiotique", 30),
        Product("Lait bébé", "1200 DA", "Pharmacie El Salam", "Oran", R.drawable.matriel_background, "bebe", 20),
        Product("Biberon", "800 DA", "Pharmacie Centrale", "SBA", R.drawable.matriel_background, "bebe", 0),
        Product("Thermomètre", "800 DA", "Pharmacie Ibn Sina", "Alger", R.drawable.matriel_background, "materiel", 0),
        Product("Seringue", "200 DA", "Pharmacie El Amal", "Tlemcen", R.drawable.matriel_background, "materiel", 0)
    )

    private val promoProducts = allProducts.filter { it.promotion > 0 }
    private val medicaments = allProducts.filter { it.category == "medicament" }
    private val bebe = allProducts.filter { it.category == "bebe" }
    private val materiel = allProducts.filter { it.category == "materiel" }

    private val soins = allProducts.filter { it.category == "soin" }
    private fun setupRecycler(isHorizontal: Boolean = false) {

        if (isHorizontal) {
            binding.recyclerProducts.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        } else {
            binding.recyclerProducts.layoutManager =
                GridLayoutManager(this, 2)
        }

        adapter = ProductAdapter(allProducts)
        binding.recyclerProducts.adapter = adapter
    }

    private fun setupCategories() {

        binding.catMedic.setOnClickListener {
            adapter.updateList(medicaments)
        }

        binding.catBebe.setOnClickListener {
            adapter.updateList(bebe)
        }

        binding.catMateriel.setOnClickListener {
            adapter.updateList(materiel)
        }
    }
    private fun setupSearch() {

        val productNames = allProducts.map { it.name }

        val adapterSuggest = android.widget.ArrayAdapter(
            this,
            android.R.layout.simple_dropdown_item_1line,
            productNames
        )

        binding.searchAuto.setAdapter(adapterSuggest)

        // 🤍 dropdown background (blanc)
        binding.searchAuto.setDropDownBackgroundDrawable(
            android.graphics.drawable.ColorDrawable(android.graphics.Color.WHITE)
        )

        // 🖤 text color noir داخل AutoComplete
        binding.searchAuto.setTextColor(android.graphics.Color.BLACK)

        // 💙 suggestion selection
        binding.searchAuto.setOnItemClickListener { parent, _, position, _ ->
            val selected = parent.getItemAtPosition(position) as String

            val filtered = allProducts.filter {
                it.name == selected
            }

            adapter.updateList(filtered)
        }

        // 🔎 search live typing
        binding.searchAuto.addTextChangedListener(object : android.text.TextWatcher {

            override fun afterTextChanged(s: android.text.Editable?) {

                val text = s.toString()

                val filtered = allProducts.filter {
                    it.name.contains(text, true)
                }

                adapter.updateList(filtered)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }
}
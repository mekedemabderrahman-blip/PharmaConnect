package com.example.pharmaconnect

data class Product(
    val name: String,
    val price: String,
    val pharmacy: String,
    val location: String,
    val image: Int,
    val category: String,
    val promotion: Int
)
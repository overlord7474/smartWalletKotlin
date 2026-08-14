package com.example.smartwallet.Domain.Models

data class Card(
    val number: String,
    val expiryDate: String,
    val cvv: String
)
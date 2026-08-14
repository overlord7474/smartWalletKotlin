package com.example.smartwallet.Domain.Models

data class Expense(
    val id: Int = 0,
    val title: String,
    val amount: Double,
    val createdAt: Long
)
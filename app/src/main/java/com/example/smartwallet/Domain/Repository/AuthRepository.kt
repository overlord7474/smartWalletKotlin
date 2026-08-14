package com.example.smartwallet.Domain.Repository

import com.example.smartwallet.Domain.Models.User

interface AuthRepository {

    suspend fun login(
        name: String,
        income: Double
    ): User


    suspend fun getCurrentUser(): User?


    suspend fun logout()
}
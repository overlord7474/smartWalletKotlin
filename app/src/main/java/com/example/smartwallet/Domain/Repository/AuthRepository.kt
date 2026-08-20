package com.example.smartwallet.Domain.Repository

import com.example.smartwallet.Domain.Models.User

interface AuthRepository {

    suspend fun login(
        User : User
    )

    suspend fun register(
        User : User
    )

    suspend fun getCurrentUser(): User?


    suspend fun logout() : Boolean
}
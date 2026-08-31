package com.example.smartwallet.Domain.Repository

import com.example.smartwallet.Domain.Models.User

interface AuthRepository {

    suspend fun login(
        user: User
    ): Result<Unit>

    suspend fun register(
        user: User
    ): Result<Unit>

    suspend fun getCurrentUser(): User?

    suspend fun logout(): Result<Unit>
}
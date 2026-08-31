package com.example.smartwallet.Application.UseCase

import com.example.smartwallet.Domain.Models.User
import com.example.smartwallet.Domain.Repository.AuthRepository

class LoginUseCase(
    private val authRepository: AuthRepository
) {
    //'invoke' lets you call this class like a function:
    // loginUseCase(user) instead of loginUseCase.execute(user)
    suspend operator fun invoke(user: User): Result<Unit> {
        // You can add validation rules here later if needed
        // (e.g., check if name is blank before hitting the repo)
        if (user.name.isBlank()) {
            return Result.failure(IllegalArgumentException("Name cannot be blank"))
        }

        return authRepository.login(user)
    }
}
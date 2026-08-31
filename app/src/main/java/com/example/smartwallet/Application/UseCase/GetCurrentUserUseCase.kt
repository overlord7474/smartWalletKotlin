package com.example.smartwallet.Application.UseCase

import com.example.smartwallet.Domain.Models.User
import com.example.smartwallet.Domain.Repository.AuthRepository

class GetCurrentUserUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(): User? {
        val user = authRepository.getCurrentUser()
        if(user != null){
            return user
        }else{
            return null
        }
    }
}
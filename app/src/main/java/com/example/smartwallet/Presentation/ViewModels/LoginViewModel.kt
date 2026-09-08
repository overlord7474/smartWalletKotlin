package com.example.smartwallet.Presentation.ViewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartwallet.Application.UseCase.LoginUseCase
import com.example.smartwallet.Domain.Models.User
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase
): ViewModel() {
    var name by mutableStateOf("")
    var monthlyIncome by mutableStateOf("")
    var errorMessage by mutableStateOf<String?>(null)

    fun onNameChange(newName: String) {
        name = newName
    }
    fun onMonthlyIncomeChange(newMI: String) {
        monthlyIncome = newMI
    }

    fun login(){
        viewModelScope.launch {
            val result = loginUseCase(
                user = User(
                    name = name,
                    monthlyIncome = monthlyIncome.toDoubleOrNull() ?: 0.0
                )
            )
            result.onSuccess {
                // TODO: Trigger navigation to Home!
            }.onFailure { error ->
                // Catch the failure message and show it on the UI
                errorMessage = error.message ?: "Login failed"
            }
        }
    }
}
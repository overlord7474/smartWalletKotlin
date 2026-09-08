package com.example.smartwallet.Presentation.Views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.smartwallet.Presentation.ViewModels.LoginViewModel


// 1. STATEFUL: This connects to your ViewModel for the real app
@Composable
fun LoginScreen(viewModel: LoginViewModel) {
    LoginContent(
        name = viewModel.name,
        monthlyIncome = viewModel.monthlyIncome,
        errorMessage = viewModel.errorMessage,
        onNameChange = { viewModel.onNameChange(it) },
        onMonthlyIncomeChange = { viewModel.onMonthlyIncomeChange(it) },
        onLoginClick = { viewModel.login() }
    )
}

// 2. STATELESS: This only cares about data and callbacks. No ViewModel needed!
@Composable
fun LoginContent(
    name: String,
    monthlyIncome: String,
    errorMessage: String?,
    onNameChange: (String) -> Unit,
    onMonthlyIncomeChange: (String) -> Unit,
    onLoginClick: () -> Unit
) {
    Column {
        TextField(
            value = name,
            onValueChange = onNameChange,
            label = { Text("Name") }
        )
        TextField(
            value = monthlyIncome,
            onValueChange = onMonthlyIncomeChange,
            label = { Text("Monthly Income") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Button(onClick = onLoginClick) {
            Text("Login")
        }

        errorMessage?.let { error ->
            Text(text = error, color = Color.Red)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    // You can see how it looks normally
    LoginContent(
        name = "John Doe",
        monthlyIncome = "3000",
        errorMessage = null,
        onNameChange = {},
        onMonthlyIncomeChange = {},
        onLoginClick = {}
    )
}

@Preview(showBackground = true)
@Composable
fun LoginScreenErrorPreview() {
    // Or preview what it looks like when an error happens!
    LoginContent(
        name = "John Doe",
        monthlyIncome = "invalid",
        errorMessage = "User not found",
        onNameChange = {},
        onMonthlyIncomeChange = {},
        onLoginClick = {}
    )
}

package com.example.jetpackapp.loginandsignup


class PasswordState : TextFieldState(
    validator = ::isPasswordValid,
    errorMessage = { passwordErrorMessage() }
)

private fun isPasswordValid(password: String) = password.length >= 4


private fun passwordErrorMessage() = "Password is invalid"
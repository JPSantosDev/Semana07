package com.example.aprenderacesso.ui.state

data class FormState(
    val loginMode: Boolean = true,

    val name: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",

    val acceptedTerms: Boolean = false,

    val passwordVisible: Boolean = false,
    val confirmPasswordVisible: Boolean = false,

    val nameError: String? = null,
    val emailError: String? = null,
    val passwordError: String? = null,
    val confirmPasswordError: String? = null,
    val termsError: String? = null
)
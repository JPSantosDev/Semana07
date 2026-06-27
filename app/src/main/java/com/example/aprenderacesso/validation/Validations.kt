package com.example.aprenderacesso.validation


data class ValidationResult(
    val isValid: Boolean,
    val message: String?
)
object Validations{

    fun validateName(name:String) : ValidationResult {
        return when{
            name.trim().isBlank() -> ValidationResult(false, "O nome não pode estar em branco")
            name.trim().length < 3 -> ValidationResult(false, "O nome deve ter pelo menos 3 caracteres")
            name.trim().split(" ").filter { it.isNotBlank() }.size < 2 -> ValidationResult(false, "O nome deve conter pelo menos um sobrenome")
            else -> ValidationResult(true, null)
        }
    }

    fun validateEmail(email:String) : ValidationResult {
        return when{
            email.trim().isBlank() -> ValidationResult(false, "O email não pode estar em branco")
            "@" !in email.trim() -> ValidationResult(false, "O email deve conter um caractere @")
            "." !in email.trim().substringAfter("@","") -> ValidationResult(false, "O dominio do email deve conter ponto")
            else -> ValidationResult(true, null)
        }
    }

    fun validatePassword(password:String) : ValidationResult {
        val hasLetter = password.any() { it.isLetter() }
        val hasDigit = password.any() { it.isDigit() }
        return when{
            password.trim().isBlank() -> ValidationResult(false, "A senha não pode estar em branco")
            password.trim().length < 6 -> ValidationResult(false, "A senha deve ter pelo menos 6 caracteres")
            !hasLetter || !hasDigit -> ValidationResult(false, "A senha deve conter letras e numeros")
            else -> ValidationResult(true, null)
        }
    }

    fun validateConfirmPassword(confirmPassword:String, password:String) : ValidationResult {
        return when{
            confirmPassword.isBlank() -> ValidationResult(false, "Confirme a senha")
            !confirmPassword.equals(password) -> ValidationResult(false, "As senhas não coincidem")
            else -> ValidationResult(true, null)
        }
    }
}
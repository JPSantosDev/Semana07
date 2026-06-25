package com.example.aprenderacesso.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.example.aprenderacesso.ui.theme.Typography

@Composable
fun FormularioLogin(
    valorEmail: String,
    valorEmailChange: (String) -> Unit,
    valorSenha: String,
    valorSenhaChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var check by remember { mutableStateOf(false) }


    Column(modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = valorEmail,
            onValueChange = valorEmailChange,
            label = {Text(text = "Email") },
            placeholder = {Text(text = "Digite seu email") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            )
        )

        OutlinedTextField(
            value = valorSenha,
            onValueChange = valorSenhaChange,
            label = {Text(text = "Senha") },
            placeholder = {Text(text = "Digite sua senha") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            isError = valorSenha.length < 6,
        )

        Row(modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,) {
            Checkbox(
            checked = check,
            onCheckedChange = { check = !check }
            )
            Text(text = "Manter-me conectado",
                style = Typography.bodyMedium
            )
        }
    }
}

@Composable
@Preview (showBackground = true)
fun FormularioLoginPreview() {

    var valorEmail by remember { mutableStateOf("") }
    var valorSenha by remember { mutableStateOf("") }

    FormularioLogin(
        valorEmail = valorEmail,
        valorSenha = valorSenha,
        valorEmailChange = { valorEmail = it },
        valorSenhaChange = { valorSenha = it }
    )

}
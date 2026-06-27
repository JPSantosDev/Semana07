package com.example.aprenderacesso.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.aprenderacesso.ui.theme.Typography
import com.example.aprenderacesso.ui.state.FormState
import com.example.aprenderacesso.validation.Validations
import kotlinx.coroutines.launch

@Composable
fun FormularioLogin(
    navController: NavController,
    state: FormState,
    valorEmailChange: (String) -> Unit,
    valorSenhaChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    validations: Validations
) {
    var check by remember { mutableStateOf(false) }
    var senhaVisivel by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(snackbarHost = { SnackbarHost(hostState = snackbarHostState) }) { innerPadding->
        Column(modifier.fillMaxWidth().padding(innerPadding), horizontalAlignment = Alignment.CenterHorizontally) {
            OutlinedTextField(
                value = state.email,
                onValueChange = valorEmailChange,
                label = { Text(text = "Email") },
                placeholder = { Text(text = "Digite seu email") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                ),
                isError = !validations.validateEmail(state.email).isValid,
                supportingText = {
                    if (!validations.validateEmail(state.email).isValid) {
                        Text(
                            text = validations.validateEmail(state.email).message ?: "",
                            style = Typography.bodySmall,
                            color = MaterialTheme.colorScheme.error,
                            textAlign = TextAlign.Start
                        )
                    } else {
                        Text("")
                    }
                }
            )
            OutlinedTextField(
                value = state.password,
                onValueChange = valorSenhaChange,
                label = { Text(text = "Senha") },
                placeholder = { Text(text = "Digite sua senha") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                isError = !validations.validatePassword(state.password).isValid,
                supportingText = {
                    if (!validations.validatePassword(state.password).isValid) {
                        Text(
                            text = validations.validatePassword(state.password).message ?: "",
                            style = Typography.bodySmall,
                            color = MaterialTheme.colorScheme.error,
                            textAlign = TextAlign.Start
                        )

                    } else {
                        Text("")
                    }
                },
                visualTransformation = if (senhaVisivel) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { senhaVisivel = !senhaVisivel }) {
                        Icon(
                            imageVector = if (senhaVisivel) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                            contentDescription = if (senhaVisivel) "Ocultar senha" else "Mostrar senha"
                        )
                    }
                }
            )

            Row(
                modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Checkbox(
                    checked = check,
                    onCheckedChange = { check = !check }
                )
                Text(
                    text = "Manter-me conectado",
                    style = Typography.bodyMedium
                )
            }
            Column(
                modifier = modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                Text(
                    modifier = modifier.clickable(onClick = { state.loginMode = false }),
                    text = "Criar conta",
                    color = Color.Blue
                )
            }
            Row() {
                Button(onClick = {
                    if (validations.validateEmail(state.email).isValid && validations.validatePassword(state.password).isValid)
                        scope.launch { snackbarHostState.showSnackbar("Login validado com sucesso") }
                    else {
                        scope.launch { snackbarHostState.showSnackbar("Dados de login inválidos") }
                    }
                }) {
                    Text("Entrar")
                }

                Spacer(modifier = modifier.width(8.dp))

                Button(onClick = {}) {
                    Text("Cancelar")
                }
            }
        }
    }
}


@Composable
@Preview (showBackground = true)
fun FormularioLoginPreview() {

    val validations = Validations()

    var valorEmail by remember { mutableStateOf("") }
    var valorSenha by remember { mutableStateOf("") }
    val navController = rememberNavController()

    FormularioLogin(
        navController = navController,
        state = FormState(
            email = valorEmail,
            password = valorSenha
        ),
        valorEmailChange = { valorEmail = it },
        valorSenhaChange = { valorSenha = it },
        validations = validations

    )
}
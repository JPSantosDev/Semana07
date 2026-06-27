package com.example.aprenderacesso.ui.screens

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
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
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
import androidx.compose.ui.unit.dp
import com.example.aprenderacesso.ui.theme.Typography
import com.example.aprenderacesso.ui.state.FormState
import com.example.aprenderacesso.validation.Validations
import kotlinx.coroutines.launch

@Composable
fun AccessScreen(
    modifier: Modifier = Modifier,
    state: FormState,
    valorEmailChange: (String)-> Unit,
    valorPasswordChange: (String) -> Unit,
    valorPasswordConfirmChange: (String) -> Unit
){

    if(state.loginMode){
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
                    isError = !Validations.validateEmail(state.email).isValid,
                    supportingText = {
                        if (!Validations.validateEmail(state.email).isValid) {
                            Text(
                                text = Validations.validateEmail(state.email).message ?: "",
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
                    onValueChange = valorPasswordChange,
                    label = { Text(text = "Senha") },
                    placeholder = { Text(text = "Digite sua senha") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                    ),
                    isError = !Validations.validatePassword(state.password).isValid,
                    supportingText = {
                        if (!Validations.validatePassword(state.password).isValid) {
                            Text(
                                text = Validations.validatePassword(state.password).message ?: "",
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
                        modifier = modifier.clickable(onClick = {state.copy(loginMode = false)}),
                        text = "Criar conta",
                        color = Color.Blue
                    )
                }
                Row() {
                    Button(onClick = {
                        if (Validations.validateEmail(state.email).isValid && Validations.validatePassword(state.password).isValid)
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
    else {

        var termosCheck by remember { mutableStateOf(false) }
        var senhaConfirmarVisivel by remember {mutableStateOf(false)}
        var senhaVisivel by remember {mutableStateOf(false)}

        Scaffold() { innerPadding ->
            Column(modifier = modifier
                .fillMaxWidth()
                .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally) {

                OutlinedTextField(
                    value = state.name,
                    onValueChange = valorEmailChange,
                    placeholder = { Text("Coloque seu nome") },
                    label = {Text("Nome & Sobrenome")},
                    singleLine = true,
                    isError = !Validations.validateName(state.name).isValid,
                    trailingIcon = { Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "Nome"
                    ) },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text
                    ),
                    supportingText = {
                        Text(
                            text = Validations.validateName(state.name).message ?: ""
                        )
                    }
                )

                OutlinedTextField(
                    value = state.email,
                    onValueChange = valorEmailChange,
                    placeholder = { Text("Coloque seu email") },
                    label = {Text("Email")},
                    singleLine = true,
                    isError = !Validations.validateEmail(state.email).isValid,
                    trailingIcon = { Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Email"
                    ) },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email
                    ),
                    supportingText = {
                        Text(
                            text = Validations.validateEmail(state.email).message ?: ""
                        )
                    }
                )

                OutlinedTextField(
                    value = state.password,
                    onValueChange = valorPasswordChange,
                    placeholder = { Text("Coloque sua senha") },
                    label = {Text("Senha")},
                    singleLine = true,
                    isError = !Validations.validatePassword(state.password).isValid,
                    trailingIcon = { IconButton(onClick = { senhaVisivel = !senhaVisivel }) { Icon(
                        imageVector = if(senhaVisivel) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = if (senhaVisivel) "Ocultar senha" else "Mostrar senha"
                    ) }},
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                    ),
                    supportingText = {
                        Text(
                            text = Validations.validatePassword(state.password).message ?: ""
                        )
                    }


                )

                OutlinedTextField(
                    value = state.password,
                    onValueChange = valorPasswordConfirmChange,
                    placeholder = { Text("Confirme sua senha") },
                    label = {Text("Confirme a senha")},
                    singleLine = true,
                    isError = !Validations.validateConfirmPassword(state.confirmPassword,state.password).isValid,
                    supportingText = {if(!Validations.validateConfirmPassword(state.confirmPassword,state.password).isValid){
                        Text(
                            text = Validations.validateConfirmPassword(state.confirmPassword,state.password).message ?: "",
                            style = Typography.bodySmall,
                            color = MaterialTheme.colorScheme.error,
                            textAlign = TextAlign.Start
                        )
                    }},
                    trailingIcon = { Icon(
                        imageVector = Icons.Default.Visibility,
                        contentDescription = "Senha"
                    ) },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                    ),
                    visualTransformation = if(senhaConfirmarVisivel) VisualTransformation.None else PasswordVisualTransformation()
                )

                Row(modifier = modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center) {
                    Checkbox(
                        checked = termosCheck,
                        onCheckedChange = {termosCheck = !termosCheck},
                    )
                    Text(text = "Aceito os termos de uso")
                }

                Row(modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center) {
                    Button(onClick = {}) {
                        Text("Cadastrar")
                    }
                    Button(onClick = {}) {
                        Text("Cancelar")
                    }
                }

                Column(modifier = modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Já tenho conta",
                        modifier = modifier
                            .clickable(onClick = {state.copy(loginMode = true)}),
                        color = Color.Blue
                    )
                }
            }
        }
    }
}
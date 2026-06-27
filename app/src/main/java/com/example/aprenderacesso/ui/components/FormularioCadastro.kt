package com.example.aprenderacesso.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.aprenderacesso.ui.theme.Typography
import com.example.aprenderacesso.ui.state.FormState
import com.example.aprenderacesso.validation.Validations

@Composable
fun FormularioCadastro(
    navController: NavController,
    state: FormState,
    validations: Validations,
    modifier: Modifier = Modifier,
    valorEmailChange: (String)-> Unit,
    valorPasswordChange: (String) -> Unit,
    valorPasswordConfirmChange: (String) -> Unit
){
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
                isError = !validations.validateName(state.name).isValid,
                trailingIcon = { Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Nome"
                ) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                ),
                supportingText = {
                    Text(
                       text = validations.validateName(state.name).message ?: ""
                    )
                }
            )

            OutlinedTextField(
                value = state.email,
                onValueChange = valorEmailChange,
                placeholder = { Text("Coloque seu email") },
                label = {Text("Email")},
                singleLine = true,
                isError = !validations.validateEmail(state.email).isValid,
                trailingIcon = { Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "Email"
                ) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                ),
                supportingText = {
                    Text(
                        text = validations.validateEmail(state.email).message ?: ""
                    )
                }
            )

            OutlinedTextField(
                value = state.password,
                onValueChange = valorPasswordChange,
                placeholder = { Text("Coloque sua senha") },
                label = {Text("Senha")},
                singleLine = true,
                isError = !validations.validatePassword(state.password).isValid,
                trailingIcon = { IconButton(onClick = { senhaVisivel = !senhaVisivel }) { Icon(
                    imageVector = if(senhaVisivel) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                    contentDescription = if (senhaVisivel) "Ocultar senha" else "Mostrar senha"
                ) }},
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                supportingText = {
                    Text(
                        text = validations.validatePassword(state.password).message ?: ""
                    )
                }


            )

            OutlinedTextField(
                value = state.password,
                onValueChange = valorPasswordConfirmChange,
                placeholder = { Text("Confirme sua senha") },
                label = {Text("Confirme a senha")},
                singleLine = true,
                isError = !validations.validateConfirmPassword(state.confirmPassword,state.password).isValid,
                supportingText = {if(!validations.validateConfirmPassword(state.confirmPassword,state.password).isValid){
                    Text(
                        text = validations.validateConfirmPassword(state.confirmPassword,state.password).message ?: "",
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
                        .clickable(onClick = {state.copy(loginMode = true) }),
                    color = Color.Blue
                )
            }

        }
    }
}

@Composable
@Preview(showBackground = true)

fun Preview(){
    var valorEmail by remember { mutableStateOf("") }
    var valorSenha by remember { mutableStateOf("") }
    var valorConfirmSenha by remember { mutableStateOf("") }
    val navController = rememberNavController()
    FormularioCadastro(
        navController = navController,
        state = FormState(
            email = valorEmail,
            password = valorSenha,
            confirmPassword = valorConfirmSenha

        ),
        valorEmailChange = {valorEmail = it},
        valorPasswordChange = {valorEmail = it},
        valorPasswordConfirmChange = {valorConfirmSenha = it},
        validations = Validations

    )
}
package com.example.aprenderacesso.ui.components

import android.inputmethodservice.Keyboard
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
import androidx.compose.material.icons.filled.Key
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
import com.example.aprenderacesso.navigation.Routes
import com.example.aprenderacesso.ui.theme.Typography
import com.example.aprenderacesso.validation.FormState
import com.example.aprenderacesso.validation.validateConfirmPassword
import com.example.aprenderacesso.validation.validateEmail
import com.example.aprenderacesso.validation.validateName
import com.example.aprenderacesso.validation.validatePassword

@Composable
fun FormularioCadastro(
    navController: NavController,
    state: FormState,
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
                value = state.nameCadastro,
                onValueChange = valorEmailChange,
                placeholder = { Text("Coloque seu nome") },
                label = {Text("Nome & Sobrenome")},
                singleLine = true,
                isError = !validateName(state.nameCadastro).isValid,
                trailingIcon = { Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Nome"
                ) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                ),
                supportingText = {
                    Text(
                       text = validateName(state.nameCadastro).message ?: ""
                    )
                }
            )

            OutlinedTextField(
                value = state.emailCadastro,
                onValueChange = valorEmailChange,
                placeholder = { Text("Coloque seu email") },
                label = {Text("Email")},
                singleLine = true,
                isError = !validateEmail(state.emailCadastro).isValid,
                trailingIcon = { Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "Email"
                ) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                ),
                supportingText = {
                    Text(
                        text = validateEmail(state.emailCadastro).message ?: ""
                    )
                }
            )

            OutlinedTextField(
                value = state.passwordCadastro,
                onValueChange = valorPasswordChange,
                placeholder = { Text("Coloque sua senha") },
                label = {Text("Senha")},
                singleLine = true,
                isError = !validatePassword(state.passwordCadastro).isValid,
                trailingIcon = { IconButton(onClick = { senhaVisivel = !senhaVisivel }) { Icon(
                    imageVector = if(senhaVisivel) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                    contentDescription = if (senhaVisivel) "Ocultar senha" else "Mostrar senha"
                ) }},
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                supportingText = {
                    Text(
                        text = validatePassword(state.passwordCadastro).message ?: ""
                    )
                }


            )

            OutlinedTextField(
                value = state.passwordCadastro,
                onValueChange = valorPasswordConfirmChange,
                placeholder = { Text("Confirme sua senha") },
                label = {Text("Confirme a senha")},
                singleLine = true,
                isError = !validateConfirmPassword(state.confirmPasswordCadastro,state.passwordCadastro).isValid,
                supportingText = {if(!validateConfirmPassword(state.confirmPassword,state.confirmPasswordCadastro).isValid){
                    Text(
                        text = validateConfirmPassword(state.confirmPasswordCadastro,state.passwordCadastro).message ?: "",
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
                        .clickable(onClick = {navController.navigate(Routes.LOGIN)}),
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
            emailCadastro = valorEmail,
            passwordCadastro = valorSenha,
            confirmPasswordCadastro = valorConfirmSenha

        ),
        valorEmailChange = {valorEmail = it},
        valorPasswordChange = {valorEmail = it},
        valorPasswordConfirmChange = {valorConfirmSenha = it}

    )
}
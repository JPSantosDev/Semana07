package com.example.aprenderacesso

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.aprenderacesso.ui.screens.AccessScreen
import com.example.aprenderacesso.ui.theme.AprenderAcessoTheme
import com.example.aprenderacesso.ui.state.FormState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AprenderAcessoTheme {
                AccessScreen(
                    state = FormState(),
                    valorPasswordChange = {},
                    valorPasswordConfirmChange = {},
                    valorEmailChange = {}
                )
            }
        }
    }
}
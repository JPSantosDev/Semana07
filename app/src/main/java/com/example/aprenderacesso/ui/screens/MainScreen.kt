package com.example.aprenderacesso.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.aprenderacesso.ui.theme.Typography

@Composable
fun MainScreen(
    modifier: Modifier = Modifier
){
    Scaffold(modifier.fillMaxSize()) { innerPadding ->
        Column(modifier
            .fillMaxWidth()
            .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Aprender+ Acesso",
                style = Typography.titleLarge
            )
            Text(text = "Uma plataforma para acesso a conteúdos de aprendizagem",
            style = Typography.bodyMedium
        )

        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen()
}
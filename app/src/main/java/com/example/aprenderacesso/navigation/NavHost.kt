package com.example.aprenderacesso.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.aprenderacesso.ui.screens.CadastroScreen
import com.example.aprenderacesso.ui.screens.LoginScreen

@Composable
fun NavHost(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.LOGIN
    ){
        composable(Routes.LOGIN){
            LoginScreen(navController = navController)
        }
        composable(Routes.CADASTRO){
            CadastroScreen(navController = navController)
        }
    }
}
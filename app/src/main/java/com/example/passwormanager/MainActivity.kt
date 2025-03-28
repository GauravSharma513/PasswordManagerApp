package com.example.passwormanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.passwormanager.home.presentation.ErrorScreen
import com.example.passwormanager.home.presentation.HomeScreen
import com.example.passwormanager.home.presentation.LoadingScreen
import com.example.passwormanager.home.presentation.LoginScreen
import com.example.passwormanager.home.presentation.SignUpScreen
import com.example.passwormanager.ui.theme.AuthViewModelFactory
import com.example.passwormanager.ui.theme.PassworManagerTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val authViewModel:AuthViewModel = viewModel(factory = AuthViewModelFactory(navController))
            PassworManagerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    //HomeScreen()
                    NavigationGraph(navController = navController, authViewModel = authViewModel) {
                        finish()
                    }
                }
            }
        }
    }
}

@Composable
fun NavigationGraph(
    authViewModel: AuthViewModel,
    navController: NavHostController,
    onFinish: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = Screen.LoginScreen.route
    ) {
        composable(Screen.SignUpScreen.route) {
            SignUpScreen(
                authViewModel = authViewModel,
                onNavigateToLogin = { navController.navigate(Screen.LoginScreen.route) }
            )
        }
        composable(Screen.LoginScreen.route) {
            LoginScreen(
                authViewModel = authViewModel,
                onNavigateToSignUp = { navController.navigate(Screen.SignUpScreen.route) },
                onFinish = onFinish
            )
        }
        composable(Screen.LoadingScreen.route) {
            LoadingScreen(
                authViewModel = authViewModel
            )
        }

        composable(Screen.HomeScreen.route) {
            HomeScreen(onFinish)
        }
        composable(Screen.ErrorScreen.route) {
            backStackEntry ->
            val errorMessage = backStackEntry.arguments?.getString("errorMessage")
            val screenSource = backStackEntry.arguments?.getString("screenSource")
            ErrorScreen(navController,errorMessage, screenSource)
        }
    }
}



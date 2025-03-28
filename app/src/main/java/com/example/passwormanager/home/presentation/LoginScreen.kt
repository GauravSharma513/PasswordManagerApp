package com.example.passwormanager.home.presentation

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.passwormanager.AuthViewModel

@Composable
fun LoginScreen(
    authViewModel: AuthViewModel,
    onNavigateToSignUp: () -> Unit,
    onFinish: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var isSignUpSuccessful by remember{ authViewModel.isSignUpSuccessful }



    if (isSignUpSuccessful){
        Toast.makeText(LocalContext.current,"User Registered Successfully",Toast.LENGTH_SHORT).show()
        authViewModel.isSignUpSuccessful.value=false
    }



    val isInvalidEmail by remember {
        authViewModel.isInvalidEmail
    }


    if (isInvalidEmail) {
        Toast.makeText(LocalContext.current, "Email not valid", Toast.LENGTH_SHORT).show()
       authViewModel.isInvalidEmail.value=false
    }



    BackHandler {
        // exit the app
        onFinish()
    }

    Scaffold { it ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image = if (passwordVisible)
                        Icons.Filled.Visibility
                    else
                        Icons.Filled.VisibilityOff

                    IconButton(onClick = {
                        passwordVisible = !passwordVisible
                    }) {
                        Icon(imageVector = image, contentDescription = null)
                    }
                }
            )
            Button(
                // modifier = Modifier.background(),
                onClick = {
                    authViewModel.login(email, password)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text("Login")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "Don't have an account? Sign up.",
                modifier = Modifier.clickable {
                    onNavigateToSignUp()
                }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    val navController = rememberNavController() // Create a mock NavHostController for the preview

    LoginScreen(
        authViewModel = AuthViewModel(navController), // Pass the mock NavHostController
        onNavigateToSignUp = { /* Do nothing for preview */ },
        onFinish = { /* Do nothing for preview */ }
    )
}


//
//@Preview
//@Composable
//fun LoginPreview() {
//    //LoginScreen( {})
//}
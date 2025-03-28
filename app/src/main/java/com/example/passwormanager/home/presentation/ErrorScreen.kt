package com.example.passwormanager.home.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.passwormanager.Screen


@Composable
fun ErrorScreen(
    navHostController: NavHostController,
    errorMessage: String?,
    screenSource:String?

) {

    BackHandler {
        if (screenSource.equals("login")){
            navHostController.navigate(Screen.LoginScreen.route)
        }
        else{
            navHostController.navigate((Screen.SignUpScreen.route))

        }

    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = errorMessage ?: "An unknown error occurred.",
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 16.dp)
            )

        }
    }
}
//
//@Preview(showBackground = true)
//@Composable
//fun ErrorScreenPreview() {
//    ErrorScreen(
//        errorMessage = "Firebase Authentication failed: Invalid credentials.",
//
//    )
//}

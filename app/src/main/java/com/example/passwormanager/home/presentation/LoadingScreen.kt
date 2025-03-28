package com.example.passwormanager.home.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Scaffold
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.passwormanager.AuthViewModel

@Composable
fun LoadingScreen(authViewModel: AuthViewModel) {
    BackHandler {
        // do nothing
    }
    Scaffold { it ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White), // Set the background color
            contentAlignment = Alignment.Center // Center the content
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Circular Progress Indicator
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.primary, // Customize the color if needed
                    strokeWidth = 4.dp // Thickness of the indicator
                )
                Spacer(modifier = Modifier.height(16.dp)) // Add spacing between the progress bar and text
                // Loading Text
                Text(
                    text = "Loading...",
                    style = MaterialTheme.typography.bodyMedium, // Use a text style from the theme
                    color = MaterialTheme.colorScheme.onBackground // Text color
                )
            }
        }
    }
}

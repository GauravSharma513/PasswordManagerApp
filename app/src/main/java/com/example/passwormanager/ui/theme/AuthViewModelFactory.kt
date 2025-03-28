package com.example.passwormanager.ui.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import com.example.passwormanager.AuthViewModel

@Suppress("UNCHECKED_CAST")
class AuthViewModelFactory(private val navHostController: NavHostController): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            return AuthViewModel(navHostController = navHostController) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
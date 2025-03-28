package com.example.passwormanager

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.passwormanager.home.domain.UserRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class AuthViewModel (val navHostController: NavHostController) : ViewModel() {

    var isInvalidEmail = mutableStateOf<Boolean>(false)
    var isInvalidPassword = mutableStateOf<Boolean>(false)
    var isSignUpSuccessful = mutableStateOf(false)
    private val userRepository: UserRepository

    init {
        userRepository = UserRepository(
            FirebaseAuth.getInstance(),
            Injection.instance()
        )
    }

    fun signUp(email: String, password: String, firstName: String, lastName: String) {
        Log.d("SignUp", "$email,$password ")
        if(isValid(email,password)) {
            viewModelScope.launch {
                navHostController.navigate(Screen.LoadingScreen.route)
                val result = userRepository.signUp(email, password, firstName, lastName)
                 result.onSuccess {
                     isSignUpSuccessful.value= true
                     navHostController.navigate(Screen.LoginScreen.route)
                 }.onFailure { exception ->
                     val errorMessage = exception.message ?:"an unknown error occurred."
                     navHostController.navigate(Screen.ErrorScreen.createRoute(errorMessage, screenSource = "signUp"))

                 }



                }
            }
        }



    fun login(email: String, password: String) {
        if(isEmailValid(email)) {
            viewModelScope.launch {
                navHostController.navigate(Screen.LoadingScreen.route)
                val result = userRepository.login(email, password)
                if (result.isSuccess) {
                    navHostController.navigate(Screen.HomeScreen.route)
                }
                else{
                    val exception = result.exceptionOrNull()
                    val errorMessage = exception?.message?:"An unknown error occurred"
                    navHostController.navigate(Screen.ErrorScreen.createRoute(errorMessage, screenSource = "login"))
                }
            }
        }
    }

    fun isEmailValid(email: String): Boolean {
        // Regex for email validation
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()
        val isEmailValid = email.matches(emailRegex)
        if(isEmailValid.not()) {
            isInvalidEmail.value = true
            return false
        }
        isInvalidEmail.value = false
        return true
    }

    fun isPasswordValid(password: String): Boolean {
        // Regex for password validation:
        // - At least 6 characters
        // - At least one uppercase letter
        // - At least one digit
        // - At least one special character
        val passwordRegex = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%^&*()_+=<>?~`{}\\[\\]|\\\\:;\"',./-]).{6,}$".toRegex()
        val isPasswordValid = password.matches(passwordRegex)
        if(isPasswordValid.not()) {
            isInvalidPassword.value = true
            return false
        }
        isInvalidPassword.value = false
        return true
    }

    fun isValid(email: String, password: String): Boolean {
        return isEmailValid(email) && isPasswordValid(password)
    }
}


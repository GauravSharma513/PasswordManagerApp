package com.example.passwormanager

import android.net.Uri

sealed class Screen(val route:String) {
    object LoginScreen : Screen("loginscreen")
    object SignUpScreen : Screen("signupscreen")
    object LoadingScreen : Screen("loadingscreen")
    object HomeScreen : Screen("homescreen")
    object ErrorScreen : Screen("errorscreen/{errorMessage}/{screenSource}"){
        fun createRoute(errorMessage:String,screenSource:String):String {

            return "errorscreen/${Uri.encode(errorMessage)}/${screenSource}"
        }
    }
}
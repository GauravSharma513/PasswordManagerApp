package com.example.passwormanager.home.domain

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import android.util.Log
import com.example.passwormanager.home.data.User
import kotlinx.coroutines.tasks.await

class UserRepository(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) {
    private val TAG = "UserRepository"
    suspend fun signUp(
        email: String,
        password: String,
        firstName: String,
        lastName: String
    ): Result<Boolean> =
        try {
            auth.createUserWithEmailAndPassword(email, password).await()
            Log.d(TAG, "user created on firebase successfully")

            val user = User(
                firstName = firstName,
                lastName = lastName,
                email = email
            )
            saveUserToFirestore(user)
            Result.success(true)
        } catch (e: Exception) {
            Log.d(TAG, "user creation failed on firebase")
            Result.failure(e)
        }


    suspend fun login(email: String, password: String): Result<Boolean> =
        try {
            auth.signInWithEmailAndPassword(email, password).await()
            Log.d(TAG, "firebase login successful")
            Result.success(true)
        } catch (e: Exception) {
            Log.d(TAG, "firebase login failed")
            Result.failure(e)
        }


    private suspend fun saveUserToFirestore(user: User) {
        try {
            firestore.collection("users").document(user.email).set(user).await()
            Log.d(TAG, "User saved to firestore successfully")
        } catch (e: Exception) {
            Log.d(TAG, "User saving to firestore failed. Reason: $e")
        }
    }

    suspend fun getCurrentUser(): Result<User> = try {
        val uid = auth.currentUser?.email
        if (uid != null) {
            val userDocument = firestore.collection("users").document(uid).get().await()
            val user = userDocument.toObject(User::class.java)
            if (user != null) {
                Log.d("user2", "$uid")
                Result.success(user)
            } else {
                Result.failure(Exception("User data not found"))
            }
        } else {
            Result.failure(Exception("User not authenticated"))
        }
    } catch (e: Exception) {
        Result.failure(e)
    }
}
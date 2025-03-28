package com.example.passwormanager.home.presentation

import android.util.Base64
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.passwormanager.home.data.Entity
import com.example.passwormanager.home.domain.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {


    private val _passwordList = MutableStateFlow<List<Entity>>(emptyList())
    val passwordList = _passwordList.asStateFlow()
    val loading = mutableStateOf(false)

    init {
        viewModelScope.launch (Dispatchers.IO){
            repository.getAllPassword().distinctUntilChanged()
                .collect{listOfPassword->

                    if(listOfPassword.isNullOrEmpty()){
                        Log.d("TAG","EmptyList")
                    }else{
                        _passwordList.value = listOfPassword
                    }
                }
        }
    }

    fun addPassword(entity: Entity)=viewModelScope.launch {
        loading.value = true
        delay(2000L)
        repository.addPassword(entity)
        loading.value = false
    }

    fun delete(entity: Entity)=viewModelScope.launch {
        loading.value = true
        delay(2000L)
        repository.deletedPassword(entity)
        loading.value = false
    }

    fun update(entity: Entity)=viewModelScope.launch {
        loading.value = true
        delay(2000L)
        repository.updatePassword(entity)
        loading.value = false
    }

    //encryption and decryption

    private var key : String="mysecretkey12345"
    private var secretKeySpec = SecretKeySpec(key.toByteArray(),"AES")
    fun encrypt(string: String) : String{

        val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding") //Specifying which mode of AES is to be used
        cipher.init(Cipher.ENCRYPT_MODE,secretKeySpec)// Specifying the mode wither encrypt or decrypt
        val encryptBytes =cipher.doFinal(string.toByteArray(Charsets.UTF_8))//Converting the string that will be encrypted to byte array
        return Base64.encodeToString(encryptBytes, Base64.DEFAULT) // returning the encrypted string

    }

    fun decrypt(string : String) : String{

        val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
        cipher.init(Cipher.DECRYPT_MODE,secretKeySpec)
        val decryptedBytes = cipher.doFinal(Base64.decode(string, Base64.DEFAULT)) // decoding the entered string
        return String(decryptedBytes,Charsets.UTF_8) // returning the decrypted string
    }



}
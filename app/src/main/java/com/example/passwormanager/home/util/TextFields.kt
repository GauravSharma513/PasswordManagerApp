package com.example.passwormanager.home.util

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonTextField(inputext:String,text:String,onChange:(String)->Unit){


    OutlinedTextField(
        value = inputext, onValueChange = { onChange(it) },
        singleLine = true,
        maxLines = 1,
        modifier = Modifier
            .padding(vertical = 10.dp, horizontal = 20.dp)
            .fillMaxWidth(),
        label = { Text(text = text)},
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
        colors = TextFieldDefaults.textFieldColors(
            cursorColor = Color.Blue,
            containerColor = MaterialTheme.colorScheme.surface,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.secondaryContainer,
            focusedIndicatorColor = Color.DarkGray,

            ),
        shape = RoundedCornerShape(15.dp)

    )
}
//@Preview(showBackground = true)
//@Composable
//fun CommonTextFieldPreview() {
//    CommonTextField(
//        inputext = "Initial Text", // Initial text value
//        text = "Label Text", // Label text
//        onChange = {} // Empty lambda for onChange
//    )
//}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(inputext:String,text:String,onChange:(String)->Unit) {

    var showPassword by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = inputext, onValueChange = { onChange(it) },
        singleLine = true,
        maxLines = 1,
        modifier = Modifier
            .padding(vertical = 10.dp, horizontal = 20.dp)
            .fillMaxWidth(),
        label = { Text(text = text) },
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
        colors = TextFieldDefaults.textFieldColors(
            cursorColor = Color.Blue,
            containerColor = MaterialTheme.colorScheme.surface,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.secondaryContainer,
            focusedIndicatorColor = Color.DarkGray,

            ),
        shape = RoundedCornerShape(15.dp),
        visualTransformation = if (showPassword) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        trailingIcon = {
            if (showPassword) {
                IconButton(onClick = { showPassword = false }) {
                    Icon(
                        imageVector = Icons.Filled.Visibility,
                        contentDescription = "show"
                    )
                }
            } else {
                IconButton(
                    onClick = { showPassword = true }) {
                    Icon(
                        imageVector = Icons.Filled.VisibilityOff,
                        contentDescription = "hide"
                    )
                }
            }
        }

        )
}
@Preview(showBackground = true)@Composable
fun PasswordTextFieldPreview() {
    PasswordTextField(
        inputext = "Initial Password", // Initial password value
        text = "Password", // Label text
        onChange = {} // Empty lambda for onChange
    )
}

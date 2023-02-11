package com.omdeep.jetpackcompose.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.omdeep.jetpackcompose.R
import com.omdeep.jetpackcompose.ui.activity.ui.theme.JetpackComposeTheme

@Composable
fun RegisterScreen(
    home: () -> Unit,
    back: () -> Unit
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current).data(data = R.drawable.user_profile)
                    .apply(block = fun ImageRequest.Builder.() {
                        scale(Scale.FILL)
                        transformations(CircleCropTransformation())
                    }).build()
            ), contentDescription = "user profile", modifier = Modifier
                .height(100.dp)
                .width(100.dp)
                .weight(0.2f)
                .clickable {

                }
        )

        Spacer(Modifier.size(16.dp))

        OutlinedTextField(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
            label = { Text(text = "Name") },
            placeholder = { Text(text = "Enter your name.") },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Person, contentDescription = "name")
            },
            trailingIcon = {
                Icon(Icons.Default.Clear,
                    contentDescription = "clear text",
                    modifier = Modifier.clickable {

                    })
            },
            shape = RoundedCornerShape(10.dp),
            isError = false,
            maxLines = 1,
            singleLine = true,
            value = "",
            onValueChange = {

            })

        Spacer(Modifier.size(16.dp))

        OutlinedTextField(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
            label = { Text(text = "Mobile Number") },
            placeholder = { Text(text = "Enter your mobile number.") },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Phone, contentDescription = "name")
            },
            trailingIcon = {
                Icon(Icons.Default.Clear,
                    contentDescription = "clear text",
                    modifier = Modifier.clickable {

                    })
            },
            shape = RoundedCornerShape(10.dp),
            isError = false,
            maxLines = 1,
            singleLine = true,
            value = "",
            onValueChange = {

            })

        Spacer(Modifier.size(16.dp))

        OutlinedTextField(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
            label = { Text(text = "Email") },
            placeholder = { Text(text = "Enter your email.") },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Email, contentDescription = "email")
            },
            trailingIcon = {
                Icon(Icons.Default.Clear,
                    contentDescription = "clear text",
                    modifier = Modifier.clickable {

                    })
            },
            shape = RoundedCornerShape(10.dp),
            isError = false,
            maxLines = 1,
            singleLine = true,
            value = "",
            onValueChange = {

            })

        Spacer(Modifier.size(16.dp))
        var passwordVisible by rememberSaveable { mutableStateOf(false) }
        OutlinedTextField(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
            label = { Text(text = "Password") },
            placeholder = { Text(text = "Enter your password.") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (passwordVisible) Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff
                val description = if (passwordVisible) "Hide password" else "Show password"

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, contentDescription = description)
                }
            },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Lock, contentDescription = "password")
            },
            shape = RoundedCornerShape(10.dp),
            isError = false,
            maxLines = 1,
            singleLine = true,
            value = "",
            onValueChange = {

            })

        Spacer(Modifier.size(16.dp))

        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            Button(onClick = {

            },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color.White,
                    backgroundColor = Color.Blue,
                    disabledContentColor = Color.Yellow
                ),
                contentPadding = PaddingValues(
                    start = 20.dp,
                    top = 12.dp,
                    end = 20.dp,
                    bottom = 12.dp
                )
            ) {
                Text(text = "Register")
            }
        }

        Spacer(Modifier.size(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterPreview() {
    JetpackComposeTheme {
//        RegisterScreen()
    }
}

@Composable
fun LoginScreen(
    home: () -> Unit,
    back: () -> Unit
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(Modifier.size(16.dp))

        OutlinedTextField(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
            label = { Text(text = "Email") },
            placeholder = { Text(text = "Enter your email.") },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Email, contentDescription = "email")
            },
            trailingIcon = {
                Icon(Icons.Default.Clear,
                    contentDescription = "clear text",
                    modifier = Modifier.clickable {

                    })
            },
            shape = RoundedCornerShape(10.dp),
            isError = false,
            maxLines = 1,
            singleLine = true,
            value = "",
            onValueChange = {

            })

        Spacer(Modifier.size(16.dp))
        var passwordVisible by rememberSaveable { mutableStateOf(false) }
        OutlinedTextField(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
            label = { Text(text = "Password") },
            placeholder = { Text(text = "Enter your password.") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (passwordVisible) Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff
                val description = if (passwordVisible) "Hide password" else "Show password"

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, contentDescription = description)
                }
            },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Lock, contentDescription = "password")
            },
            shape = RoundedCornerShape(10.dp),
            isError = false,
            maxLines = 1,
            singleLine = true,
            value = "",
            onValueChange = {

            })

        Spacer(Modifier.size(16.dp))

        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            Button(onClick = {

            },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color.White,
                    backgroundColor = Color.Blue,
                    disabledContentColor = Color.Yellow
                ),
                contentPadding = PaddingValues(
                    start = 20.dp,
                    top = 12.dp,
                    end = 20.dp,
                    bottom = 12.dp
                )
            ) {
                Text(text = "Login")
            }
        }

        Spacer(Modifier.size(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    JetpackComposeTheme {
//        RegisterScreen()
    }
}
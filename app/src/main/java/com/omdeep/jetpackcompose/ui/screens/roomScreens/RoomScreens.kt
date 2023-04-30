package com.omdeep.jetpackcompose.ui.screens.roomScreens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavHostController
import com.omdeep.jetpackcompose.R
import com.omdeep.jetpackcompose.ui.theme.Purple700
import com.omdeep.jetpackcompose.ui.viewModel.LoginViewModel
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.setValue
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import com.omdeep.jetpackcompose.ui.activity.MainScreenActivity
import com.omdeep.jetpackcompose.ui.viewModel.RegisterViewModel
import com.omdeep.jetpackcompose.ui.navigation.Routes

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginPage(
    navController: NavHostController,
    lvm: LoginViewModel,
    mContext: Context = LocalContext.current,
    lifecycle: LifecycleOwner = LocalLifecycleOwner.current,
    focusManager: FocusManager = LocalFocusManager.current,
    keyboardController: SoftwareKeyboardController? = LocalSoftwareKeyboardController.current,
) {
//    lvm.shouldEnabled()
    val userData = lvm.userLiveData.observeAsState()
    if (userData.value != null) {
        if (userData.value!!.email == lvm.email.value && userData.value!!.password == lvm.password.value) {
            mContext.startActivity(Intent(mContext, MainScreenActivity::class.java))
        } else {
            Toast.makeText(mContext, "User not found", Toast.LENGTH_SHORT).show()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize(1f)
    ) {
        Image(
            painter = painterResource(id = R.drawable.login),
            contentDescription = "Compose Image",
            alignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            contentScale = ContentScale.FillBounds
        )
    }

    Column(
        modifier = Modifier
            .padding(20.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        Spacer(modifier = Modifier.padding(ButtonDefaults.IconSpacing))
        Text(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(1f),
            text = "Login",
            style = TextStyle(
                fontSize = 80.sp,
                fontFamily = FontFamily.Cursive,
                fontWeight = FontWeight.ExtraBold,
                textDecoration = TextDecoration.None,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        )

        Spacer(modifier = Modifier.padding(ButtonDefaults.IconSpacing + 20.dp))

        OutlinedTextField(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                autoCorrect = true,
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            }),
            label = { Text(text = "Username") },
            placeholder = { Text(text = "Enter your username.") },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Email, contentDescription = "email")
            },
            trailingIcon = {
                Icon(Icons.Default.Clear,
                    contentDescription = "clear text",
                    modifier = Modifier.clickable {
                        lvm.email.value = ""
                    })
            },
            shape = RoundedCornerShape(10.dp),
            isError = false,
            maxLines = 1,
            singleLine = true,
            value = lvm.email.value,
            onValueChange = {
                lvm.email.value = it
//                lvm.validateEmail()
            })

        Text(
            modifier = Modifier
                .padding(8.dp),
            text = lvm.emailErrMsg.value,
            fontSize = 14.sp,
            color = Color.Red,
            textAlign = TextAlign.Left
        )

        Spacer(modifier = Modifier.padding(ButtonDefaults.IconSpacing))
        var passwordVisible by rememberSaveable {
            mutableStateOf(false)
        }
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                autoCorrect = true,
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = {
                focusManager.moveFocus(FocusDirection.Down)
                keyboardController?.hide()
            }),
            label = { Text(text = "Password") },
            placeholder = { Text(text = "Enter your password.") },
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
            value = lvm.password.value,
            onValueChange = {
                lvm.password.value = it
//                lvm.validatePassword()
            })

        Text(
            modifier = Modifier
                .padding(start = 8.dp)
                .fillMaxWidth()
                .align(Alignment.Start),
            text = lvm.passwordErrMsg.value,
            fontSize = 14.sp,
            color = Color.Red,
        )

        Spacer(modifier = Modifier.padding(ButtonDefaults.IconSpacing + 20.dp))
        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            Button(
                onClick = {
                        lvm.login()
                },
                shape = RoundedCornerShape(50.dp),
                enabled = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.23f),
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
                Text(
                    text = "Login",
                    style = TextStyle(
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Default,
                        textDecoration = TextDecoration.None,
                        color = Color.White
                    )
                )
            }
        }

        ClickableText(
            text = AnnotatedString("sign up here"),
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(1f),
            onClick = {
                navController.navigate(Routes.SignUp.route)
            },
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily.Default,
                textAlign = TextAlign.Center,
                textDecoration = TextDecoration.Underline,
                color = Purple700
            )
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SignUpPage(
    navController: NavHostController,
    rvm: RegisterViewModel,
    context: Context = LocalContext.current,
    focusManager: FocusManager = LocalFocusManager.current,
    keyboardController: SoftwareKeyboardController? = LocalSoftwareKeyboardController.current,
) {
    Box(
        modifier = Modifier
            .fillMaxSize(1f)
    ) {
        Image(
            painter = painterResource(id = R.drawable.sign_up),
            contentDescription = "Compose Image",
            alignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            contentScale = ContentScale.FillBounds
        )
    }


    Column(
        modifier = Modifier
            .padding(20.dp)
            .verticalScroll(rememberScrollState())
    ) {

        Text(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(1f),
            text = "Register",
            style = TextStyle(
                fontSize = 80.sp,
                fontFamily = FontFamily.Cursive,
                fontWeight = FontWeight.ExtraBold,
                textDecoration = TextDecoration.None,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        )


        Spacer(modifier = Modifier.padding(ButtonDefaults.IconSpacing))

        OutlinedTextField(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                capitalization = KeyboardCapitalization.Words,
                autoCorrect = true,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            }),
            label = { Text(text = "Name") },
            placeholder = { Text(text = "Enter your name.") },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Person, contentDescription = "name")
            },
            trailingIcon = {
                Icon(Icons.Default.Clear,
                    contentDescription = "clear text",
                    modifier = Modifier.clickable {
                        rvm.userName.value = ""
                    })
            },
            shape = RoundedCornerShape(10.dp),
            isError = false,
            maxLines = 1,
            singleLine = true,
            value = rvm.userName.value,
            onValueChange = {
                rvm.userName.value = it
            })

        Text(
            modifier = Modifier
                .padding(8.dp),
            text = rvm.userNameErrMsg.value,
            fontSize = 14.sp,
            color = Color.Red,
            textAlign = TextAlign.Left
        )

        Spacer(modifier = Modifier.padding(ButtonDefaults.IconSpacing))

        OutlinedTextField(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                autoCorrect = true,
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            }),
            label = {
                Text(
                    text = "" +
                            "Username"
                )
            },
            placeholder = { Text(text = "Enter your username.") },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Email, contentDescription = "email")
            },
            trailingIcon = {
                Icon(Icons.Default.Clear,
                    contentDescription = "clear text",
                    modifier = Modifier.clickable {
                        rvm.email.value = ""
                    })
            },
            shape = RoundedCornerShape(10.dp),
            isError = false,
            maxLines = 1,
            singleLine = true,
            value = rvm.email.value,
            onValueChange = {
                rvm.email.value = it
            })

        Text(
            modifier = Modifier
                .padding(8.dp),
            text = rvm.emailErrMsg.value,
            fontSize = 14.sp,
            color = Color.Red,
            textAlign = TextAlign.Left
        )

        Spacer(modifier = Modifier.padding(ButtonDefaults.IconSpacing))
        var passwordVisible by rememberSaveable {
            mutableStateOf(false)
        }
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                autoCorrect = true,
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            }),
            label = { Text(text = "Password") },
            placeholder = { Text(text = "Enter your password.") },
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
            value = rvm.password.value,
            onValueChange = {
                rvm.password.value = it
            })

        Text(
            modifier = Modifier
                .padding(start = 8.dp)
                .fillMaxWidth()
                .align(Alignment.Start),
            text = rvm.passwordErrMsg.value,
            fontSize = 14.sp,
            color = Color.Red,
        )

        Spacer(modifier = Modifier.padding(ButtonDefaults.IconSpacing))
        var cPasswordVisible by rememberSaveable {
            mutableStateOf(false)
        }
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                autoCorrect = true,
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = {
                focusManager.moveFocus(FocusDirection.Down)
                keyboardController?.hide()
            }),
            label = { Text(text = "Confirm Password") },
            placeholder = { Text(text = "Enter confirm password.") },
            visualTransformation = if (cPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (cPasswordVisible) Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff
                val description = if (cPasswordVisible) "Hide password" else "Show password"

                IconButton(onClick = { cPasswordVisible = !cPasswordVisible }) {
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
            value = rvm.confirmPassword.value,
            onValueChange = {
                rvm.confirmPassword.value = it
            })

        Text(
            modifier = Modifier
                .padding(start = 8.dp)
                .fillMaxWidth()
                .align(Alignment.Start),
            text = rvm.confirmPasswordErrMsg.value,
            fontSize = 14.sp,
            color = Color.Red,
        )

        Spacer(modifier = Modifier.padding(ButtonDefaults.IconSpacing))
        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            Button(
                onClick = {
                    rvm.register()
                    if (rvm.validate()) {
                        Toast.makeText(context, "User Registered successfully.", Toast.LENGTH_SHORT)
                            .show()
                        navController.navigate(Routes.Login.route)
                    }
                },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.53f),
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
                Text(
                    text = "Register",
                    style = TextStyle(
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Default,
                        textDecoration = TextDecoration.None,
                        color = Color.White
                    )
                )
            }
        }

        ClickableText(
            text = AnnotatedString("Login here"),
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(1f),
            onClick = {
                navController.navigate(Routes.Login.route)
            },
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily.Default,
                textAlign = TextAlign.Center,
                textDecoration = TextDecoration.Underline,
                color = Purple700
            )
        )
    }
}
package com.omdeep.jetpackcompose.ui.screens

import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.omdeep.jetpackcompose.R

@Composable
fun CustomTopAppBar(
    title: String,
    navigationIcon: @Composable() (() -> Unit)?,
    actions: @Composable() (RowScope.() -> Unit)
) {
        TopAppBar(
            modifier = Modifier
                .fillMaxWidth(1f)
                .wrapContentHeight(),
            title = {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(1f),
                    text = title,
                    fontSize = 20.sp,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            },
            backgroundColor = Color.Blue,
            contentColor = Color.White,
            actions = actions,
            navigationIcon = navigationIcon
        )
}

@Composable
fun CommonText(text : String) {
    Text(
        modifier = Modifier
            .fillMaxWidth(0.9f),
        text = buildAnnotatedString {
            
        }
    )
}

@Composable
fun NavigateBackOnPress(navController: NavHostController) {
    IconButton(
        onClick = {
            navController.navigateUp()
        }) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = stringResource(id = R.string.back),
        )
    }
}

@Composable
fun MySpacer() {
    Spacer(modifier = Modifier.padding(ButtonDefaults.IconSpacing))
}

@Composable
fun CustomSpacer(height : Dp) {
    Spacer(modifier = Modifier.height(height))
}
package com.omdeep.jetpackcompose.ui.screens

import android.app.Activity
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material.*
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
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

//
@Composable
@OptIn(ExperimentalMaterialApi::class)
fun SwipeBackground(dismissState: DismissState) {
    val direction = dismissState.dismissDirection ?: return
    val color by animateColorAsState(
        when (dismissState.targetValue) {
            DismissValue.Default -> Color.LightGray
            DismissValue.DismissedToEnd -> Color.Green
            DismissValue.DismissedToStart -> Color.Red
        }
    )
    val alignment = when (direction) {
        DismissDirection.StartToEnd -> Alignment.CenterStart
        DismissDirection.EndToStart -> Alignment.CenterEnd
    }
    val icon = when (direction) {
        DismissDirection.StartToEnd -> Icons.Default.Done
        DismissDirection.EndToStart -> Icons.Default.Delete
    }
    val scale by animateFloatAsState(
        if (dismissState.targetValue == DismissValue.Default) 0.75f else 1f
    )

    Box(
        Modifier
            .fillMaxSize()
            .background(color)
            .padding(horizontal = 20.dp),
        contentAlignment = alignment
    ) {
        Icon(
            icon,
            contentDescription = "Localized description",
            modifier = Modifier.scale(scale)
        )
    }
}
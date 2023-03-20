package com.omdeep.jetpackcompose.ui.screens

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.omdeep.jetpackcompose.R
import com.omdeep.jetpackcompose.ui.activity.BarcodeAnalyserActivity
import com.omdeep.jetpackcompose.ui.activity.DatastoreActivity
import com.omdeep.jetpackcompose.ui.activity.ExpensesManagerActivity
import com.omdeep.jetpackcompose.ui.activity.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ComposeMainScreen(
    context: Context = LocalContext.current,
    activity: Activity = context as Activity,
    scope: CoroutineScope = rememberCoroutineScope(),
    sheetState: ModalBottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
) {
    ModalBottomSheetLayout(sheetState = sheetState, sheetContent = {
        BottomSheetScreen(onCancel = {
            scope.launch {
                sheetState.animateTo(
                    ModalBottomSheetValue.Hidden, anim = tween(
                        durationMillis = 700,
                        delayMillis = 0
                    )
                )
            }
        })
    }) {

    }
    Scaffold(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        topBar = {
            CustomTopAppBar(title = "Main Screen", navigationIcon = {
                IconButton(
                    onClick = {
                        activity.finish()
                    }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.back),
                    )
                }
            }, actions = {
            })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    scope.launch {
                        sheetState.animateTo(
                            ModalBottomSheetValue.Expanded, anim = tween(
                                durationMillis = 700,
                                delayMillis = 0
                            )
                        )
                    }
                }, backgroundColor = Color.White, modifier = Modifier.padding(10.dp)
            ) {
                Icon(
                    Icons.Rounded.Add, contentDescription = "", tint = Color.Green
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(1f)
                .fillMaxHeight(1f)
                .padding(it)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(0.95f)
                    .fillMaxHeight(0.4f)
//                .weight(1f)
                    .clickable {
                        context.startActivity(Intent(context, MainActivity::class.java))
                    },
                shape = RoundedCornerShape(10.dp),
                backgroundColor = Color("#FFE6FFFF".toColorInt()),
                elevation = 10.dp,
                contentColor = Color.Black
            ) {
                Text(
                    text = "Navigation/Bottom Nav",
                    style = TextStyle(
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Default,
                        textDecoration = TextDecoration.None,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                )
            }
            Spacer(modifier = Modifier.padding(ButtonDefaults.IconSpacing))

            Card(
                modifier = Modifier
                    .fillMaxWidth(0.95f)
                    .fillMaxHeight(0.4f)
//                .weight(1f)
                    .clickable {
                        context.startActivity(Intent(context, ExpensesManagerActivity::class.java))
                    },
                shape = RoundedCornerShape(10.dp),
                backgroundColor = Color("#FFE6FFFF".toColorInt()),
                elevation = 10.dp,
                contentColor = Color.Black
            ) {
                Text(
                    text = "ExpenseMainScreen",
                    style = TextStyle(
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Default,
                        textDecoration = TextDecoration.None,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                )
            }

            Spacer(modifier = Modifier.padding(ButtonDefaults.IconSpacing))

            Card(
                modifier = Modifier
                    .fillMaxWidth(0.95f)
                    .fillMaxHeight(0.4f)
//                .weight(1f)
                    .clickable {
                        context.startActivity(Intent(context, BarcodeAnalyserActivity::class.java))
                    },
                shape = RoundedCornerShape(10.dp),
                backgroundColor = Color("#FFE6FFFF".toColorInt()),
                elevation = 10.dp,
                contentColor = Color.Black
            ) {
                Text(
                    text = "BarCode Analyser",
                    style = TextStyle(
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Default,
                        textDecoration = TextDecoration.None,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                )
            }
            Spacer(modifier = Modifier.padding(ButtonDefaults.IconSpacing))

            Card(
                modifier = Modifier
                    .fillMaxWidth(0.95f)
                    .fillMaxHeight(0.4f)
//                .weight(1f)
                    .clickable {
                        context.startActivity(Intent(context, DatastoreActivity::class.java))
                    },
                shape = RoundedCornerShape(10.dp),
                backgroundColor = Color("#FFE6FFFF".toColorInt()),
                elevation = 10.dp,
                contentColor = Color.Black
            ) {
                Text(
                    text = "Jetpack Data Store",
                    style = TextStyle(
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Default,
                        textDecoration = TextDecoration.None,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                )
            }
            Spacer(modifier = Modifier.padding(ButtonDefaults.IconSpacing))

        }
    }
}

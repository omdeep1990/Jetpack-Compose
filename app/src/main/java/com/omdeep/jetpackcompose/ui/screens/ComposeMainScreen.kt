package com.omdeep.jetpackcompose.ui.screens

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.omdeep.jetpackcompose.R
import com.omdeep.jetpackcompose.ui.activity.BarcodeAnalyserActivity
import com.omdeep.jetpackcompose.ui.activity.DatastoreActivity
import com.omdeep.jetpackcompose.ui.activity.ExpensesManagerActivity
import com.omdeep.jetpackcompose.ui.activity.MainActivity
import com.omdeep.jetpackcompose.ui.activity.PermissionsActivity
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
                //Problem after updating, animateTo is now internal in ModalBottomSheet:
//                sheetState.animateTo(
//                    ModalBottomSheetValue.Hidden, anim = tween(
//                        durationMillis = 700,
//                        delayMillis = 0
//                    )
//                )
            }
        })
    }) {
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
//                            sheetState.animateTo(
//                                ModalBottomSheetValue.Expanded, anim = tween(
//                                    durationMillis = 700,
//                                    delayMillis = 0
//                                )
//                            )
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
                //Navigation/Bottom Nav
                TitleScreen(title = "Navigation/Bottom Nav") {
                    context.startActivity(Intent(context, MainActivity::class.java))
                }

                //Expense Main Screen
                TitleScreen(title = "ExpenseMainScreen") {
                    context.startActivity(Intent(context, ExpensesManagerActivity::class.java))
                }

                //BarCode Analyzer
                TitleScreen(title = "BarCode Analyser") {
                    context.startActivity(Intent(context, BarcodeAnalyserActivity::class.java))
                }

                //Jetpack Data Store
                TitleScreen(title = "Jetpack Data Store") {
                    context.startActivity(Intent(context, DatastoreActivity::class.java))
                }

                //Compose Permissions
                TitleScreen(title = "Compose Permissions") {
                    context.startActivity(Intent(context, PermissionsActivity::class.java))
                }
            }
        }
    }
}

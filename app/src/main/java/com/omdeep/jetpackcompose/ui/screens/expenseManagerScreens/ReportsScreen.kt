package com.omdeep.jetpackcompose.ui.screens.expenseManagerScreens

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.omdeep.jetpackcompose.R
import com.omdeep.jetpackcompose.data.factory.ReportFactory
import com.omdeep.jetpackcompose.data.repository.EarningsRepository
import com.omdeep.jetpackcompose.data.repository.ExpensesRepository
import com.omdeep.jetpackcompose.data.room.MainDatabase
import com.omdeep.jetpackcompose.ui.navigation.ExpenseRoutes
import com.omdeep.jetpackcompose.ui.viewModel.ReportViewModel
import com.omdeep.jetpackcompose.utils.Constants.LEDGER
import com.omdeep.jetpackcompose.utils.Convertors.convertDateToString

@Composable
fun SearchReportByStartDateEndDate(
    navController: NavHostController,
    context: Context = LocalContext.current,
    focusManager: FocusManager = LocalFocusManager.current,
    viewModel: ReportViewModel = viewModel(factory = ReportFactory(EarningsRepository(MainDatabase.getInstance(context).dao),ExpensesRepository(
        MainDatabase.getInstance(context).dao)))
) {


    Scaffold(
        modifier = Modifier
            .fillMaxSize(1f),
        topBar = {
            MyTopAppBar(title = "Ledger")
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(1f)
                .padding(it)
        ) {
            Spacer(modifier = Modifier.padding(ButtonDefaults.IconSpacing))
            Row(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .wrapContentHeight()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .wrapContentHeight()
                        .weight(1f)
                ) {
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            autoCorrect = true,
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }),
                        label = { Text(text = "Start Date") },
                        placeholder = { Text(text = "Select start date.") },
                        leadingIcon = {
                            Image(
                                modifier = Modifier
                                    .width(30.dp)
                                    .height(30.dp),
                                painter = painterResource(id = R.drawable.rupee),
                                contentDescription = "amount"
                            )
                        },
                        trailingIcon = {
                            Icon(
                                Icons.Default.Clear,
                                contentDescription = "clear text",
                                modifier = Modifier.clickable {
                                    viewModel.startDate.value = ""
                                })
                        },
                        shape = RoundedCornerShape(10.dp),
                        isError = viewModel.isValidStartDate.value,
                        maxLines = 1,
                        singleLine = true,
                        value = viewModel.startDate.value,
                        onValueChange = {
                            viewModel.startDate.value = it
                        })

                    Text(
                        modifier = Modifier
                            .padding(8.dp),
                        text = viewModel.startDateErrMsg.value,
                        fontSize = 14.sp,
                        color = Color.Red,
                        textAlign = TextAlign.Left
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .wrapContentHeight()
                        .weight(1f)
                ) {
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            autoCorrect = true,
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }),
                        label = { Text(text = "End Date") },
                        placeholder = { Text(text = "Select end date.") },
                        leadingIcon = {
                            Image(
                                modifier = Modifier
                                    .width(30.dp)
                                    .height(30.dp),
                                painter = painterResource(id = R.drawable.rupee),
                                contentDescription = "amount"
                            )
                        },
                        trailingIcon = {
                            Icon(
                                Icons.Default.Clear,
                                contentDescription = "clear text",
                                modifier = Modifier.clickable {
                                    viewModel.endDateErrorMsg.value = ""
                                })
                        },
                        shape = RoundedCornerShape(10.dp),
                        isError = viewModel.isValidEndDate.value,
                        maxLines = 1,
                        singleLine = true,
                        value = viewModel.startDate.value,
                        onValueChange = {
                            viewModel.endDate.value = it
                        })

                    Text(
                        modifier = Modifier
                            .padding(8.dp),
                        text = viewModel.endDateErrorMsg.value,
                        fontSize = 14.sp,
                        color = Color.Red,
                        textAlign = TextAlign.Left
                    )
                }

            }


            Spacer(modifier = Modifier.padding(ButtonDefaults.IconSpacing + 20.dp))

            Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                Button(
                    onClick = {
                        if (viewModel.validate()) {
                            navController.navigate(LEDGER)
                        }
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
                        text = "Submit",
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
        }
    }
}

@Composable
fun EarningsReportSelectedMonth(
    startDate : String?,
    endDate : String?
) {
    convertDateToString(startDate!!.toLong())?.let { Text(text = it) }
    if (endDate != null) {
        Text(text = convertDateToString(startDate.toLong())+" " +convertDateToString(endDate.toLong()))
    }
}
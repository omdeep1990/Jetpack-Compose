package com.omdeep.jetpackcompose.ui.screens.expenseManagerScreens

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.*
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.omdeep.jetpackcompose.R
import com.omdeep.jetpackcompose.data.factory.EarningsFactory
import com.omdeep.jetpackcompose.data.factory.ExpensesFactory
import com.omdeep.jetpackcompose.data.factory.ReportFactory
import com.omdeep.jetpackcompose.data.repository.EarningsRepository
import com.omdeep.jetpackcompose.data.repository.ExpensesRepository
import com.omdeep.jetpackcompose.data.room.MainDatabase
import com.omdeep.jetpackcompose.data.room.tables.Earnings
import com.omdeep.jetpackcompose.data.room.tables.Expenses
import com.omdeep.jetpackcompose.ui.navigation.ExpenseRoutes
import com.omdeep.jetpackcompose.ui.viewModel.EarningsViewModel
import com.omdeep.jetpackcompose.ui.viewModel.ExpensesViewModel
import com.omdeep.jetpackcompose.ui.viewModel.ReportViewModel
import com.omdeep.jetpackcompose.utils.Constants.LEDGER
import com.omdeep.jetpackcompose.utils.Convertors.convertDateToString

@Composable
fun SearchReportByStartDateEndDate(
    navController: NavHostController,
    context: Context = LocalContext.current,
    focusManager: FocusManager = LocalFocusManager.current,
    viewModel: ReportViewModel = viewModel(
        factory = ReportFactory(
            EarningsRepository(MainDatabase.getInstance(context).dao), ExpensesRepository(
                MainDatabase.getInstance(context).dao
            )
        )
    )
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
fun RowScope.TableCell(
    text: String,
    weight: Float,
    fontSize: TextUnit,
    fontWeight: FontWeight?,
    textDecoration: TextDecoration?,
    color: Color,
    fontStyle: FontStyle?,
    fontFamily: FontFamily?
) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .weight(weight)
            .border(1.dp, Color.Black),
        text = text,
        style = TextStyle(
            fontSize = fontSize,
            fontWeight = fontWeight,
            textAlign = TextAlign.Center,
            textDecoration = textDecoration,
            textDirection = TextDirection.Content,
            color = color,
            fontStyle = fontStyle,
            fontFamily = fontFamily,
            shadow = Shadow(color = Color.LightGray, offset = Offset.Infinite, blurRadius = 0.10f),
//            textGeometricTransform = TextGeometricTransform(1.0f, 1f),
        )
    )
}

@Composable
fun EarningsReportSelectedMonth(
    startDate: String?,
    endDate: String?,
    navController: NavHostController,
    context: Context = LocalContext.current,
    viewModel: EarningsViewModel = viewModel(
        factory = EarningsFactory(
            EarningsRepository(
                MainDatabase.getInstance(context).dao
            )
        )
    )
) {
    val totalEarnings = viewModel.fetchAllEarningsBYMonth(startDate!!.toLong(), endDate!!.toLong()).observeAsState(initial = listOf())
    var earningsData = 0F
    for (i in totalEarnings.value) {
        earningsData += i.amount.toFloat()
    }

    Scaffold(
        modifier = Modifier
            .fillMaxWidth(1f)
            .wrapContentHeight(),
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .wrapContentHeight(),
                title = {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(1f),
                        text = "Current Month Earnings",
                        fontSize = 20.sp,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                },
                backgroundColor = Color.Blue,
                contentColor = Color.White,
                actions = {
                },
                navigationIcon = {
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
            )
        }
    ) { it ->
        Column(
            modifier = Modifier
                .fillMaxSize(1f)
                .padding(it),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(5.dp),
                text = "From ${convertDateToString(startDate.toLong())} to ${
                    convertDateToString(
                        endDate.toLong()
                    )
                }",
                textAlign = TextAlign.Center,
                color = Color.Blue,
                fontWeight = FontWeight.Bold
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(10.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .background(Color("#F0E9FD".toColorInt()))
                    ) {
                        //Creating earning table: -
                        TableCell(
                            text = "Total Earnings",
                            weight = 1f,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.ExtraBold,
                            textDecoration = TextDecoration.None,
                            color = Color.Black,
                            fontStyle = FontStyle.Normal,
                            fontFamily = FontFamily.SansSerif
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .background(Color("#F0E9FD".toColorInt()))
                    ) {
                        TableCell(
                            text = "Date",
                            weight = 0.25f,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            textDecoration = TextDecoration.None,
                            color = Color.Black,
                            fontStyle = FontStyle.Normal,
                            fontFamily = FontFamily.Default
                        )

                        TableCell(
                            text = "Time",
                            weight = 0.25f,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            textDecoration = TextDecoration.None,
                            color = Color.Black,
                            fontStyle = FontStyle.Normal,
                            fontFamily = FontFamily.Default
                        )

                        TableCell(
                            text = "Type",
                            weight = 0.25f,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            textDecoration = TextDecoration.None,
                            color = Color.Black,
                            fontStyle = FontStyle.Normal,
                            fontFamily = FontFamily.Default
                        )

                        TableCell(
                            text = "Amount",
                            weight = 0.25f,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            textDecoration = TextDecoration.None,
                            color = Color.Black,
                            fontStyle = FontStyle.Normal,
                            fontFamily = FontFamily.Default
                        )
                    }

                }

                items(totalEarnings.value) {
                    DesignForEarningsTable(earnings = it)
                }

                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .background(Color("#F0E9FD".toColorInt()))
                    ) {
                        TableCell(
                            text = "Total",
                            weight = 0.75f,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.ExtraBold,
                            textDecoration = TextDecoration.None,
                            color = Color.Black,
                            fontStyle = FontStyle.Normal,
                            fontFamily = FontFamily.SansSerif
                        )

                        earningsData?.toString()?.let { it1 ->
                            TableCell(
                                text = "₹ $it1",
                                weight = 0.25f,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold,
                                textDecoration = TextDecoration.None,
                                color = Color("#41EE48".toColorInt()),
                                fontStyle = FontStyle.Normal,
                                fontFamily = FontFamily.SansSerif
                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun ExpensesReportSelectedMonth(
    startDate: String?,
    endDate: String?,
    navController: NavHostController,
    context: Context = LocalContext.current,
    viewModel: ExpensesViewModel = viewModel(
        factory = ExpensesFactory(
            ExpensesRepository(
                MainDatabase.getInstance(context).dao
            )
        )
    )
) {
    val totalExpenses = viewModel.fetchAllExpensesByMonth(startDate!!.toLong(), endDate!!.toLong()).observeAsState(initial = listOf())
    var expensesData = 0F
    for (i in totalExpenses.value) {
        expensesData += i.amount.toFloat()
    }

    Scaffold(
        modifier = Modifier
            .fillMaxWidth(1f)
            .wrapContentHeight(),
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .wrapContentHeight(),
                title = {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(1f),
                        text = "Current Month Earnings",
                        fontSize = 20.sp,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                },
                backgroundColor = Color.Blue,
                contentColor = Color.White,
                actions = {
                },
                navigationIcon = {
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
            )
        }
    ) { it ->
        Column(
            modifier = Modifier
                .fillMaxSize(1f)
                .padding(it),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(5.dp),
                text = "From ${convertDateToString(startDate.toLong())} to ${
                    convertDateToString(
                        endDate.toLong()
                    )
                }",
                textAlign = TextAlign.Center,
                color = Color.Blue,
                fontWeight = FontWeight.Bold
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(10.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .background(Color("#F0E9FD".toColorInt()))
                    ) {
                        //Creating earning table: -
                        TableCell(
                            text = "Total Earnings",
                            weight = 1f,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.ExtraBold,
                            textDecoration = TextDecoration.None,
                            color = Color.Black,
                            fontStyle = FontStyle.Normal,
                            fontFamily = FontFamily.SansSerif
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .background(Color("#F0E9FD".toColorInt()))
                    ) {
                        TableCell(
                            text = "Date",
                            weight = 0.25f,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            textDecoration = TextDecoration.None,
                            color = Color.Black,
                            fontStyle = FontStyle.Normal,
                            fontFamily = FontFamily.Default
                        )

                        TableCell(
                            text = "Time",
                            weight = 0.25f,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            textDecoration = TextDecoration.None,
                            color = Color.Black,
                            fontStyle = FontStyle.Normal,
                            fontFamily = FontFamily.Default
                        )

                        TableCell(
                            text = "Type",
                            weight = 0.25f,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            textDecoration = TextDecoration.None,
                            color = Color.Black,
                            fontStyle = FontStyle.Normal,
                            fontFamily = FontFamily.Default
                        )

                        TableCell(
                            text = "Amount",
                            weight = 0.25f,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            textDecoration = TextDecoration.None,
                            color = Color.Black,
                            fontStyle = FontStyle.Normal,
                            fontFamily = FontFamily.Default
                        )
                    }

                }

                items(totalExpenses.value) {
                    DesignForExpensesTable(expenses = it)
                }

                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .background(Color("#F0E9FD".toColorInt()))
                    ) {
                        TableCell(
                            text = "Total",
                            weight = 0.75f,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.ExtraBold,
                            textDecoration = TextDecoration.None,
                            color = Color.Black,
                            fontStyle = FontStyle.Normal,
                            fontFamily = FontFamily.SansSerif
                        )

                        expensesData?.toString()?.let { it1 ->
                            TableCell(
                                text = "₹ $it1",
                                weight = 0.25f,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold,
                                textDecoration = TextDecoration.None,
                                color = Color.Red,
                                fontStyle = FontStyle.Normal,
                                fontFamily = FontFamily.SansSerif
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DesignForEarningsTable(earnings: Earnings) {
    Row(
        modifier = Modifier
            .fillMaxWidth(1f)
            .background(Color("#F4F1F8".toColorInt()))
    ) {
        convertDateToString(earnings.date)?.let {
            TableCell(
                text = it,
                weight = 0.25f,
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                textDecoration = TextDecoration.None,
                color = Color.Black,
                fontStyle = FontStyle.Normal,
                fontFamily = FontFamily.Default
            )
        }

        TableCell(
            text = earnings.time,
            weight = 0.25f,
            fontSize = 15.sp,
            fontWeight = FontWeight.Normal,
            textDecoration = TextDecoration.None,
            color = Color.Black,
            fontStyle = FontStyle.Normal,
            fontFamily = FontFamily.Default
        )

        TableCell(
            text = earnings.earningsType,
            weight = 0.25f,
            fontSize = 15.sp,
            fontWeight = FontWeight.Normal,
            textDecoration = TextDecoration.None,
            color = Color.Black,
            fontStyle = FontStyle.Normal,
            fontFamily = FontFamily.Default
        )

        TableCell(
            text = "₹ ${earnings.amount})",
            weight = 0.25f,
            fontSize = 15.sp,
            fontWeight = FontWeight.Normal,
            textDecoration = TextDecoration.None,
            color = Color.Black,
            fontStyle = FontStyle.Normal,
            fontFamily = FontFamily.Default
        )
    }
}

@Composable
fun DesignForExpensesTable(expenses: Expenses) {
    Row(
        modifier = Modifier
            .fillMaxWidth(1f)
            .background(Color("#F4F1F8".toColorInt()))
    ) {
        convertDateToString(expenses.date)?.let {
            TableCell(
                text = it,
                weight = 0.25f,
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                textDecoration = TextDecoration.None,
                color = Color.Black,
                fontStyle = FontStyle.Normal,
                fontFamily = FontFamily.Default
            )
        }

        TableCell(
            text = expenses.time,
            weight = 0.25f,
            fontSize = 15.sp,
            fontWeight = FontWeight.Normal,
            textDecoration = TextDecoration.None,
            color = Color.Black,
            fontStyle = FontStyle.Normal,
            fontFamily = FontFamily.Default
        )

        TableCell(
            text = expenses.earningsType,
            weight = 0.25f,
            fontSize = 15.sp,
            fontWeight = FontWeight.Normal,
            textDecoration = TextDecoration.None,
            color = Color.Black,
            fontStyle = FontStyle.Normal,
            fontFamily = FontFamily.Default
        )

        TableCell(
            text = "₹ ${expenses.amount}",
            weight = 0.25f,
            fontSize = 15.sp,
            fontWeight = FontWeight.Normal,
            textDecoration = TextDecoration.None,
            color = Color.Black,
            fontStyle = FontStyle.Normal,
            fontFamily = FontFamily.Default
        )
    }
}
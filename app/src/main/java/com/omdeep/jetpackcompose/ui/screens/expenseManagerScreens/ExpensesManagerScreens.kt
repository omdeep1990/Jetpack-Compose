package com.omdeep.jetpackcompose.ui.screens.expenseManagerScreens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.core.graphics.toColorInt
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.omdeep.jetpackcompose.R
import com.omdeep.jetpackcompose.data.factory.EarningsFactory
import com.omdeep.jetpackcompose.data.factory.ExpensesFactory
import com.omdeep.jetpackcompose.data.repository.EarningsRepository
import com.omdeep.jetpackcompose.data.repository.ExpensesRepository
import com.omdeep.jetpackcompose.data.room.MainDatabase
import com.omdeep.jetpackcompose.ui.theme.JetpackComposeTheme
import com.omdeep.jetpackcompose.ui.viewModel.EarningsViewModel
import com.omdeep.jetpackcompose.ui.viewModel.ExpensesViewModel
import com.omdeep.jetpackcompose.utils.Calender.getEndDate
import com.omdeep.jetpackcompose.utils.Calender.getStartDate
import com.omdeep.jetpackcompose.utils.Calender.monthName
import com.omdeep.jetpackcompose.utils.Calender.openDatePickerDialog
import com.omdeep.jetpackcompose.utils.Calender.openTimePickerDialog
import com.omdeep.jetpackcompose.utils.Calender.year
import com.omdeep.jetpackcompose.utils.Constants.EARNINGS
import com.omdeep.jetpackcompose.utils.Constants.EARNINGS_REPORT
import com.omdeep.jetpackcompose.utils.Constants.EXPENSES
import com.omdeep.jetpackcompose.utils.Constants.EXPENSES_REPORT
import com.omdeep.jetpackcompose.utils.Constants.GET_REPORT
import com.omdeep.jetpackcompose.utils.Constants.LEDGER
import com.omdeep.jetpackcompose.utils.Constants.monthList
import com.omdeep.jetpackcompose.utils.Constants.yearList
import com.omdeep.jetpackcompose.utils.Convertors.convertDateToLong
import java.util.*

@Composable
fun MyTopAppBar(title: String) {
    TopAppBar(
        title = {
            Text(
                modifier = Modifier
                    .padding(8.dp),
                text = title,
                fontSize = 30.sp,
                color = Color.Blue,
                textAlign = TextAlign.Center
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        backgroundColor = Color.White,
        contentColor = Color.Blue,
        actions = {

        },
        elevation = 0.dp
    )
}

@Composable
fun MainExpenseManagerScreen(
    navController: NavHostController,
    context: Context = LocalContext.current,
    earningsViewModel: EarningsViewModel = viewModel(factory = EarningsFactory(EarningsRepository(
        MainDatabase.getInstance(context).dao))),
    expensesViewModel: ExpensesViewModel = viewModel(factory = ExpensesFactory(ExpensesRepository(MainDatabase.getInstance(context).dao)))
) {
    var mDisplayMenu by remember { mutableStateOf(false) }
    var expandMonth by remember { mutableStateOf(false) }
    var expandYear by remember { mutableStateOf(false) }
    //Fetching and selecting month name from the month list and displaying data according to the date: -

    val startDate = getStartDate(earningsViewModel.month, earningsViewModel.year)
    val endDate = getEndDate(earningsViewModel.month, earningsViewModel.year)
    val allEarningsByMonth =
        earningsViewModel.fetchAllEarningsBYMonth(convertDateToLong(startDate), convertDateToLong(endDate)).observeAsState(initial = listOf())
    val allExpenses = expensesViewModel.fetchAllExpensesByMonth(convertDateToLong(startDate), convertDateToLong(endDate)).observeAsState(initial = listOf())
    //TODO: fetching current month name from calender: -

    var totalEarnings = 0F
    var totalExpenses = 0F
    for (i in allEarningsByMonth.value) {
        totalEarnings += i.amount.toFloat()
    }

    for (i in allExpenses.value) {
        totalExpenses += i.amount.toFloat()
    }

    Scaffold(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        backgroundColor = Color.White,
        contentColor = Color.Blue,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .padding(8.dp),
                        text = "My Ledger",
                        fontSize = 30.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                backgroundColor = Color("#04347c".toColorInt()),
                contentColor = Color.Blue,
                actions = {
                    IconButton(
                        onClick = { mDisplayMenu = !mDisplayMenu }) {
                        Card(
                            modifier = Modifier
                                .wrapContentWidth()
                                .wrapContentHeight()
                                .padding(10.dp),
                            shape = RoundedCornerShape(50.dp),
                            backgroundColor = Color("#91B8F1".toColorInt()),
                            elevation = 15.dp,
                            border = BorderStroke(1.dp, Color.LightGray),
                            contentColor = Color.Black
                        ) {
                            Icon(
                                imageVector = Icons.Default.Segment,
                                contentDescription = "",
                                tint = Color.White)
                        }
                    }
                    DropdownMenu(
                        expanded = mDisplayMenu,
                        onDismissRequest = { mDisplayMenu = false }
                    ) {
                        DropdownMenuItem(
                            onClick = {
                            mDisplayMenu = false
                            navController.navigate(GET_REPORT)
                        }) {
                                Text(text = "View Short report")
                            Divider(
                                modifier = Modifier
                                    .fillMaxWidth(1f),
                                color = Color.LightGray,
                                thickness = 0.2.dp
                            )
                        }
                    }
                },
                elevation = 0.dp
            )
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

            Divider(
                modifier = Modifier
                    .fillMaxWidth(1f),
                color = Color.LightGray,
                thickness = 0.2.dp
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .fillMaxHeight(0.12f)
                    .padding(5.dp),
                shape = RoundedCornerShape(10.dp),
                backgroundColor = Color("#b193c2".toColorInt()),
                elevation = 15.dp,
                border = BorderStroke(1.dp, Color.LightGray),
                contentColor = Color.Black
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(1f),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (earningsViewModel.month.value == "") monthName else earningsViewModel.month.value,
                        style = TextStyle(
                            fontSize = 30.sp,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = FontFamily.SansSerif,
                            textDecoration = TextDecoration.None,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )
                    )

                    Spacer(modifier = Modifier.padding(ButtonDefaults.IconSpacing))

                    Icon(if (expandMonth) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown, "icon", Modifier.clickable { expandMonth = !expandMonth })

                    DropdownMenu(
                        expanded = expandMonth,
                        onDismissRequest = { expandMonth = false },
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .fillMaxHeight(0.5f)
                    ) {
                        monthList.forEach { label ->
                            DropdownMenuItem(
                                onClick = {
                                    earningsViewModel.month.value = label
                                    expandMonth = false
                                }
                            ) {
                                Text(text = label)
                            }
                        }
                    }

                    Text(
                        text = if (earningsViewModel.year.value == "") year.toString() else earningsViewModel.year.value,
                        style = TextStyle(
                            fontSize = 30.sp,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = FontFamily.SansSerif,
                            textDecoration = TextDecoration.None,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )
                    )

                    Icon(if (expandYear)
                        Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown, "icon",
                        Modifier.clickable { expandYear = !expandYear })

                    DropdownMenu(
                        expanded = expandYear,
                        onDismissRequest = { expandYear = false },
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .fillMaxHeight(0.5f)
                    ) {
                        yearList.forEach { label ->
                            DropdownMenuItem(
                                onClick = {
                                    earningsViewModel.year.value = label
                                    expandYear = false
                                }
                            ) {
                                Text(text = label)
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.padding(ButtonDefaults.IconSpacing))
            Divider(
                modifier = Modifier
                    .fillMaxWidth(1f),
                color = Color.LightGray,
                thickness = 0.2.dp
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .fillMaxHeight(0.4f)
                        .weight(1f)
                        .clip(RectangleShape)
                        .padding(5.dp)
                        .clickable {
                            navController.navigate(
                                "$EARNINGS_REPORT/${convertDateToLong(startDate)}/${
                                    convertDateToLong(
                                        endDate
                                    )
                                }"
                            )
                        },
                    shape = RoundedCornerShape(10.dp),
                    backgroundColor = Color("#FFE6FFFF".toColorInt()),
                    elevation = 5.dp,
                    border = BorderStroke(1.dp, Color.Black),
                    contentColor = Color.Black
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(1f)
                            .fillMaxHeight(0.5f)
                            .clip(RectangleShape)
                            .padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        Spacer(modifier = Modifier.padding(ButtonDefaults.IconSpacing))
                        Text(
                            text = "Total Earnings",
                            style = TextStyle(
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Default,
                                textDecoration = TextDecoration.None,
                                color = Color.Black,
                                textAlign = TextAlign.Center
                            )
                        )

                        Spacer(modifier = Modifier.padding(ButtonDefaults.IconSpacing))

                        Text(
                            text = "₹ $totalEarnings",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Normal,
                                fontFamily = FontFamily.Default,
                                textDecoration = TextDecoration.None,
                                color = Color.Black
                            )
                        )

                        Spacer(modifier = Modifier.padding(ButtonDefaults.IconSpacing))
                    }
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .fillMaxHeight(0.4f)
                        .weight(1f)
                        .clip(RectangleShape)
                        .padding(5.dp)
                        .clickable {
                            navController.navigate(
                                "$EXPENSES_REPORT/${convertDateToLong(startDate)}/${
                                    convertDateToLong(
                                        endDate
                                    )
                                }"
                            )
                        },
                    shape = RoundedCornerShape(10.dp),
                    backgroundColor = Color("#FFE6FFFF".toColorInt()),
                    elevation = 5.dp,
                    border = BorderStroke(1.dp, Color.Black),
                    contentColor = Color.Black
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(1f)
                            .fillMaxHeight(0.5f)
                            .clip(RectangleShape)
                            .padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        Spacer(modifier = Modifier.padding(ButtonDefaults.IconSpacing))
                        Text(
                            text = "Total Expenses",
                            style = TextStyle(
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Default,
                                textDecoration = TextDecoration.None,
                                color = Color.Black,
                                textAlign = TextAlign.Center
                            )
                        )

                        Spacer(modifier = Modifier.padding(ButtonDefaults.IconSpacing))

                        Text(
                            text = "₹ $totalExpenses",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Normal,
                                fontFamily = FontFamily.Default,
                                textDecoration = TextDecoration.None,
                                color = Color.Black
                            )
                        )

                        Spacer(modifier = Modifier.padding(ButtonDefaults.IconSpacing))

                    }
                }
            }

            Spacer(modifier = Modifier.padding(ButtonDefaults.IconSpacing + 20.dp))

            ExtendedFloatingActionButton(
                text = {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .fillMaxHeight(0.15f),
                        text = "Earnings",
                        style = TextStyle(
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = FontFamily.Default,
                            textDecoration = TextDecoration.None,
                            color = Color.Black
                        )
                    )
                },
                onClick = {
                    navController.navigate(EARNINGS)
                },
                backgroundColor = Color.Green,
                contentColor = Color.White,
                icon = {
                    Image(
                        modifier = Modifier
                            .width(30.dp)
                            .height(30.dp),
                        painter = painterResource(id = R.drawable.earnings),
                        contentDescription = "earnings"
                    )
                }
            )

            Spacer(modifier = Modifier.padding(ButtonDefaults.IconSpacing + 15.dp))

            ExtendedFloatingActionButton(
                text = {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .fillMaxHeight(0.15f),
                        text = "Expenses",
                        style = TextStyle(
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = FontFamily.Default,
                            textDecoration = TextDecoration.None,
                            color = Color.Black
                        )
                    )
                },
                onClick = {
                    navController.navigate(EXPENSES)
                },
                backgroundColor = Color.Red,
                contentColor = Color.White,
                icon = {
                    Image(
                        modifier = Modifier
                            .width(30.dp)
                            .height(30.dp),
                        painter = painterResource(id = R.drawable.expense),
                        contentDescription = "earnings"
                    )
                }
            )

        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddExpenses(
    navController: NavHostController,
    context: Context = LocalContext.current,
    focusManager: FocusManager = LocalFocusManager.current,
    evm: ExpensesViewModel = viewModel(factory = ExpensesFactory(ExpensesRepository(MainDatabase.getInstance(context).dao))),
    keyboardController: SoftwareKeyboardController? = LocalSoftwareKeyboardController.current,
) {
    //TODO: For opening drop down menu in text field: -
    var mExpanded by remember { mutableStateOf(false) }
    val earningsType = listOf("Milk", "Fees", "Travel", "Grocery", "Rent", "Insurance", "Telephone & Network")
    var mTextFieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if (mExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    DropdownMenu(
        expanded = mExpanded,
        onDismissRequest = { mExpanded = false },
        modifier = Modifier
            .width(with(LocalDensity.current) { mTextFieldSize.width.toDp() })
    ) {
        earningsType.forEach { label ->
            DropdownMenuItem(
                onClick = {
                    evm.expenses.value = label
                    mExpanded = false
                }
            ) {
                Text(text = label)
            }
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        backgroundColor = Color.White,
        contentColor = Color.Blue,
        topBar = {
            MyTopAppBar(title = "My Ledger")
        }
    ) { it ->
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
                    .fillMaxWidth(1f)
                    .fillMaxHeight(0.12f)
                    .padding(5.dp),
                shape = RoundedCornerShape(10.dp),
                backgroundColor = Color("#FFE6FFFF".toColorInt()),
                elevation = 5.dp,
                border = BorderStroke(1.dp, Color.Black),
                contentColor = Color.Black
            ) {
                Text(
                    text = "Add Expenses",
                    style = TextStyle(
                        fontSize = 50.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Cursive,
                        textDecoration = TextDecoration.Underline,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)
                        .weight(1f),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        autoCorrect = true,
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }),
                    label = { Text(text = "Date") },
                    placeholder = { Text(text = "Select any date.") },
                    trailingIcon = {
                        Image(
                            modifier = Modifier
                                .width(30.dp)
                                .height(30.dp)
                                .clickable {
                                    openDatePickerDialog(context, evm.date).show()
                                },
                            painter = painterResource(id = R.drawable.calendar),
                            contentDescription = "date"
                        )
                    },
                    shape = RoundedCornerShape(10.dp),
                    isError = evm.isValidDate.value,
                    maxLines = 1,
                    singleLine = true,
                    value = evm.date.value,
                    onValueChange = {
                        evm.date.value = it
                    })

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)
                        .weight(1f),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        autoCorrect = true,
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }),
                    label = { Text(text = "Time") },
                    placeholder = { Text(text = "Select any time.") },
                    trailingIcon = {
                        Image(
                            modifier = Modifier
                                .width(30.dp)
                                .height(30.dp)
                                .clickable {
                                    openTimePickerDialog(context, evm.time).show()
                                },
                            painter = painterResource(id = R.drawable.time),
                            contentDescription = "time"
                        )
                    },
                    shape = RoundedCornerShape(10.dp),
                    isError = evm.isValidTime.value,
                    maxLines = 1,
                    singleLine = true,
                    value = evm.time.value,
                    onValueChange = {
                        evm.time.value = it
                    })
            }

            Spacer(modifier = Modifier.padding(ButtonDefaults.IconSpacing + 5.dp))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
                    .onGloballyPositioned { coordinates ->
                        mTextFieldSize = coordinates.size.toSize()
                    },
                keyboardOptions = KeyboardOptions.Default.copy(
                    autoCorrect = true,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }),
                label = { Text(text = "Expenses Type") },
                placeholder = { Text(text = "Expenses Type") },
                trailingIcon = {
                    Icon(icon, "icon",
                        Modifier.clickable { mExpanded = !mExpanded })
                },
                shape = RoundedCornerShape(10.dp),
                isError = evm.isValidExpenses.value,
                maxLines = 1,
                singleLine = true,
                value = evm.expenses.value,
                onValueChange = {
                    evm.expenses.value = it
                })

            Text(
                modifier = Modifier
                    .padding(8.dp),
                text = evm.expensesErrMsg.value,
                fontSize = 14.sp,
                color = Color.Red
            )

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
                label = { Text(text = "Amount") },
                placeholder = { Text(text = "Enter any amount.") },
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
                    Icon(Icons.Default.Clear,
                        contentDescription = "clear text",
                        modifier = Modifier.clickable {
                            evm.amount.value = ""
                        })
                },
                shape = RoundedCornerShape(10.dp),
                isError = evm.isValidAmount.value,
                maxLines = 1,
                singleLine = true,
                value = evm.amount.value,
                onValueChange = {
                    evm.amount.value = it
                })

            Text(
                modifier = Modifier
                    .padding(8.dp),
                text = evm.amountErrMsg.value,
                fontSize = 14.sp,
                color = Color.Red,
                textAlign = TextAlign.Left
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    autoCorrect = true,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                    keyboardController?.hide()
                }),
                label = { Text(text = "Note") },
                placeholder = { Text(text = "Note (Optional)") },
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
                    Icon(Icons.Default.Clear,
                        contentDescription = "clear text",
                        modifier = Modifier.clickable {
                            evm.note.value = ""
                        })
                },
                shape = RoundedCornerShape(10.dp),
                isError = false,
                maxLines = 1,
                singleLine = true,
                value = evm.note.value,
                onValueChange = {
                    evm.note.value = it
                })

            Spacer(modifier = Modifier.padding(ButtonDefaults.IconSpacing + 20.dp))

            Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                Button(
                    onClick = {
                        if (evm.validate()) {
                            evm.insertAllExpenses()
                            Toast.makeText(context, "Current expenses inserted successfully", Toast.LENGTH_SHORT).show()
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

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddEarnings(
    navController: NavHostController,
    context: Context = LocalContext.current,
    focusManager: FocusManager = LocalFocusManager.current,
    evm: EarningsViewModel = viewModel(factory = EarningsFactory(EarningsRepository(MainDatabase.getInstance(context).dao))),
    keyboardController: SoftwareKeyboardController? = LocalSoftwareKeyboardController.current,
) {
    //TODO: For opening drop down menu in textfield: -
    var mExpanded by remember { mutableStateOf(false) }
    val earningsType = listOf("Wages", "Interest", "Profit", "Rent", "Salary", "Pensions", "Others")
    var mTextFieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if (mExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    DropdownMenu(
        expanded = mExpanded,
        onDismissRequest = { mExpanded = false },
        modifier = Modifier
            .width(with(LocalDensity.current) { mTextFieldSize.width.toDp() })
    ) {
        earningsType.forEach { label ->
            DropdownMenuItem(
                onClick = {
                    evm.earnings.value = label
                    mExpanded = false
                }
            ) {
                Text(text = label)
            }
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        backgroundColor = Color.White,
        contentColor = Color.Blue,
        topBar = {
            MyTopAppBar(title = "My Ledger")
        }
    ) { it ->
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
                    .fillMaxWidth(1f)
                    .fillMaxHeight(0.12f)
                    .padding(5.dp),
                shape = RoundedCornerShape(10.dp),
                backgroundColor = Color("#FFE6FFFF".toColorInt()),
                elevation = 5.dp,
                border = BorderStroke(1.dp, Color.Black),
                contentColor = Color.Black
            ) {
                Text(
                    text = "Add Earnings",
                    style = TextStyle(
                        fontSize = 50.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Cursive,
                        textDecoration = TextDecoration.Underline,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)
                        .weight(1f),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        autoCorrect = true,
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }),
                    label = { Text(text = "Date") },
                    placeholder = { Text(text = "Select any date.") },
                    trailingIcon = {
                        Image(
                            modifier = Modifier
                                .width(30.dp)
                                .height(30.dp)
                                .clickable {
                                    openDatePickerDialog(context, evm.date).show()
                                },
                            painter = painterResource(id = R.drawable.calendar),
                            contentDescription = "date"
                        )
                    },
                    shape = RoundedCornerShape(10.dp),
                    isError = false,
                    maxLines = 1,
                    singleLine = true,
                    value = evm.date.value,
                    onValueChange = {
                        evm.date.value = it
                    })

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)
                        .weight(1f),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        autoCorrect = true,
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }),
                    label = { Text(text = "Time") },
                    placeholder = { Text(text = "Select any time.") },
                    trailingIcon = {
                        Image(
                            modifier = Modifier
                                .width(30.dp)
                                .height(30.dp)
                                .clickable {
                                    openTimePickerDialog(context, evm.time).show()
                                },
                            painter = painterResource(id = R.drawable.time),
                            contentDescription = "time"
                        )
                    },
                    shape = RoundedCornerShape(10.dp),
                    isError = false,
                    maxLines = 1,
                    singleLine = true,
                    value = evm.time.value,
                    onValueChange = {
                        evm.time.value = it
                    })
            }

            Spacer(modifier = Modifier.padding(ButtonDefaults.IconSpacing + 5.dp))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
                    .onGloballyPositioned { coordinates ->
                        mTextFieldSize = coordinates.size.toSize()
                    },
                keyboardOptions = KeyboardOptions.Default.copy(
                    autoCorrect = true,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }),
                label = { Text(text = "Earnings Type") },
                placeholder = { Text(text = "Earnings Type") },
                trailingIcon = {
                    Icon(icon, "icon",
                        Modifier.clickable { mExpanded = !mExpanded })
                },
                shape = RoundedCornerShape(10.dp),
                isError = false,
                maxLines = 1,
                singleLine = true,
                value = evm.earnings.value,
                onValueChange = {
                    evm.earnings.value = it
                })

            Text(
                modifier = Modifier
                    .padding(8.dp),
                text = evm.earningsErrMsg.value,
                fontSize = 14.sp,
                color = Color.Red
            )

            OutlinedTextField(modifier = Modifier
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
                label = { Text(text = "Amount") },
                placeholder = { Text(text = "Enter any amount.") },
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
                    Icon(Icons.Default.Clear,
                        contentDescription = "clear text",
                        modifier = Modifier.clickable {
                            evm.amount.value = ""
                        })
                },
                shape = RoundedCornerShape(10.dp),
                isError = false,
                maxLines = 1,
                singleLine = true,
                value = evm.amount.value,
                onValueChange = {
                    evm.amount.value = it
                })

            Text(
                modifier = Modifier
                    .padding(8.dp),
                text = evm.amountErrMsg.value,
                fontSize = 14.sp,
                color = Color.Red,
                textAlign = TextAlign.Left
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    autoCorrect = true,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                    keyboardController?.hide()
                }),
                label = { Text(text = "Note") },
                placeholder = { Text(text = "Note (Optional)") },
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
                    Icon(Icons.Default.Clear,
                        contentDescription = "clear text",
                        modifier = Modifier.clickable {
                            evm.note.value = ""
                        })
                },
                shape = RoundedCornerShape(10.dp),
                isError = false,
                maxLines = 1,
                singleLine = true,
                value = evm.note.value,
                onValueChange = {
                    evm.note.value = it
                })

            Spacer(modifier = Modifier.padding(ButtonDefaults.IconSpacing + 20.dp))

            Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                Button(
                    onClick = {
                        if (evm.validate()) {
                            evm.insertAllEarnings()
                            navController.navigate(LEDGER)
                            Toast.makeText(context, "Current earnings inserted successfully", Toast.LENGTH_SHORT).show()
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
fun CurrentMonthReport(
    navController: NavHostController
) {

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview4() {
    JetpackComposeTheme {

    }
}
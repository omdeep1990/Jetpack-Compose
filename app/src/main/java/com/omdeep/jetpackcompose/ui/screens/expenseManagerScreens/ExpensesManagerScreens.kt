package com.omdeep.jetpackcompose.ui.screens.expenseManagerScreens

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.core.graphics.toColorInt
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.omdeep.jetpackcompose.R
import com.omdeep.jetpackcompose.data.factory.EarningsFactory
import com.omdeep.jetpackcompose.data.factory.ExpensesFactory
import com.omdeep.jetpackcompose.data.repository.EarningsRepository
import com.omdeep.jetpackcompose.data.repository.ExpensesRepository
import com.omdeep.jetpackcompose.data.room.earnings.EarningsDatabase
import com.omdeep.jetpackcompose.data.room.expenses.ExpensesDatabase
import com.omdeep.jetpackcompose.ui.navigation.ExpenseRoutes
import com.omdeep.jetpackcompose.ui.viewModel.EarningsViewModel
import com.omdeep.jetpackcompose.ui.viewModel.ExpensesViewModel
import java.text.SimpleDateFormat
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
    lifecycle: LifecycleOwner = LocalLifecycleOwner.current,
    viewModel: EarningsViewModel = viewModel(factory = EarningsFactory(EarningsRepository(EarningsDatabase.getInstance(context).dao))),
    exvm: ExpensesViewModel = viewModel(factory = ExpensesFactory(ExpensesRepository(ExpensesDatabase.getInstance(context).dao)))
) {
    //TODO: fetching current month name from calender: -
    val cal = Calendar.getInstance()
    val month = cal.get(Calendar.MONTH)
    val monthDate = SimpleDateFormat("MMMM", Locale.getDefault())
    val monthName: String = monthDate.format(cal.time)
    var totalEarnings : Float = 0F
    var totalExpenses : Float = 0F
//    val allEarnings = viewModel.fetchAllEarnings().observeAsState(initial = listOf())
    var allEarnings = viewModel.fetchAllEarningsBYMonth("01/${month+1}/2023", "31/${month+1}/2023")
    val allExpenses = exvm.fetchAllExpenses().observeAsState(initial = listOf())
    if (allEarnings.value != null) {
        for (i in allEarnings.value!!) {
            totalEarnings += i.amount.toFloat()
        }
    } else {
        Toast.makeText(context, "Non data found", Toast.LENGTH_SHORT).show()
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
            MyTopAppBar(title = "My Ledger")
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
                    text = "$monthName",
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

            Spacer(modifier = Modifier.padding(ButtonDefaults.IconSpacing))

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
                        .padding(5.dp),
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
                        .padding(5.dp),
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
                    navController.navigate(ExpenseRoutes.Earnings.route)
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
                    navController.navigate(ExpenseRoutes.Expenses.route)
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
    lifecycle: LifecycleOwner = LocalLifecycleOwner.current,
    focusManager: FocusManager = LocalFocusManager.current,
    evm: ExpensesViewModel = viewModel(factory = ExpensesFactory(ExpensesRepository(ExpensesDatabase.getInstance(context).dao))),
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

    //TODO: Date picker dialog: -
    val year: Int
    val month: Int
    val day: Int

    val mCalendar = Calendar.getInstance()
    year = mCalendar.get(Calendar.YEAR)
    month = mCalendar.get(Calendar.MONTH)
    day = mCalendar.get(Calendar.DAY_OF_MONTH)

    mCalendar.time = Date()
    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            evm.date.value = "$mDayOfMonth/${mMonth + 1}/$mYear"
        }, year, month, day
    )

    //TODO: Time picker dialog: -
    val hour = mCalendar[Calendar.HOUR_OF_DAY]
    val minute = mCalendar[Calendar.MINUTE]
    val timePickerDialog = TimePickerDialog(
        context,
        { _, mHour: Int, mMinute: Int ->
            evm.time.value = "$mHour:$mMinute"
        }, hour, minute, false
    )

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
                                    datePickerDialog.show()
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
                                    timePickerDialog.show()
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
                value = evm.expenses.value,
                onValueChange = {
                    evm.expenses.value = it
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
                            evm.insertAllExpenses()
                            Toast.makeText(context, "Current expenses inserted successfully", Toast.LENGTH_SHORT).show()
                            navController.navigate(ExpenseRoutes.Ledger.route)
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
    lifecycle: LifecycleOwner = LocalLifecycleOwner.current,
    focusManager: FocusManager = LocalFocusManager.current,
    evm: EarningsViewModel = viewModel(factory = EarningsFactory(EarningsRepository(EarningsDatabase.getInstance(context).dao))),
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

    //TODO: Date picker dialog: -
    val year: Int
    val month: Int
    val day: Int

    val mCalendar = Calendar.getInstance()
    year = mCalendar.get(Calendar.YEAR)
    month = mCalendar.get(Calendar.MONTH)
    day = mCalendar.get(Calendar.DAY_OF_MONTH)

    mCalendar.time = Date()
    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            evm.date.value = "$mDayOfMonth/${mMonth + 1}/$mYear"
        }, year, month, day
    )

    //TODO: Time picker dialog: -
    val hour = mCalendar[Calendar.HOUR_OF_DAY]
    val minute = mCalendar[Calendar.MINUTE]
    val timePickerDialog = TimePickerDialog(
        context,
        { _, mHour: Int, mMinute: Int ->
            evm.time.value = "$mHour:$mMinute"
        }, hour, minute, false
    )

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
                                    datePickerDialog.show()
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
                                    timePickerDialog.show()
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
                            navController.navigate(ExpenseRoutes.Ledger.route)
                            Toast.makeText(context, "Current earnings inserted successfully", Toast.LENGTH_SHORT).show()
                        }
                        navController.navigate(ExpenseRoutes.Ledger.route)
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

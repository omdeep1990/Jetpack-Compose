package com.omdeep.jetpackcompose.ui.screens.dataStore

import android.content.Context
import android.content.res.Configuration
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.omdeep.jetpackcompose.R
import com.omdeep.jetpackcompose.ui.screens.CustomTopAppBar
import com.omdeep.jetpackcompose.ui.screens.NavigateBackOnPress
import com.omdeep.jetpackcompose.ui.viewModel.DataStoreViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.omdeep.jetpackcompose.data.factory.DataStoreFactory
import com.omdeep.jetpackcompose.data.repository.DataStoreRepository
import com.omdeep.jetpackcompose.ui.screens.CustomSpacer
import com.omdeep.jetpackcompose.ui.screens.MySpacer
import com.omdeep.jetpackcompose.utils.Constants.PROTO_DATA_STORE
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@Composable
fun PreferenceScreen(
    navController: NavHostController
) {
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
    )

    val showModalSheet = rememberSaveable {
        mutableStateOf(false)
    }

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = { BottomSheetContent() }
    ) {
        PreferenceScreenContent(navController, sheetState, showModalSheet)
    }
}

@Composable
fun BottomSheetContent() {
    Surface(
        modifier = Modifier.height(300.dp),
        color = Color(0xff7353ba)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Modal Bottom Sheet",
                fontSize = 20.sp,
                modifier = Modifier.padding(10.dp),
                color = Color.White
            )
            Divider(
                modifier = Modifier.padding(5.dp),
                color = Color.White
            )
            Text(
                text = stringResource(R.string.jetpack_compose_content),
                fontSize = 15.sp,
                fontStyle = FontStyle.Italic,
                color = Color.White,
                modifier = Modifier.padding(10.dp)
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterialApi::class)
@Composable
fun PreferenceScreenContent(
    navController: NavHostController,
    sheetState: ModalBottomSheetState,
    showModalSheet: MutableState<Boolean>,
    scope : CoroutineScope = rememberCoroutineScope(),
    context: Context = LocalContext.current,
    configuration: Configuration = LocalConfiguration.current,
    focusManager: FocusManager = LocalFocusManager.current,
    viewModel: DataStoreViewModel = viewModel(factory = DataStoreFactory(DataStoreRepository(context))),
    keyboardController: SoftwareKeyboardController? = LocalSoftwareKeyboardController.current
) {
    Scaffold(
        modifier = Modifier
            .fillMaxWidth(1f)
            .wrapContentHeight(),
        topBar = {
            CustomTopAppBar(title = "Data Store", navigationIcon = {
                NavigateBackOnPress(navController)
            }, actions = {

            })
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

            MySpacer()

            Image(
                painter =
                rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current).data(data = R.drawable.user_profile)
                        .apply(block = fun ImageRequest.Builder.() {
                            scale(Scale.FILL)
                            transformations(CircleCropTransformation())
                        }).build()
                ),
                contentDescription = "profile",
                modifier = Modifier
                    .height(configuration.screenWidthDp.dp/3)
            )
            CustomSpacer(height = 20.dp)
            /*Preference data store screen*/
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(vertical = 10.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    autoCorrect = true,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }),
                label = { Text(text = "Name") },
                placeholder = { Text(text = "Enter name") },
                leadingIcon = {
                    Image(
                        modifier = Modifier
                            .width(30.dp)
                            .height(30.dp),
                        painter = painterResource(id = R.drawable.name),
                        contentDescription = "amount"
                    )
                },
                trailingIcon = {
                    Icon(
                        Icons.Default.Clear,
                        contentDescription = "clear text",
                        modifier = Modifier.clickable {
                            viewModel.name.value = ""
                        })
                },
                shape = RoundedCornerShape(10.dp),
                isError = false,
                maxLines = 1,
                singleLine = true,
                value = viewModel.name.value,
                onValueChange = {
                    viewModel.name.value = it
                })

            CustomSpacer(height = 20.dp)
            /*Phone number*/
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(vertical = 10.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    autoCorrect = true,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }),
                label = { Text(text = "Phone number") },
                placeholder = { Text(text = "Enter phone number") },
                leadingIcon = {
                    Image(
                        modifier = Modifier
                            .width(30.dp)
                            .height(30.dp),
                        painter = painterResource(id = R.drawable.telephone),
                        contentDescription = "amount"
                    )
                },
                trailingIcon = {
                    Icon(
                        Icons.Default.Clear,
                        contentDescription = "clear text",
                        modifier = Modifier.clickable {
                            viewModel.phone.value = ""
                        })
                },
                shape = RoundedCornerShape(10.dp),
                isError = false,
                maxLines = 1,
                singleLine = true,
                value = viewModel.phone.value,
                onValueChange = {
                    viewModel.phone.value = it
                })

            CustomSpacer(height = 20.dp)
            /*Address*/
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
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
                label = { Text(text = "Address") },
                placeholder = { Text(text = "Enter address") },
                leadingIcon = {
                    Image(
                        modifier = Modifier
                            .width(30.dp)
                            .height(30.dp),
                        painter = painterResource(id = R.drawable.address),
                        contentDescription = "amount"
                    )
                },
                trailingIcon = {
                    Icon(
                        Icons.Default.Clear,
                        contentDescription = "clear text",
                        modifier = Modifier.clickable {
                            viewModel.address.value = ""
                        })
                },
                shape = RoundedCornerShape(10.dp),
                isError = false,
                maxLines = 1,
                singleLine = true,
                value = viewModel.address.value,
                onValueChange = {
                    viewModel.address.value = it
                })

            CustomSpacer(height = 20.dp)
            /*Submit button*/
            Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                Button(
                    onClick = {
                        viewModel.saveData()
                        navController.navigate(PROTO_DATA_STORE)
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

            Box(modifier = Modifier.fillMaxSize()) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowUp,
                    contentDescription = "",
                    modifier = Modifier
                        .align(alignment = Alignment.BottomCenter)
                        .clickable {
                            showModalSheet.value = !showModalSheet.value
                            scope.launch {
                                sheetState.show()
                            }
                        }
                )
            }

        }
    }
}

@Composable
fun ProtoScreen(navController: NavHostController) {
    Scaffold(
        modifier = Modifier
            .fillMaxWidth(1f)
            .wrapContentHeight(),
        topBar = {
            CustomTopAppBar(title = "Data Store", navigationIcon = {
                NavigateBackOnPress(navController)
            }, actions = {

            })
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
                    .fillMaxWidth(0.9f)
                    .wrapContentHeight()
                    .padding(10.dp),
                shape = RoundedCornerShape(20.dp),
                backgroundColor = Color.White,
                elevation = 10.dp
            ) {
                Text(
                    text = "Preference Data Store coming in future",
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
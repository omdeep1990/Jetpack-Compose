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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.omdeep.jetpackcompose.R

import android.content.Context
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.omdeep.jetpackcompose.ui.theme.JetpackComposeTheme

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


/*Custom Widgets*/

@Composable
fun CustomCircularImage(
    data: Any?,
    modifier: Modifier,
    context: Context = LocalContext.current
) {
    Image(
        painter =
        rememberAsyncImagePainter(
            ImageRequest.Builder(context).data(data).apply(
                block = fun ImageRequest.Builder.() {
                    scale(Scale.FILL)
                    transformations(CircleCropTransformation())
                }).build()
        ),
        contentDescription = "krishna",
        modifier = modifier
    )
}

@Composable
fun CustomNormalImage(
    data: Any?,
    modifier: Modifier,
    context: Context = LocalContext.current
) {
    Image(
        painter =
        rememberAsyncImagePainter(
            ImageRequest.Builder(context).data(data).apply(
                block = fun ImageRequest.Builder.() {
                    scale(Scale.FILL)
                }).build()
        ),
        contentDescription = "krishna",
        modifier = modifier
    )
}

@Composable
fun CustomIcon(
    imageVector: ImageVector,
    modifier: Modifier
) {
    Icon(
        modifier = modifier,
        imageVector = imageVector,
        contentDescription = "close",
        tint = Color.Black
    )
}

@Composable
fun CustomTopAppBar(
    text: String,
    fontSize: TextUnit,
    fontStyle: FontStyle,
    fontWeight: FontWeight,
    actions: @Composable (RowScope.() -> Unit),
    navigationIcon: @Composable (() -> Unit)

) {
    TopAppBar(
        title = {
            Text(
                modifier = Modifier
                    .fillMaxWidth(1f),
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color("#FF5722".toColorInt()),
                            fontSize = fontSize,
                            fontStyle = fontStyle,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = fontWeight
                        )
                    ) {
                        append(text)
                    }
                    withStyle(
                        style = SpanStyle(
                            color = Color.Black,
                            fontSize = 15.sp,
                            fontStyle = fontStyle,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.Normal
                        )
                    ) {
                        append(" in Hindi")
                    }
                },
                textAlign = TextAlign.Start
            )
        },
        modifier =
        Modifier
            .fillMaxWidth(1f)
            .padding(5.dp),
        actions = {
            actions()
        },
        navigationIcon = {
            navigationIcon()
        },
        elevation = 0.dp,
        backgroundColor = Color.White
    )
}

@Preview(showBackground = true)
@Composable
fun CustomAppBar() {
    JetpackComposeTheme {
        CustomTopAppBar(
            text = "MahaKavyas",
            fontSize = 20.sp,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Bold,
            actions = { },
            navigationIcon = { }
        )
    }
}

@Composable
fun NormalTopAppBar(
    text: String,
    fontSize: TextUnit,
    fontStyle: FontStyle,
    fontWeight: FontWeight,
    actions: @Composable (RowScope.() -> Unit),
    navigationIcon: @Composable (() -> Unit)

) {
    TopAppBar(
        title = {
            Text(
                modifier = Modifier
                    .fillMaxWidth(0.9f),
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color("#FF5722".toColorInt()),
                            fontSize = fontSize,
                            fontStyle = fontStyle,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = fontWeight
                        )
                    ) {
                        append(text)
                    }
                },
                textAlign = TextAlign.Center
            )
        },
        modifier =
        Modifier
            .fillMaxWidth(1f)
            .padding(5.dp),
        actions = {
            actions()
        },
        navigationIcon = {
            navigationIcon()
        },
        elevation = 0.dp,
        backgroundColor = Color.White
    )
}

/*Custom Card View*/
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CustomCard(
    color: Color,
    modifier: Modifier,
    onClick: () -> Unit,
    shape: Shape,
    elevation: Dp,
    content: @Composable () -> Unit
) {
    Card(
        onClick = {
            onClick()
        },
        modifier = modifier,
        shape = shape,
        elevation = elevation,
        backgroundColor = color,
        content = {
            content()
        }
    )
}

@Preview(showBackground = true)
@Composable
fun CustomCardPreview() {
    JetpackComposeTheme {
        CustomCard(
            color = colorResource(id = R.color.light_pink),
            modifier = Modifier
                .fillMaxWidth(0.9f),
            onClick = {

            },
            shape = RoundedCornerShape(10.dp),
            elevation = 0.dp
        ) {

        }
    }
}

@Composable
fun CustomText(
    text: String,
    textStyle: TextStyle,
    modifier: Modifier
) {
    Text(
        modifier = modifier,
        text = text,
        style = textStyle
    )
}

@Composable
fun AllCategories(
    text: String,
    data: Any,
    modifier: Modifier,
    configuration: Configuration = LocalConfiguration.current,
    onClick: () -> Unit
) {
    /*Mantra Card*/
    CustomCard(
        color = colorResource(id = R.color.card_color),
        modifier = modifier,
        onClick = {
            onClick()
        },
        shape = RoundedCornerShape(10.dp),
        elevation = 0.dp
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            CustomNormalImage(
                data = data,
                modifier = Modifier
                    .width(configuration.screenWidthDp.dp / 8)
                    .height(configuration.screenWidthDp.dp / 8)
            )

            CustomText(
                modifier = Modifier
                    .fillMaxWidth(0.95f),
                text = text,
                textStyle = TextStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            )
        }
    }
}

@Composable
fun NavigationBackIcon(
    navController: NavHostController
) {

    CustomCard(
        color = colorResource(id = R.color.partial_transparent),
        modifier = Modifier
            .wrapContentSize(Alignment.Center),
        onClick = {

        },
        shape = CircleShape,
        elevation = 0.dp
    ) {
        IconButton(
            onClick = {
                navController.navigateUp()
            }) {
            Icon(
                modifier = Modifier
                    .wrapContentSize(),
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "back_icon",
                tint = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NavigationBackPreview() {
    val navController = rememberNavController()
    JetpackComposeTheme {
        NavigationBackIcon(navController)
    }
}

@Composable
fun CustomDivider() {
    Divider(
        modifier = Modifier
            .fillMaxWidth(0.9f),
        color = Color.DarkGray,
        thickness = 0.5.dp
    )
}

@Composable
fun AdjustDivider(
    fraction: Float
) {
    Divider(
        modifier = Modifier
            .fillMaxWidth(fraction),
        color = Color.DarkGray,
        thickness = 0.5.dp
    )
}

@Composable
fun CustomSpacer() {
    Spacer(modifier = Modifier.padding(ButtonDefaults.IconSpacing))
}

@Composable
fun AdjustSpacer(height: Dp) {
    Spacer(modifier = Modifier.height(height))
}

/*Common Custom Alert Dialog*/
@Composable
fun CommonAlertDialog(
    openDialog: MutableState<Boolean>,
    context: Context = LocalContext.current,
    activity: Activity = context as Activity
) {
    if (openDialog.value) {
        AlertDialog(
            modifier = Modifier
                .fillMaxWidth(1f)
                .wrapContentHeight(),
            onDismissRequest = { openDialog.value = true },
            title = {
                CustomText(
                    text = "बाहर निकलें",
                    textStyle = TextStyle(
                        fontSize = 25.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    ),
                    modifier = Modifier
                        .fillMaxWidth(1f)
                )
            },
            text = {
                CustomText(
                    text = "क्या आप बाहर निकलना चाहते हैं?",
                    textStyle = TextStyle(
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black
                    ),
                    modifier = Modifier
                        .fillMaxWidth(1f)
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                        activity.finishAffinity()
                    }
                ) {
                    CustomText(
                        text = "पुष्टि",
                        textStyle = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black
                        ),
                        modifier = Modifier
                            .wrapContentWidth()
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                    }
                ) {
                    CustomText(
                        text = "निरस्त",
                        textStyle = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black
                        ),
                        modifier = Modifier
                            .wrapContentWidth()
                    )
                }
            },
            shape = RoundedCornerShape(20.dp),
            backgroundColor = Color.White,
            contentColor = Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CustomAlertDialogPreview() {
    val openDialog = remember { mutableStateOf(true) }
    JetpackComposeTheme {
        CommonAlertDialog(openDialog)
    }
}

@Composable
fun SetTitle(text: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth(0.9f),
        text = buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color.Black)) {
                append(text)
            }
            withStyle(style = SpanStyle(color = Color.Red)) {
                append(" *")
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun SetTitlePreview() {
    JetpackComposeTheme {
        SetTitle(text = "Email")
    }
}

@Composable
fun CustomTextField(
    textValue: MutableState<String>,
    label: String,
    placeholder: String,
    isError: Boolean,
    trailingIcon: @Composable() (() -> Unit),
    leadingIcon: @Composable() (() -> Unit),
    keyboardType: KeyboardType,
    capitalization: KeyboardCapitalization,
    passwordVisible: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) }
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(0.9f),
        label = { Text(text = label) },
        placeholder = { Text(text = placeholder) },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            capitalization = capitalization
        ),
        visualTransformation =
        if (keyboardType == KeyboardType.Password) {
            if (passwordVisible.value) VisualTransformation.None
            else PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
        trailingIcon = {
            if (keyboardType == KeyboardType.Password) PasswordTrailingIcon(passwordVisible)
            else trailingIcon()
        },
        leadingIcon = {
            leadingIcon()
        },
        shape = RoundedCornerShape(10.dp),
        isError = isError,
        maxLines = 1,
        singleLine = true,
        value = textValue.value,
        onValueChange = {
            textValue.value = it
        })

}

/*Show Error*/
@Composable
fun ShowError(errorText: String) {
    CustomText(
        text = errorText,
        textStyle = TextStyle(
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Red,
            textAlign = TextAlign.Start
        ),
        modifier = Modifier
            .fillMaxWidth(0.9f)
    )
}

@Composable
fun PasswordTrailingIcon(visibility: MutableState<Boolean>) {
    val image = if (visibility.value) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
    val description = if (visibility.value) "Hide password" else "Show password"

    IconButton(onClick = { visibility.value = !visibility.value }) {
        Icon(imageVector = image, contentDescription = description)
    }
}

/*Custom Button*/
@Composable
fun CustomButton(
    text: String,
    gradient: Brush,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    onClick: () -> Unit
) {
    Button(
        onClick = {
            onClick()
        },
//            shape = RoundedCornerShape(50.dp),
        enabled = true,
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .wrapContentHeight(),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = Color.White,
            backgroundColor = Color.Transparent,
            disabledContentColor = Color.Yellow
        ),
        contentPadding = PaddingValues()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(gradient)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            CustomText(
                text = text,
                textStyle = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    color = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            )
        }

    }

}

@Preview(showBackground = true)
@Composable
fun ButtonPreview() {
    JetpackComposeTheme {
        CustomButton(
            text = "Register",
            gradient = Brush.horizontalGradient(
                colors = listOf(
                    Color(0xffF57F17),
                    Color(0xffFFEE58),
                    Color(0xffFFF9C4)
                )
            )
        ) {

        }
    }
}

@Composable
fun TitleScreen(
    title: String,
    openScreen: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .fillMaxHeight(0.4f)
            .clickable {
                openScreen()
            },
        shape = RoundedCornerShape(10.dp),
        backgroundColor = Color("#FFE6FFFF".toColorInt()),
        elevation = 10.dp,
        contentColor = Color.Black
    ) {
        Text(
            text = title,
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
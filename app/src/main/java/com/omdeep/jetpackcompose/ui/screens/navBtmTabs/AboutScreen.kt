package com.omdeep.jetpackcompose.ui.screens.navBtmTabs

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.StartOffset
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.omdeep.jetpackcompose.R
import com.omdeep.jetpackcompose.data.factory.RegisterFactory
import com.omdeep.jetpackcompose.data.repository.UserRepository
import com.omdeep.jetpackcompose.data.room.MainDatabase
import com.omdeep.jetpackcompose.ui.screens.CustomButton
import com.omdeep.jetpackcompose.ui.screens.CustomTextField
import com.omdeep.jetpackcompose.ui.screens.SetTitle
import com.omdeep.jetpackcompose.ui.screens.ShowError
import com.omdeep.jetpackcompose.ui.theme.JetpackComposeTheme
import com.omdeep.jetpackcompose.ui.theme.Magenta
import com.omdeep.jetpackcompose.ui.theme.Purple
import com.omdeep.jetpackcompose.ui.theme.Red
import com.omdeep.jetpackcompose.ui.viewModel.RegisterViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.lang.Math.PI
import java.lang.Math.cos
import java.lang.Math.min
import java.lang.Math.sin
import kotlin.math.absoluteValue
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin


private const val NumDots = 5
private const val AnimationDuration = 2000
private const val AnimationSegment = AnimationDuration / 10
private val MainDotSize = 24.dp

private val Float.alphaFromRadians: Float
    get() {
        val normalized = (this / (2f * PI)).toFloat()
        return .5f + (normalized - .5f).absoluteValue
    }
@Stable
interface ProgressState {
    fun start(scope: CoroutineScope)
    operator fun get(index: Int): Float
}

class ProgressStateImpl : ProgressState {
    private val animationValues: List<MutableState<Float>> = List(NumDots) {
        mutableStateOf(0f)
    }

    override operator fun get(index: Int) = animationValues[index].value

    override fun start(scope: CoroutineScope) {
        repeat(NumDots) { index ->
            scope.launch {
                animate(
                    initialValue = 0f,
                    targetValue = (2f * PI).toFloat(),
                    animationSpec = infiniteRepeatable(
                        animation = keyframes {
                            durationMillis = AnimationDuration
                            0f at 0
                            (.5 * PI).toFloat() at 2 * AnimationSegment
                            PI.toFloat() at 3 * AnimationSegment
                            (1.5 * PI).toFloat() at 4 * AnimationSegment
                            (2f * PI).toFloat() at 6 * AnimationSegment
                        },
                        repeatMode = RepeatMode.Restart,
                        initialStartOffset = StartOffset(offsetMillis = 100 * index)
                    ),
                ) { value, _ ->
                    animationValues[index].value = value
                }
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ProgressStateImpl

        if (animationValues != other.animationValues) return false

        return true
    }

    override fun hashCode(): Int = animationValues.hashCode()
}

@Composable
fun rememberProgressState(): ProgressState = remember {
    ProgressStateImpl()
}

@Composable
fun AboutScreen(viewModel : RegisterViewModel = viewModel(
    factory = RegisterFactory
        (UserRepository(MainDatabase.getInstance(LocalContext.current).dao))
)) {
    Box(
        modifier = Modifier
            .fillMaxSize(1f),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(0.3f),
            contentAlignment = Alignment.Center
        ) {
            ProgressIndicator()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        /*Email*/
        SetTitle(text = "Email")
        CustomTextField(
            textValue = viewModel.email,
            label = "Email",
            placeholder = "Enter your email",
            isError = false,
            trailingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Clear,
                        contentDescription = "clear",
                        modifier = Modifier.clickable {
                            
                        })
                
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Email,
                    contentDescription = "email",
                )
            },
            keyboardType = KeyboardType.Email,
            capitalization = KeyboardCapitalization.None
        )

        /*Show email error*/
        ShowError(
            errorText = "Invalid email"
        )

        /*Password*/
        SetTitle(text = "Password")
        CustomTextField(
            textValue = viewModel.password,
            label = "Password",
            placeholder = "Enter your password",
            isError = false,
            trailingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Clear,
                        contentDescription = "clear",
                        modifier = Modifier.clickable {
                        })

            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Password,
                    contentDescription = "password",
                )
            },
            keyboardType = KeyboardType.Password,
            capitalization = KeyboardCapitalization.None
        )

        /*Show email error*/
        ShowError(
            errorText = "Invalid password"
        )

        /*Register Button*/
        CustomButton(
            text = "Register",
            gradient = Brush.horizontalGradient(
                colors = listOf(
                    Magenta,
                    Red,
                    Purple
                ))
        ) {

        }
    }

}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    AboutScreen()
}


@Composable
fun ProgressIndicator(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.primary,
) {
    val state = rememberProgressState()
    LaunchedEffect(key1 = Unit) {
        state.start(this)
    }
    Layout(
        content = {
            val minFactor = .3f
            val step = minFactor / NumDots
            repeat(NumDots) { index ->
                val size = MainDotSize * (1f - step * index)
                Dot(
                    color = color,
                    modifier = Modifier
                        .requiredSize(size)
                        .graphicsLayer {
                            alpha = state[index].alphaFromRadians
                        },
                )
            }
        },
        modifier = modifier,
    ) { measurables, constraints ->
        val looseConstraints = constraints.copy(
            minWidth = 0,
            minHeight = 0,
        )
        val placeables = measurables.map { measurable -> measurable.measure(looseConstraints) }
        layout(
            width = constraints.maxWidth,
            height = constraints.maxHeight,
        ) {
            val radius = min(constraints.maxWidth, constraints.maxHeight) / 2f
            placeables.forEachIndexed { index, placeable ->
                val animatedValue = state[index]
                val x = (radius + radius * sin(animatedValue)).roundToInt()
                val y = (radius - radius * cos(animatedValue)).roundToInt()
                placeable.placeRelative(
                    x = x,
                    y = y,
                )
            }
        }
    }
}

@Composable
private fun Dot(
    color: Color,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .clip(shape = CircleShape)
            .background(color = color)
    )
}

@Preview(widthDp = 360, showBackground = true)
@Composable
fun PreviewDot() {
    JetpackComposeTheme {
        Dot(
            color = MaterialTheme.colors.primary,
            modifier = Modifier
                .padding(all = 32.dp)
                .requiredSize(32.dp)
        )
    }
}

@Preview(widthDp = 360, showBackground = true)
@Composable
private fun PreviewProgressIndicator() {
    JetpackComposeTheme {
        ProgressIndicator(
            modifier = Modifier.padding(all = 32.dp)
        )
    }
}
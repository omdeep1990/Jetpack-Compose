package com.omdeep.jetpackcompose.ui.screens.navBtmTabs

import android.content.Context
import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.omdeep.jetpackcompose.R
import com.omdeep.jetpackcompose.data.model.TimeList
import com.omdeep.jetpackcompose.ui.screens.MySpacer

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.light_blue))
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Lazy Vertical Grid Demo",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 25.sp
        )
        MySpacer()
        TimeUI()

        MySpacer()
        TimeUI()

        MySpacer()
        TimeUI()

        MySpacer()
        LazyVerticalGridAnotherDemo()
    }
}

@Preview(showBackground = true)
@Composable
fun MusicScreenPreview() {
    ProfileScreen()
}

@Composable
fun TimeUI(context: Context = LocalContext.current) {
    var selectedIndex by remember {
        mutableStateOf(-1)
    }
    val list = mutableListOf<TimeList>().apply {
        add(TimeList("10:00"))
        add(TimeList("10:30"))
        add(TimeList("11:00"))
        add(TimeList("11:30"))
        add(TimeList("12:00"))
        add(TimeList("12:30"))
        add(TimeList("01:00"))
        add(TimeList("01:30"))
        add(TimeList("02:00"))
        add(TimeList("02:30"))
    }

    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxWidth(0.9f),
        columns = GridCells.Fixed(4),
        userScrollEnabled = true,
        reverseLayout = false,
        flingBehavior = ScrollableDefaults.flingBehavior(),
        contentPadding = PaddingValues(10.dp),
//        verticalArrangement = if (!reverseLayout) Arrangement.Top else Arrangement.Bottom,
        horizontalArrangement = Arrangement.Start,
    ) {
        itemsIndexed(list) { index, item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth(0.25f)
                    .padding(6.dp)
                    .selectable(
                        selected = selectedIndex == index,
                        onClick = {
                            selectedIndex = index
                            Toast
                                .makeText(
                                    context, "${item.time} at $index, $selectedIndex",
                                    Toast.LENGTH_SHORT
                                )
                                .show()
                        }
                    ),
                backgroundColor = if (selectedIndex == index) Color.Blue else Color.White,
                elevation = 10.dp,
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(2.dp),
                    text = item.time,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W700,
                        fontStyle = FontStyle.Normal,
                        textAlign = TextAlign.Center,
                        color = if (selectedIndex == index) Color.White else Color.Blue
                    )
                )
            }
        }
    }
}

@Composable
fun LazyVerticalGridAnotherDemo(
    configuration: Configuration = LocalConfiguration.current
){
    val list = (1..10).map { it.toString() }

    LazyVerticalGrid(
        columns = GridCells.Adaptive(configuration.screenWidthDp.dp/3),

        // content padding
        contentPadding = PaddingValues(
            start = 12.dp,
            top = 16.dp,
            end = 12.dp,
            bottom = 16.dp
        ),
        content = {
            items(list.size) { index ->
                Card(
                    backgroundColor = Color.Red,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth(),
                    elevation = 8.dp,
                ) {
                    Text(
                        text = list[index],
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    )
}
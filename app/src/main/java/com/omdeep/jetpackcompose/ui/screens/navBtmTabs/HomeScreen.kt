package com.omdeep.jetpackcompose.ui.screens.navBtmTabs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.omdeep.jetpackcompose.R
import com.omdeep.jetpackcompose.ui.activity.MainActivityLayout
import com.omdeep.jetpackcompose.ui.viewModel.ApiViewModel

@Composable
fun HomeScreen(viewModel : ApiViewModel, isLoading : Boolean) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.light_blue))
            .wrapContentSize(Alignment.Center)
    ) {
//        Text(
//            text = "Home View",
//            fontWeight = FontWeight.Bold,
//            color = Color.White,
//            modifier = Modifier.align(Alignment.CenterHorizontally),
//            textAlign = TextAlign.Center,
//            fontSize = 25.sp
//        )
        MainActivityLayout(viewModel = viewModel, isLoading = isLoading)
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
//    HomeScreen()
}
package com.omdeep.jetpackcompose.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.omdeep.jetpackcompose.ui.activity.ui.theme.JetpackComposeTheme
import com.omdeep.jetpackcompose.ui.screens.MainList
import com.omdeep.jetpackcompose.ui.screens.MainScreen
import com.omdeep.jetpackcompose.ui.viewModel.ApiViewModel
import com.omdeep.jetpackcompose.utils.Status

class MainActivity : ComponentActivity() {
    private val viewModel by lazy {
        ViewModelProvider(this)[ApiViewModel::class.java]
    }
    private var isLoading: Boolean by mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen(viewModel, isLoading)
                }
            }
        }
        viewModel.progressBarStatus.observe(this, Observer {
            isLoading = when (it.status) {
                Status.SUCCESS -> {
                    false
                }
                Status.LOADING -> {
                    true
                }
                Status.ERROR -> {
                    true
                }
            }
        })
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainActivityLayout(
    viewModel: ApiViewModel,
    isLoading: Boolean
) {
    if (isLoading) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator()
        }
    } else {
        MainList(mainViewModel = viewModel)
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeTheme {
//        MainScreen()
    }
}
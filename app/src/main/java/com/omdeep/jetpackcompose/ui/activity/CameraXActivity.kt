package com.omdeep.jetpackcompose.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.omdeep.jetpackcompose.data.camerax.CameraX
import com.omdeep.jetpackcompose.ui.theme.JetpackComposeTheme
import com.omdeep.jetpackcompose.ui.screens.CameraCompose
import com.omdeep.jetpackcompose.utils.Permissions.allPermissionsGranted

class CameraXActivity : ComponentActivity() {
    private var cameraX: CameraX = CameraX(this, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CameraCompose(this, cameraX = cameraX) {
                        if (allPermissionsGranted(this)) {
                            cameraX.capturePhoto()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    JetpackComposeTheme {
        Greeting("Android")
    }
}
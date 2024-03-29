package com.omdeep.jetpackcompose.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.omdeep.jetpackcompose.data.camerax.CameraX
import com.omdeep.jetpackcompose.ui.theme.JetpackComposeTheme
import com.omdeep.jetpackcompose.ui.screens.NavComposeApp
import com.omdeep.jetpackcompose.utils.Constants.IMAGE_PATH
import com.omdeep.jetpackcompose.utils.Constants.JPG_EXT

class TabsActivity : ComponentActivity() {
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
                    intent.apply {
                        val imagePath = getStringExtra(IMAGE_PATH)
                        if (imagePath != null) {
                            if (imagePath.contains(JPG_EXT)) {
                                NavComposeApp(imagePath.removePrefix(JPG_EXT))
                            }
                        } else {
                            NavComposeApp("")
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    JetpackComposeTheme {
//        NavComposeApp()
    }
}
package com.omdeep.jetpackcompose.ui.screens.firebase

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Buttons(title: String, onClick: () -> Unit, backgroundColor: Color) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            backgroundColor = Color.Blue,
            disabledContentColor = Color.Yellow
        ),
        modifier = Modifier.fillMaxWidth().height(50.dp),
        shape = RoundedCornerShape(50.dp),
        contentPadding = PaddingValues(
            start = 20.dp,
            top = 12.dp,
            end = 20.dp,
            bottom = 12.dp
        )
    ) {
        Text(
            text = title
        )
    }
}
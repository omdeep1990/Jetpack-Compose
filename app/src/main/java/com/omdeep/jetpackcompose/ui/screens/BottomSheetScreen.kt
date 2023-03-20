package com.omdeep.jetpackcompose.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BottomSheetScreen(onCancel: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier
                .clickable {
                    onCancel()
                },
            imageVector = Icons.Default.Clear,
            contentDescription = "cancel"
        )

        Card(
            modifier = Modifier
                .fillMaxWidth(0.95f),
            elevation = 10.dp,
            backgroundColor = Color.White,
            shape = RoundedCornerShape(20.dp)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "My Bottom Sheet",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                fontFamily = FontFamily.SansSerif
            )
        }
    }
}
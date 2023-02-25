package com.omdeep.jetpackcompose.ui.screens.firebase

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import com.omdeep.jetpackcompose.data.model.FirebaseMessage

@Composable
fun DisplaySenderMessages(message: FirebaseMessage) {
    Row(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.Bottom
    ) {

    }
    Card(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .wrapContentHeight(),
        shape = RoundedCornerShape(20.dp, 0.dp, 20.dp, 20.dp),
        elevation = 10.dp,
        backgroundColor = Color("#A3F6B5".toColorInt())
    ) {
        message.message?.let {
            Text(text = it, color = Color.Black)
        }
    }
}

@Composable
fun DisplayReceiverMessages(message: FirebaseMessage) {
    Row(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.Bottom
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .wrapContentHeight(),
            shape = RoundedCornerShape(20.dp, 0.dp, 20.dp, 20.dp),
            elevation = 10.dp,
            backgroundColor = Color("#75D1FA".toColorInt())
        ) {
            message.message?.let {
                Text(text = it, color = Color.Blue)
            }
        }
    }
}
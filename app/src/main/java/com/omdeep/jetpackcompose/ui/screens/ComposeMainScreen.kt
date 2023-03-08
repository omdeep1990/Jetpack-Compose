package com.omdeep.jetpackcompose.ui.screens

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.omdeep.jetpackcompose.ui.activity.BarcodeAnalyserActivity
import com.omdeep.jetpackcompose.ui.activity.ExpensesManagerActivity
import com.omdeep.jetpackcompose.ui.activity.MainActivity

@Composable
fun ComposeMainScreen(
    context: Context = LocalContext.current
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(1f)
            .fillMaxHeight(1f)
            .padding(10.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(1f)
                .fillMaxHeight(0.4f)
//                .weight(1f)
                .clickable {
                    context.startActivity(Intent(context, MainActivity::class.java))
                },
            shape = RoundedCornerShape(10.dp),
            backgroundColor = Color("#FFE6FFFF".toColorInt()),
            elevation = 10.dp,
            contentColor = Color.Black
        ) {
            Text(
                text = "Navigation/Bottom Nav",
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

        Card(
            modifier = Modifier
                .fillMaxWidth(1f)
                .fillMaxHeight(0.4f)
//                .weight(1f)
                .clickable {
                           context.startActivity(Intent(context, ExpensesManagerActivity::class.java))
                },
            shape = RoundedCornerShape(10.dp),
            backgroundColor = Color("#FFE6FFFF".toColorInt()),
            elevation = 10.dp,
            contentColor = Color.Black
        ) {
            Text(
                text = "ExpenseMainScreen",
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

        Card(
            modifier = Modifier
                .fillMaxWidth(1f)
                .fillMaxHeight(0.4f)
//                .weight(1f)
                .clickable {
                    context.startActivity(Intent(context, BarcodeAnalyserActivity::class.java))
                },
            shape = RoundedCornerShape(10.dp),
            backgroundColor = Color("#FFE6FFFF".toColorInt()),
            elevation = 10.dp,
            contentColor = Color.Black
        ) {
            Text(
                text = "BarCode Analyser",
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
}

package com.omdeep.jetpackcompose.ui.screens.navBtmTabs

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.omdeep.jetpackcompose.R
import androidx.compose.material.Button
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.omdeep.jetpackcompose.data.factory.RegisterFactory
import com.omdeep.jetpackcompose.data.repository.UserRepository
import com.omdeep.jetpackcompose.data.room.MainDatabase
import com.omdeep.jetpackcompose.ui.screens.OtpTextField
import com.omdeep.jetpackcompose.ui.viewModel.RegisterViewModel
import com.togitech.ccp.component.TogiCountryCodePicker
import com.togitech.ccp.component.getErrorStatus
import com.togitech.ccp.component.getFullPhoneNumber
import com.togitech.ccp.component.getOnlyPhoneNumber
import com.togitech.ccp.component.isPhoneNumber

@Composable
fun YogaScreen(
    context: Context = LocalContext.current,
    viewModel: RegisterViewModel = viewModel(
        factory = RegisterFactory(UserRepository(MainDatabase.getInstance(LocalContext.current).dao))
    )
) {
    var otpValue by remember { mutableStateOf("") }
    var otpFilledValue by remember { mutableStateOf("") }
    var otpFillToCheckValue by remember { mutableStateOf(false) }
    val fullPhoneNumber = rememberSaveable { mutableStateOf("") }
    val onlyPhoneNumber = rememberSaveable { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.light_blue))
            .wrapContentSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Books View",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 25.sp
        )

        TogiCountryCodePicker(
            text = viewModel.mobileNo.value,
            onValueChange = { viewModel.mobileNo.value = it },
            unfocusedBorderColor = MaterialTheme.colors.primary,
            bottomStyle = false, //  if true the text-field is below the country code selector at the top.
            shape = RoundedCornerShape(10.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = {
            if (!isPhoneNumber()) {
                fullPhoneNumber.value = getFullPhoneNumber()
                onlyPhoneNumber.value = getOnlyPhoneNumber()
            } else {
                fullPhoneNumber.value = "Error"
                onlyPhoneNumber.value = "Error"
            }
        }) {
            Text(text = "Check")
        }
        Text(
            text = "Full Phone Number: ${fullPhoneNumber.value}",
            color = if (getErrorStatus()) Color.Red else Color.Green
        )

        Text(
            text = "Only Phone Number: ${onlyPhoneNumber.value}",
            color = if (getErrorStatus()) Color.Red else Color.Green
        )

        OtpTextField(
            otpText = otpValue,
            onOtpTextChange = { value, otpInputFilled ->
                otpValue = value
                otpFilledValue = otpInputFilled.toString()
                otpFillToCheckValue = otpInputFilled
            }
        )

        Button(
            onClick = {
            if (otpFillToCheckValue) {
                Toast.makeText(context, "Otp verified successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Please input all fields", Toast.LENGTH_SHORT).show()
            }
        }) {
            Text(text = "Check")
        }
        if (otpFillToCheckValue) {
            Text(text = otpValue, fontSize = 15.sp, fontWeight = FontWeight.SemiBold, color = Color.Black)
}
        Log.d("newOtp", "$otpValue $otpFilledValue")
    }
}

@Preview(showBackground = true)
@Composable
fun BooksScreenPreview() {
    YogaScreen()
}
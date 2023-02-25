package com.omdeep.jetpackcompose.ui.screens.tabs

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.omdeep.jetpackcompose.R
import com.omdeep.jetpackcompose.data.model.FirebaseUser
import com.omdeep.jetpackcompose.ui.activity.MainActivity
import com.omdeep.jetpackcompose.ui.viewModel.FirebaseHomeViewModel
import com.omdeep.jetpackcompose.utils.Constants.UID

@Composable
fun ChatScreen() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .background(colorResource(id = R.color.white))
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = "Testing")
        UsersListInChats()
    }
}

@Preview(showBackground = true)
@Composable
fun ChatScreenPreview() {
    ChatScreen()
}

@Composable
fun DisplayRegisteredUsersInChats(users: FirebaseUser, context: Context = LocalContext.current) {
    val userProfile by remember {
        mutableStateOf(users.imagePath)
    }

    Card(
        modifier = Modifier
            .wrapContentSize()
            .clickable {
                context.startActivity(Intent(context, MainActivity::class.java).apply {
                    putExtra(UID, users.uid)
                })
            }
    ) {
        Row(
            modifier = Modifier
                .padding(4.dp)
                .wrapContentHeight()
        ) {
            Image(
                painter =
//                if (userProfile != null) {
//                    rememberAsyncImagePainter(
//                        ImageRequest.Builder(LocalContext.current).data("$userProfile").apply(
//                            block = fun ImageRequest.Builder.() {
//                                scale(Scale.FILL)
//                                transformations(CircleCropTransformation())
//                            }).build()
//                    )
//                } else {
                    painterResource(id = R.drawable.profile1),
//                },
                contentDescription = "Userprofile",
                modifier = Modifier
                    .height(50.dp)
                    .weight(0.2f)
            )

            Column(
                modifier = Modifier
                    .padding(4.dp)
                    .wrapContentHeight()
                    .weight(0.8f)
            ) {
                users.name?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.Bold
                    )
                }

                users.mobileNo?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))

                Text(
                    text = "11:37 AM",
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.End,
                    color = Color.Gray,
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Composable
fun UsersListInChats(viewModel: FirebaseHomeViewModel = viewModel()) {
    LazyColumn(state = rememberLazyListState()) {
        items(
            items = viewModel.usersListData,
            itemContent = {
                DisplayRegisteredUsersInChats(users = it)
            }
        )
    }
}
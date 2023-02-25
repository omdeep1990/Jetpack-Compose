package com.omdeep.jetpackcompose.ui.screens.firebase

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import androidx.lifecycle.viewmodel.compose.viewModel
import com.omdeep.jetpackcompose.R
import com.omdeep.jetpackcompose.ui.viewModel.ChatViewModel

@Composable
fun UserSendMessage(uid: String, viewModel: ChatViewModel = viewModel()) {
    val message: String by viewModel.message.observeAsState("")
    viewModel.retrieveMessages(uid)

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(
            state = rememberLazyListState(),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.9f),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            userScrollEnabled = true
        ) {
            items(
                items = viewModel.chatList
            ) {
                Row(
                    horizontalArrangement = if (viewModel.auth.currentUser?.uid == it.uid)
                        Arrangement.Start else Arrangement.End
                ) {
                    if (viewModel.auth.currentUser?.uid == it.uid) {

                    } else {

                    }
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .width(335.dp)
                    .padding(5.dp),
                label = { Text(text = "Message") },
                shape = RoundedCornerShape(50.dp),
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.emoji),
                        contentDescription = "searchIcon"
                    )
                },
                trailingIcon = {
                    TrailingIcons()
                },
                maxLines = 1,
                singleLine = true,
                value = message,
                onValueChange = {
                    viewModel.updateMsgOnValueChange(it)
                }
            )
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            FloatingActionButton(
                onClick =
                {
                    if (message != "") {
                        viewModel.sendMessage(message, uid)
                        viewModel._message.value = ""
                    }
                }, backgroundColor = Color("#FF32BDAD".toColorInt())
            ) {
                Icon(
                    modifier = Modifier
                        .height(30.dp)
                        .width(30.dp)
                        .clickable {
                            if (message != "") {
                                viewModel.sendMessage(message, uid)
                                viewModel._message.value = ""
                            }
                        },
                    painter = painterResource(id = R.drawable.baseline_send_24),
                    contentDescription = "send",
                    tint = Color.White
                )
            }
        }
    }
}

@Composable
fun TrailingIcons() {
    Row(
        modifier = Modifier
            .wrapContentHeight()
            .wrapContentWidth()
    ) {
        Icon(
            painter = painterResource(id = R.drawable.attach),
            contentDescription = "attachment"
        )
        Icon(
            painter = painterResource(id = R.drawable.rupee),
            contentDescription = "rupee"
        )
        Icon(
            painter = painterResource(id = R.drawable.camera),
            contentDescription = "camera"
        )
    }
}
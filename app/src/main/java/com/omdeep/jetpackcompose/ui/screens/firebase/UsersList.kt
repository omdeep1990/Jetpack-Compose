package com.omdeep.jetpackcompose.ui.screens.firebase

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import com.omdeep.jetpackcompose.data.model.FirebaseUser
import com.omdeep.jetpackcompose.ui.viewModel.FirebaseHomeViewModel

@Composable
fun UsersList(users: FirebaseUser, context: Context = LocalContext.current) {
    var bgColor by rememberSaveable(users.uid) {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .padding(8.dp, 4.dp)
            .fillMaxWidth()
            .height(110.dp)
            .clickable {
                bgColor = !bgColor
                Toast
                    .makeText(context, "${users.name}", Toast.LENGTH_SHORT)
                    .show()
            },
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp,
        backgroundColor =
        if (bgColor) {
            Color("#87CEEB".toColorInt())
        } else {
            Color.White
        }
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(4.dp)
                .fillMaxHeight()
        ) {
            Text(
                text = users.name!!,
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = users.mobileNo!!,
                style = MaterialTheme.typography.caption,
                color = Color.Black,
                modifier = Modifier
                    .background(
                        Color.LightGray
                    )
                    .padding(4.dp)
            )
            Text(
                text = users.email!!,
                style = MaterialTheme.typography.body1,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = Color.Black
            )
        }
    }
}

@Composable
fun UsersListScreen(
    home: () -> Unit,
    vm: FirebaseHomeViewModel = viewModel(),
    context: Context = LocalContext.current
) {
    vm.usersList(context)
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(
            items = vm.usersListData,
            itemContent = { UsersList(users = it) }
        )
    }
}
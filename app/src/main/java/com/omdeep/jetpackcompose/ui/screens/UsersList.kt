package com.omdeep.jetpackcompose.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.omdeep.jetpackcompose.data.model.FirebaseUser
import com.omdeep.jetpackcompose.ui.viewModel.FirebaseHomeViewModel

@Composable
fun UsersList(users : FirebaseUser) {
    Card(
        modifier = Modifier
            .padding(8.dp, 4.dp)
            .fillMaxWidth()
            .height(110.dp), shape = RoundedCornerShape(8.dp), elevation = 4.dp
    ) {
//        Surface {
//            Row(
//                Modifier
//                    .padding(4.dp)
//                    .fillMaxSize()
//            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxHeight()
//                        .weight(0.8f)
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
//            }
//        }
    }
}

@ExperimentalFoundationApi
@Composable
fun GetUsersFromViewModel(vm : FirebaseHomeViewModel = viewModel()) {
    UsersListAll(users = vm.totalUsers)
}

@Composable
fun UsersListAll(users : List<FirebaseUser>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ){
        items(
            items = users,
            itemContent = { UsersList(users = it) }
        )
    }
}
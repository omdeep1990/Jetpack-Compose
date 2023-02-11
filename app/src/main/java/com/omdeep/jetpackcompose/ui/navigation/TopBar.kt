package com.omdeep.jetpackcompose.ui.navigation

import android.content.Intent
import android.widget.Toast
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.omdeep.jetpackcompose.R
import com.omdeep.jetpackcompose.ui.activity.TabsActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun TopBar(scope: CoroutineScope, scaffoldState: ScaffoldState) {
    var mDisplayMenu by remember { mutableStateOf(false) }
    val mContext = LocalContext.current

    TopAppBar(
        title = { Text(text = stringResource(R.string.app_name), fontSize = 18.sp) },
        navigationIcon = {
            IconButton(onClick = {
                scope.launch {
                    scaffoldState.drawerState.open()
                }
            }) {
                Icon(Icons.Filled.Menu, "")
            }
        },
        actions = {
            IconButton(onClick = { Toast.makeText(mContext, "Favorite", Toast.LENGTH_SHORT).show() }) {
                Icon(Icons.Default.Favorite, "")
            }
            IconButton(onClick = { mDisplayMenu = !mDisplayMenu }) {
                Icon(Icons.Default.MoreVert, "")
            }
            DropdownMenu(
                expanded = mDisplayMenu,
                onDismissRequest = { mDisplayMenu = false }
            ) {
                DropdownMenuItem(onClick = {
                    mContext.startActivity(Intent(mContext, TabsActivity::class.java))
                    Toast.makeText(mContext, "Tabs opened!", Toast.LENGTH_SHORT).show() }) {
                    Text(text = "WhatsApp")
                }
                DropdownMenuItem(onClick = { Toast.makeText(mContext, "Logout", Toast.LENGTH_SHORT).show() }) {
                    Text(text = "Others")
                }
            }
        },
        backgroundColor = colorResource(id = R.color.blue),
        contentColor = Color.White
    )
}

@Preview(showBackground = false)
@Composable
fun TopBarPreview() {
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    TopBar(scope = scope, scaffoldState = scaffoldState)
}
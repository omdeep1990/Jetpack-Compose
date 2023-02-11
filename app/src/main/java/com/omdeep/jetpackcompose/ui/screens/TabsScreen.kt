package com.omdeep.jetpackcompose.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.omdeep.jetpackcompose.R
import com.google.accompanist.pager.*
import com.omdeep.jetpackcompose.data.model.tabItem.TabItem
import com.omdeep.jetpackcompose.data.model.tabItem.TabItem.Calls
import com.omdeep.jetpackcompose.data.model.tabItem.TabItem.Chats
import com.omdeep.jetpackcompose.data.model.tabItem.TabItem.Status
import com.omdeep.jetpackcompose.data.model.tabItem.TabItem.Community

import kotlinx.coroutines.launch

@Composable
fun TopBarWithDropDownMenu() {
    var mDisplayMenu by remember { mutableStateOf(false) }
    val mContext = LocalContext.current
    TopAppBar(
        title = { Text(text = stringResource(R.string.whatsapp), fontSize = 18.sp) },
        backgroundColor = colorResource(id = R.color.teal_green),
        contentColor = Color.White,
        elevation = 0.dp,
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
                DropdownMenuItem(onClick = { Toast.makeText(mContext, "Settings", Toast.LENGTH_SHORT).show() }) {
                    Text(text = "Settings")
                }
                DropdownMenuItem(onClick = { Toast.makeText(mContext, "Logout", Toast.LENGTH_SHORT).show() }) {
                    Text(text = "Logout")
                }
            }
        }
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabsMainScreen() {
    val tabs = listOf(Community, Chats, Status, Calls)
    val pagerState = rememberPagerState()
    Scaffold(
        topBar = { TopBarWithDropDownMenu() },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = { FloatingActionButton(onClick = {}){
            Icon(painter = painterResource(id = R.drawable.chats), contentDescription = "Fab Description",
            modifier = Modifier.height(30.dp).width(30.dp))
        } },
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            Tabs(tabs = tabs, pagerState = pagerState)
            TabsContent(tabs = tabs, pagerState = pagerState)
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Tabs(tabs: List<TabItem>, pagerState: PagerState) {
    val scope = rememberCoroutineScope()
    // OR ScrollableTabRow()
    TabRow(
        // Our selected tab is our current page
        selectedTabIndex = pagerState.currentPage,
        // Override the indicator, using the provided pagerTabIndicatorOffset modifier
        backgroundColor = colorResource(id = R.color.teal_green),
        contentColor = Color.White,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
            )
        }) {
        // Add tabs for all of our pages
        tabs.forEachIndexed { index, tab ->
            // OR Tab()
            LeadingIconTab(
                icon = {
                    tab.icon?.let { painterResource(id = it) }
                        ?.let { Icon(painter = it, contentDescription = "") }
                },
                text = { tab.title?.let { Text(it) } },
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Preview(showBackground = true)
@Composable
fun TabsPreview() {
    val tabs = listOf(Community, Chats, Status, Calls)
    val pagerState = rememberPagerState()
    Tabs(tabs = tabs, pagerState = pagerState)
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabsContent(tabs: List<TabItem>, pagerState: PagerState) {
    HorizontalPager(state = pagerState, count = tabs.size) { page ->
        tabs[page].screen()
    }
}


@OptIn(ExperimentalPagerApi::class)
@Preview(showBackground = true)
@Composable
fun TabsContentPreview() {
    val tabs = listOf(Community, Chats, Status, Calls)
    val pagerState = rememberPagerState()
    TabsContent(tabs = tabs, pagerState = pagerState)
}
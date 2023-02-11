package com.omdeep.jetpackcompose.data.model.tabItem

import androidx.compose.runtime.Composable
import com.omdeep.jetpackcompose.R
import com.omdeep.jetpackcompose.ui.screens.CallScreen
import com.omdeep.jetpackcompose.ui.screens.ChatScreen
import com.omdeep.jetpackcompose.ui.screens.CommunityScreen
import com.omdeep.jetpackcompose.ui.screens.StatusScreen

typealias ComposableFun = @Composable () -> Unit

sealed class TabItem(var icon: Int? = null, var title: String? = null, var screen: ComposableFun) {
    object Community : TabItem(R.drawable.people, null, { CommunityScreen() })
    object Chats : TabItem(null, "Chats", { ChatScreen() })
    object Status : TabItem(null, "Status", { StatusScreen() })
    object Calls : TabItem(null, "Calls", { CallScreen() })
}
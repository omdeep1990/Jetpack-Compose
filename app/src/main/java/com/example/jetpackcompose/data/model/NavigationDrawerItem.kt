package com.example.jetpackcompose.data.model

import com.example.jetpackcompose.R
import com.example.jetpackcompose.utils.Constants.ABOUT
import com.example.jetpackcompose.utils.Constants.HOME
import com.example.jetpackcompose.utils.Constants.PROFILE
import com.example.jetpackcompose.utils.Constants.VIDEO
import com.example.jetpackcompose.utils.Constants.YOGA
import com.example.jetpackcompose.utils.Constants.about
import com.example.jetpackcompose.utils.Constants.home
import com.example.jetpackcompose.utils.Constants.profile
import com.example.jetpackcompose.utils.Constants.video
import com.example.jetpackcompose.utils.Constants.yoga

sealed class NavDrawerItem(var route: String, var icon: Int, var title: String) {
    object Home : NavDrawerItem(home, R.drawable.home, HOME)
    object Profile : NavDrawerItem(profile, R.drawable.profile, PROFILE)
    object Video : NavDrawerItem(video, R.drawable.videos, VIDEO)
    object Yoga : NavDrawerItem(yoga, R.drawable.yoga, YOGA)
    object About : NavDrawerItem(about, R.drawable.about, ABOUT)
}
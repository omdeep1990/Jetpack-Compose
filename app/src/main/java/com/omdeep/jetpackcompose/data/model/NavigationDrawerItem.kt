package com.omdeep.jetpackcompose.data.model

import com.omdeep.jetpackcompose.R
import com.omdeep.jetpackcompose.utils.Constants.ABOUT
import com.omdeep.jetpackcompose.utils.Constants.HOME
import com.omdeep.jetpackcompose.utils.Constants.PROFILE
import com.omdeep.jetpackcompose.utils.Constants.VIDEO
import com.omdeep.jetpackcompose.utils.Constants.YOGA
import com.omdeep.jetpackcompose.utils.Constants.about
import com.omdeep.jetpackcompose.utils.Constants.home
import com.omdeep.jetpackcompose.utils.Constants.profile
import com.omdeep.jetpackcompose.utils.Constants.video
import com.omdeep.jetpackcompose.utils.Constants.yoga

sealed class NavDrawerItem(var route: String, var icon: Int, var title: String) {
    object Home : NavDrawerItem(home, R.drawable.home, HOME)
    object Profile : NavDrawerItem(profile, R.drawable.user_profile, PROFILE)
    object Video : NavDrawerItem(video, R.drawable.videos, VIDEO)
    object Yoga : NavDrawerItem(yoga, R.drawable.yoga, YOGA)
    object About : NavDrawerItem(about, R.drawable.about, ABOUT)
}
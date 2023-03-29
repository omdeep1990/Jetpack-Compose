package com.omdeep.jetpackcompose.ui.screens.navBtmTabs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.omdeep.jetpackcompose.R
import com.omdeep.jetpackcompose.ui.screens.DotsIndicator
import com.omdeep.jetpackcompose.ui.screens.SliderView
import com.omdeep.jetpackcompose.ui.viewModel.ApiViewModel
import com.omdeep.jetpackcompose.utils.Constants.IMAGE_BASE_URL
import kotlinx.coroutines.delay

@OptIn(ExperimentalPagerApi::class)
@Composable
fun VideoScreen(
    viewModel : ApiViewModel = viewModel(),
    state: PagerState = rememberPagerState()
) {
    val moviesList = viewModel.popularMoviesResponse.observeAsState(initial = listOf())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Movies View",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 25.sp
        )

        SliderView(state)
        Spacer(modifier = Modifier.padding(4.dp))
        DotsIndicator(
            totalDots = moviesList.value.size,
            selectedIndex = state.currentPage
        )

        LazyColumn {
            items(moviesList.value) {
                val imagePath = if (it.backdrop_path != null) IMAGE_BASE_URL+it.backdrop_path else
                    "https://www.bing.com/images/search?view=detailV2&ccid=6ehSVERd&id=1729A90A0630E91B4044BFCFEFAA478FDB27F92A&thid=OIP.6ehSVERdcHghLuJ5F3LHNgHaHa&mediaurl=https%3A%2F%2Fis2-ssl.mzstatic.com%2Fimage%2Fthumb%2FPurple118%2Fv4%2F4d%2Fc6%2Ffc%2F4dc6fc25-d156-9fad-7a6d-51603731e6db%2Fsource%2F512x512bb.jpg&exph=512&expw=512&q=tmdb+image&simid=608013833104277188&form=IRPRST&ck=CB959986C82574636E889064B42A68D0&selectedindex=1&ajaxhist=0&ajaxserp=0&pivotparams=insightsToken%3Dccid_hrKqzabF*cp_A08861D4F08DD227437698757596DB5A*mid_3A76EDFFCF2FA70040817F2DD561ED3F0D6C1665*simid_608012931157868200*thid_OIP.hrKqzabFXR8uDHj727!_N3AHaFj&vt=0&sim=11&iss=VSI&ajaxhist=0&ajaxserp=0"
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                        val painter = rememberAsyncImagePainter(
                            ImageRequest.Builder(LocalContext.current).data(data = imagePath)
                                .apply(block = fun ImageRequest.Builder.() {
                                    placeholder(R.drawable.about)
                                    scale(Scale.FILL)
                                }).build()
                        )
                        Image(
                            painter = painter, contentDescription = "",
                            Modifier
                                .padding(8.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .fillMaxSize(), contentScale = ContentScale.Crop
                        )

                }
            }
        }
//
        LaunchedEffect(key1 = state.currentPage) {
            delay(3000)
            var newPosition = state.currentPage + 1
            if (newPosition > moviesList.value.size - 1) newPosition = 0
            // scrolling to the new position.
            state.animateScrollToPage(newPosition)
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Preview(showBackground = true)
@Composable
fun MoviesScreenPreview() {
    VideoScreen()
}




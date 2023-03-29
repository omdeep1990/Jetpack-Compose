package com.omdeep.jetpackcompose.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.omdeep.jetpackcompose.R
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.omdeep.jetpackcompose.ui.viewModel.ApiViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import com.google.accompanist.pager.HorizontalPager
import com.omdeep.jetpackcompose.utils.Constants.IMAGE_BASE_URL
import org.w3c.dom.Text

//Dots design:
@Composable
fun DotsIndicator(
    totalDots: Int,
    selectedIndex: Int
) {

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(), horizontalArrangement = Arrangement.Center
    ) {

        items(totalDots) { index ->
            if (index == selectedIndex) {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(color = Color.DarkGray)
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(color = Color.LightGray)
                )
            }

            if (index != totalDots - 1) {
                Spacer(modifier = Modifier.padding(horizontal = 2.dp))
            }
        }
    }
}

//Slider view design:
@OptIn(ExperimentalPagerApi::class)
@Composable
fun SliderView(
    state: PagerState,
    viewModel: ApiViewModel = viewModel()
) {
    val list = viewModel.popularMoviesResponse.observeAsState(initial = listOf())

    val imageUrl =
        remember { mutableStateOf("") }
    HorizontalPager(
        state = state,
        count = list.value.size, modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
    ) { page ->
        imageUrl.value =
            if (list.value[page].backdrop_path != null) {
                IMAGE_BASE_URL+list.value[page].backdrop_path
            } else {
                "https://www.bing.com/images/search?view=detailV2&ccid=hrKqzabF&id=3A76EDFFCF2FA70040817F2DD561ED3F0D6C1665&thid=OIP.hrKqzabFXR8uDHj727_N3AHaFj&mediaurl=https%3a%2f%2fcodecity.gr%2fwp-content%2fuploads%2f2019%2f10%2fTMDb.jpg&exph=480&expw=640&q=tmdb+image&simid=608012931157868200&FORM=IRPRST&ck=A08861D4F08DD227437698757596DB5A&selectedIndex=2&ajaxhist=0&ajaxserp=0"
            }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(contentAlignment = Alignment.BottomCenter) {

                val painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current).data(data = imageUrl.value)
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
}
package com.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.common.navigation.NavGraphRouts
import com.common.navigation.NavigationModel
import com.common.navigation.ThriveNavigationDestination
import com.common.theme.MoviesDatabaseTheme
import com.common.utils.CommonEvents
import com.common.utils.Utils
import com.common.utils.Utils.LocalAppLocale
import com.common.utils.shimmerLoadingAnimation
import org.koin.androidx.compose.koinViewModel


@Composable
fun HomeScreen(onNavigateToDestination: (ThriveNavigationDestination?) -> Unit) {
//    val localeState = LocalAppLocale.current
    val homeViewModel: SearchMoviesViewModel = koinViewModel()
    val state by homeViewModel.uiState.collectAsStateWithLifecycle()

    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier
            .fillMaxSize()
    ) {
        when  {
            state.loading -> {
                SkeletonLoad(loadingComplete = false)
            }

            state.media.isNotEmpty() -> {
            }

            state.defaultState -> {
            }

            state.emptyView && !state.loading -> {
            }
        }

    }

//    Surface(
//        color = MaterialTheme.colorScheme.background,
//        modifier = Modifier.fillMaxSize()
//    ) {
//        Column(modifier = Modifier.fillMaxWidth()) {
//            SkeletonLoad()
//            Text(
//                text = stringResource(R.string.home_screen),
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .clickable {
////                        Utils.changeLanguage(localeState)
////            onNavigateToDestination.invoke(
////                NavigationModel(
////                    route = NavGraphRouts.Detail(
////                        email = ""
////                    ),
////                    route = CommonEvents.BACK_PRESS,
////                    startDestination = null, trackScreenStack = true,
////                ),
////            )
//                    })
//        }
//
//    }
}

@Composable
fun SkeletonLoad(loadingComplete: Boolean = false) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .padding(start = 10.dp, top = 20.dp, bottom = 20.dp)
                .background(color = Color.LightGray)
                .width(100.dp)
                .height(15.dp)
                .shimmerLoadingAnimation(
                    isLoadingCompleted = loadingComplete,
                    isLightModeActive = true,
                )
        )
        SkeletonRow()
        Box(
            modifier = Modifier
                .padding(start = 10.dp, top = 20.dp, bottom = 20.dp)
                .background(color = Color.LightGray)
                .width(100.dp)
                .height(15.dp)
                .shimmerLoadingAnimation(
                    isLoadingCompleted = loadingComplete,
                    isLightModeActive = true,
                )
        )
        SkeletonRow()
        Box(
            modifier = Modifier
                .padding(start = 10.dp, top = 20.dp, bottom = 20.dp)
                .background(color = Color.LightGray)
                .width(100.dp)
                .height(15.dp)
                .shimmerLoadingAnimation(
                    isLoadingCompleted = loadingComplete,
                    isLightModeActive = true,
                )
        )
        SkeletonRow()
    }
}
@Composable
fun SkeletonRow(loadingComplete: Boolean = false){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            verticalAlignment = Alignment.Top
        ) {
            repeat(5) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .background(color = Color.LightGray)
                        .width(150.dp)
                        .height(100.dp)
                        .shimmerLoadingAnimation(
                            isLoadingCompleted = loadingComplete,
                            isLightModeActive = true,
                        )
                )
                Spacer(modifier = Modifier.width(10.dp))
            }

        }
}

@Preview(showBackground = true, showSystemUi = true)
//@Preview(name = "Phone", device = Devices.PHONE)
//@Preview(name = "Tablet", device = Devices.TABLET)
//@Preview(name = "Foldable", device = Devices.FOLDABLE)
@Composable
fun HomeScreenPreview() {
//    val koinApp = startKoin {
//        modules(modules = appModule) // Load necessary Koin modules
//    }
    MoviesDatabaseTheme {
        HomeScreen(
            onNavigateToDestination = {}
        )
    }
}

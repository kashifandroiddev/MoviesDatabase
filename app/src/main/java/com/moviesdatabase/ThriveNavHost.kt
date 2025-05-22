package com.moviesdatabase

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.common.navigation.NavGraphRouts
import com.common.navigation.ThriveNavigationDestination
import com.common.utils.CommonEvents
import com.presentation.DetailScreen
import com.presentation.HomeScreen
import kotlin.reflect.KClass

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ThriveNavHost(
    navController: NavHostController,
    onNavigateToDestination: (ThriveNavigationDestination?) -> Unit,
    popBack: () -> Unit,
    modifier: Modifier = Modifier,
    startDestination: KClass<*>
) {
//    val context = LocalContext.current
//    val activity = context.findActivity()
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
        enterTransition = { EnterTransition.Companion.None },
        exitTransition = { ExitTransition.Companion.None },
        popEnterTransition = { EnterTransition.Companion.None },
        popExitTransition = { ExitTransition.Companion.None },
    ) {
        composable<NavGraphRouts.Home> {
            HomeScreen{item->
                when(item?.route){
                    CommonEvents.BACK_PRESS->{
                        popBack.invoke()
                    }
                    else->{
                        onNavigateToDestination.invoke(item)
                    }
                }
            }
        }
        composable<NavGraphRouts.Detail> {
            DetailScreen{item->
                when(item?.route){
                    CommonEvents.BACK_PRESS->{
                        popBack.invoke()
                    }
                    else->{
                        onNavigateToDestination.invoke(item)
                    }
                }
            }
            }
        }

//        authNavGraph(
//            onLoginStateChange,
//            authNavigation = { itemModel ->
//                when (com.composeapp.common.navigation.models.NavigationAuthModel.route) {
//                    com.composeapp.common.utils.CommonEvents.BACK_PRESS -> {
//                        Function0.invoke()
//                    }
//
//                    else -> {
//                        Function2.invoke(itemModel, "")
//                    }
//                }
//            },
//        )
//
//        homeNavGraph(
////            handleDeepLinks = { deeplink ->
////                activity?.intent = Intent(Intent.ACTION_VIEW, Uri.parse(deeplink))
////                handleDeepLinks(MerchantDestination, MerchantDestination.specificOffers, deeplink)
////            },
////            navigateToAuth = {
////                onNavigateToDestination(null,Graph.AUTHENTICATION)
////            },
////            navigateToDetail = {
////                onNavigateToDestination(null,Graph.DETAILS)
////            },
////            navigateToDetailAbout = {
////                onNavigateToDestination(null, DetailScreensRout.DetailAbout.route)
////            },
//            homeNavigation = { itemModel ->
//                when (com.composeapp.common.navigation.models.NavigationHomeModel.route) {
//                    com.composeapp.common.utils.CommonEvents.BACK_PRESS -> {
//                        Function0.invoke()
//                    }
//
//                    else -> {
//                        Function2.invoke(itemModel, "")
//                    }
//                }
//
//            },
//            navigateToOffers = {
//                onNavigateToDestination(MerchantDestination, MerchantDestination.route)
//            },
//            isApiLoading = { loading: Boolean ->
//                isApiLoading(loading)
//            },
//            nestedGraphs = {
//
//                searchGraph(navigateToDetail = {
//                    onNavigateToDestination(MerchantDestination, MerchantDestination.detail)
//                })
//
//            }
//        )


}

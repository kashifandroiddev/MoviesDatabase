package com.moviesdatabase

import androidx.activity.OnBackPressedDispatcher
import androidx.core.os.trace
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.common.navigation.ThriveNavigationDestination

//@kotlin.OptIn(ExperimentalAnimationApi::class)
@androidx.compose.runtime.Composable
fun rememberAdroAppState(
    navController: NavHostController = rememberNavController(),
    onBackPressedDispatcher: OnBackPressedDispatcher
): ThriveAppState {
    return ThriveAppState(
        navController = navController,
        onBackPressedDispatcher = onBackPressedDispatcher
    )
}

@androidx.compose.runtime.Stable
class ThriveAppState(
    val navController: NavHostController,
    val onBackPressedDispatcher: OnBackPressedDispatcher
) {

    fun navigate(
        destination: ThriveNavigationDestination? = null
    ) {
        trace("Navigation: $destination") {
            when (destination?.trackScreenStack) {
                true -> {
                    if (destination.clearStack == true) {
                        navController.navigate(destination.route ?: return@trace) {
                            popUpTo(0) {
                                inclusive = true
                            }
                        }
                    } else {
                        navController.navigate(destination.route ?: return@trace)
                    }
                }

                false -> {
                    navController.navigate(destination.route ?: return@trace) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                }

                null -> {

                }
            }
        }
    }

    fun popBack() {
        if (navController.previousBackStackEntry != null) {
            navController.popBackStack()
        } else {
            onBackPressedDispatcher.onBackPressed()
        }
    }

}


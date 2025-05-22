package com.moviesdatabase

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.common.navigation.NavGraphRouts
import com.common.theme.MoviesDatabaseTheme
import com.common.utils.Utils.LocalAppLocale
import com.common.utils.Utils.MyLocalizedApp
import java.util.Locale

@Composable
fun ThriveApp(appState: ThriveAppState) {
    val localeState = rememberSaveable { mutableStateOf(Locale("en")) }
    CompositionLocalProvider(LocalAppLocale provides localeState) {
        MyLocalizedApp(localeState = localeState) {
            MoviesDatabaseTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    content = { padding ->
                        Box(
                            modifier = Modifier
                                .padding(padding)
                                .fillMaxHeight()
                        ) {
                            ThriveNavHost(
                                navController = appState.navController,
                                popBack = appState::popBack,
                                onNavigateToDestination = appState::navigate,
                                startDestination = NavGraphRouts.Home::class,
                            )

                        }
                    },
                )

            }
        }
    }
}

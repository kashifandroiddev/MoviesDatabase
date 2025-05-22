package com.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.common.navigation.ThriveNavigationDestination


@Composable
fun DetailScreen(onNavigateToDestination: (ThriveNavigationDestination?) -> Unit) {
    Column(){
        Text("I am detail screen")
    }

}
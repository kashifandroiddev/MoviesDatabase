package com.common.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class NavGraphRouts() {
    @Serializable
    data object Home : NavGraphRouts()
    @Serializable
    data class Detail(val email: String?) : NavGraphRouts()
    @Serializable
    data class Video(val email: String?) : NavGraphRouts()
}
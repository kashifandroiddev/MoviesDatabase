package com.common.navigation


data class NavigationModel(
    override val route: Any?,
    override val startDestination: Any?,
    override val trackScreenStack: Boolean? = false,
    override val clearStack: Boolean? = false
) : ThriveNavigationDestination
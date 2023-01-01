package com.avtelma.tagaevplanner.navigation

sealed class Screen(val route: String) {
    object MainSphereScreen: Screen("sph")
    object ProgresserScreen: Screen("prg")
    object CopyPasterScreen: Screen("cpr")
}

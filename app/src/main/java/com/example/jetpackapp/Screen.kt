package com.example.jetpackapp

sealed class Screen(val route: String) {
    object MainScreen : Screen("Main_Screen")
    object DetailScreen : Screen("Detail_Screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach {
                arg -> append("/$arg")
            }
        }
    }
}

package com.example.jetpackapp.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

@Composable
fun SetUpNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(
            route = Screen.Home.route,
            arguments = listOf(
                navArgument(DETAIL_ARGUMENT_KEY) {
                    type = NavType.IntType
                    defaultValue = 0
                },
                navArgument(DETAIL_ARGUMENT_NAME) {
                    type = NavType.StringType
                    defaultValue = "Default"
                })
        ) {
            HomeScreen(navController = navController)
        }
        composable(route = Screen.Detail.route) {
            Log.e("NavArgs", it.arguments?.getInt(DETAIL_ARGUMENT_KEY).toString())
            Log.e("NavArgs", it.arguments?.getString(DETAIL_ARGUMENT_NAME).toString())
            DetailScreen(navController = navController)
        }
    }
}
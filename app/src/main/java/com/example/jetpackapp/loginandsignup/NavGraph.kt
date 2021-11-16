package com.example.jetpackapp.loginandsignup

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun SetUpNavGraph(
    navController: NavHostController,
    dataStorePref: DataStorePref,
    isLogin: Boolean
) {
    Log.e("TAG", "SetUpNavGraph: ")
    var start = Screen.SignIn.route
    if (isLogin) start = Screen.Dashboard.route
    NavHost(navController = navController, startDestination = start) {
        composable(route = Screen.Dashboard.route) {
            DashboardScreen(navController = navController)
        }
        composable(route = Screen.SignIn.route) {
            SignInScreen(navController = navController, dataStorePref)
        }
        composable(route = Screen.SignUp.route) {
            SignUpPage(navController = navController, dataStorePref)
        }

    }
}
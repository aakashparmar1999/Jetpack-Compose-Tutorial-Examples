package com.example.jetpackapp.loginandsignup

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.jetpackapp.loginandsignup.ui.theme.JetpackAppTheme


class Activity : ComponentActivity() {
    lateinit var navController: NavHostController
    lateinit var dataStorePref: DataStorePref

    companion object {
        private const val TAG = "Activity"
    }

    init {
        Log.e(TAG, "init block call: ")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackAppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    dataStorePref = DataStorePref(this)
                    val isLogin = dataStorePref.getStatus.collectAsState(initial = false)
                    navController = rememberNavController()
                    Log.e("TAG", "onCreate: ")
                    SetUpNavGraph(navController = navController, dataStorePref, isLogin.value)
                }
            }
        }
    }
}
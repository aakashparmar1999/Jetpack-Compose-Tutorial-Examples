package com.example.jetpackapp.loginandsignup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.jetpackapp.loginandsignup.ui.theme.JetpackAppTheme

class Dashboard : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    DashboardScreen(rememberNavController())
                }
            }
        }
    }
}

@Composable
fun DashboardScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize())
    Text(text = "Hello!")
}

@Preview(showBackground = true)
@Composable
fun DashboardPreview() {
    JetpackAppTheme {
        DashboardScreen(rememberNavController())
    }
}
package com.example.jetpackapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackapp.ui.theme.JetpackAppTheme

class Compose11 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var sizeState by remember {
                mutableStateOf(200.dp)
            }
            val size by animateDpAsState(
                targetValue = sizeState,
                spring(
                    Spring.DampingRatioHighBouncy
                )
                /*tween(durationMillis = 3000,
                delayMillis = 300,
                easing = LinearOutSlowInEasing)*/

            )
            JetpackAppTheme {
                Box(
                    modifier = Modifier
                        .size(size)
                        .background(Color.Red),
                    contentAlignment = Alignment.Center
                ) {
                    Button(onClick = {
                        sizeState += 50.dp

                    }) {
                        Text("Increase Size")
                    }
                }

            }
        }
    }
}

@Composable
fun Greeting2() {

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    JetpackAppTheme {
        Greeting2()
    }
}
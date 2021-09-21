package com.example.jetpackapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.produceState
import androidx.compose.runtime.rememberCoroutineScope
import com.example.jetpackapp.ui.theme.JetpackAppTheme
import kotlinx.coroutines.delay

class Compose10 : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackAppTheme {
                val scaffoldState = rememberScaffoldState()
                //val scope = rememberCoroutineScope()
                Scaffold(scaffoldState = scaffoldState) {
                    val couter = produceState(initialValue = 0){
                        delay(3000L)
                        value = 4

                    }
                    /*var couter by remember {
                        mutableStateOf(0)
                    }*/
                    /*  if (couter % 5 == 0 && couter > 0) {
                          scope.launch {
                              scaffoldState.snackbarHostState.showSnackbar("hello")
                          }
                      }*/
                    if (couter.value % 5 == 0 && couter.value > 0) {
                        LaunchedEffect(key1 = scaffoldState.snackbarHostState) {
                            scaffoldState.snackbarHostState.showSnackbar("hello")
                        }
                    }
                    Button(onClick = {  }) {
                        Text("Click me ${couter.value}")
                    }
                }

            }
        }
    }
}

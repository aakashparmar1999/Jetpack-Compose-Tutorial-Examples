package com.example.jetpackapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.jetpackapp.ui.theme.JetpackAppTheme
import kotlinx.coroutines.launch

class Compose7 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackAppTheme {
                val scaffoldState = rememberScaffoldState()
                var textFieldState by remember {
                    mutableStateOf("")
                }
                val scope = rememberCoroutineScope()
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {

                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        scaffoldState = scaffoldState
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 30.dp)
                        ) {
                            TextField(
                                value = textFieldState, label = {
                                    Text("Enter your name")
                                },
                                onValueChange = {
                                    textFieldState = it
                                },
                                singleLine = true,
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Button(onClick = {
                                scope.launch {
                                    scaffoldState.snackbarHostState.showSnackbar("Hello $textFieldState")
                                }

                            }) {
                                Text(text = "Pls greet me")
                            }
                        }
                    }
                }
            }
        }
    }
}

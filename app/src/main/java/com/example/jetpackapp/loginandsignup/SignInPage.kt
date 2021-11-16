package com.example.jetpackapp.loginandsignup

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.edit
import androidx.navigation.NavController
import com.example.jetpackapp.R
import com.example.jetpackapp.loginandsignup.DataStorePref.Companion.IsLogin
import com.example.jetpackapp.loginandsignup.DataStorePref.Companion.Password
import com.example.jetpackapp.loginandsignup.DataStorePref.Companion.Username
import com.example.jetpackapp.ui.theme.JetpackAppTheme
import com.example.jetpackapp.ui.theme.Teal200
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SignInPage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackAppTheme {
                Log.e("TAG", "onCreate: signinpage" )
//                SignInScreen(rememberNavController())
            }
        }
    }
}

fun Context.toast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()


@Composable
fun SignInScreen(navController: NavController, dataStorePref: DataStorePref) {
    Log.e("TAG", "SignInScreen: ", )

    val emailState = remember {
        EmailState()
    }

    val passwordState = remember {
        PasswordState()
    }

    var username by remember {
        mutableStateOf("")
    }
    val password by remember {
        mutableStateOf("")
    }
    var isPasswordVisible by remember {
        mutableStateOf(false)
    }
    val isFormValid by derivedStateOf {
        emailState.isValid() && passwordState.isValid()
    }
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(scaffoldState = scaffoldState, backgroundColor = Teal200) {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_3),
                contentDescription = "App Logo",
                modifier = Modifier
                    .weight(1f)
                    .size(200.dp, 100.dp),
            )
            Card(
                Modifier
                    .weight(2f)
                    .padding(10.dp),
                shape = RoundedCornerShape(32.dp)
            ) {
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(32.dp)
                ) {
                    Text(text = "Sign In", fontWeight = FontWeight.Bold, fontSize = 32.sp)
                    Column(
                        Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Spacer(modifier = Modifier.weight(1f))
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = emailState.text,
                            onValueChange = {
                                emailState.text = it
                                emailState.validate()
                            },
                            label = { Text(text = "Username") },
                            singleLine = true,
                            isError = emailState.error != null,
                            trailingIcon = {
                                if (username.isNotBlank())
                                    IconButton(onClick = { username = "" }) {
                                        Icon(
                                            imageVector = Icons.Filled.Clear,
                                            contentDescription = ""
                                        )
                                    }
                            }

                        )
                        emailState.error?.let {
                            ErrorField(it)
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = passwordState.text,
                            onValueChange = {
                                passwordState.text = it
                                passwordState.validate()
                            },
                            label = { Text(text = "Password") },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Password,
                                imeAction = ImeAction.Done
                            ),
                            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                            trailingIcon = {
                                IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                                    Icon(
                                        imageVector = if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                        contentDescription = "Password Toggle"
                                    )
                                }
                            },
                            isError = passwordState.error != null
                        )
                        passwordState.error?.let {
                            ErrorField(error = it)
                        }
                        Spacer(modifier = Modifier.height(22.dp))
                        val context = LocalContext.current
                        Button(
                            onClick = {
                                CoroutineScope(Dispatchers.Main).launch {
                                    dataStorePref.dataStore.data.collect { it ->
                                        Log.e("TAG", "SignInScreen: $it[Name] ")
                                        Log.e("TAG", "SignInScreen: $it[Username]")
                                        Log.e("TAG", "SignInScreen: $it[Password]")
                                        if (emailState.text == it[Username]!! && passwordState.text == it[Password]) {

                                            CoroutineScope(Dispatchers.IO).launch {
                                                dataStorePref.dataStore.edit {
                                                    it[IsLogin] = true
                                                }
                                            }
                                            Toast.makeText(
                                                context,
                                                "Login Sucess",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                            Log.e("TAG", "SignInScreen: login success now getlost")
                                            navController.navigate(route = Screen.Dashboard.route)
                                        } else {
                                            Toast.makeText(
                                                context,
                                                "Username and password are incorrect",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                            Log.e(
                                                "TAG",
                                                "SignInScreen: login failed you also get lost",
                                            )
                                        }
                                    }
                                }
                                Log.e("TAG", "SignInScreen: ")

                            },
                            enabled = isFormValid,
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(30.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Teal200)
                        ) {
                            Text(
                                modifier = Modifier.padding(4.dp),
                                text = "Log In"
                            )
                        }
                        TextButton(onClick = {
                            navController.navigate(route = Screen.SignUp.route)
                        }) {
                            Text(text = "Sign Up")
                        }
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackAppTheme {
//        SignInScreen(rememberNavController())
    }
}
package com.example.jetpackapp.loginandsignup

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.datastore.DataStore
import androidx.datastore.preferences.*
import androidx.navigation.NavController
import com.example.jetpackapp.R
import com.example.jetpackapp.ui.theme.JetpackAppTheme
import com.example.jetpackapp.ui.theme.Teal200
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@InternalCoroutinesApi
class SignupPage : ComponentActivity() {

    lateinit var preferenceDataStore: DataStore<Preferences>

    private suspend fun updateProfile(
        username: String,
        location: String,
        age: Int,
        status: Boolean
    ) {
        preferenceDataStore.edit { profile ->
            profile[PreferencesKeys.Username] = username
            profile[PreferencesKeys.Location] = location
            profile[PreferencesKeys.Age] = age
            profile[PreferencesKeys.IsAccountActive] = status
        }
    }

    object PreferencesKeys {
        const val PrefName = "profile"
        val Username = preferencesKey<String>("username")
        val Location = preferencesKey<String>("location")
        val Age = preferencesKey<Int>("age")
        val IsAccountActive = preferencesKey<Boolean>("status")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackAppTheme {
                //SignUpPage(rememberNavController())
                preferenceDataStore = createDataStore(
                    name = "profile",
                    migrations = listOf(SharedPreferencesMigration(this, "profile"))
                )
                Column(modifier = Modifier.padding(15.dp)) {
                    Button(onClick = {
                        CoroutineScope(Dispatchers.IO).launch {
                            updateProfile(
                                username = "Shah",
                                location = "A",
                                age = 22,
                                status = true
                            )
                            Log.d("TAG", "onCreate: " + "success")
                        }
                    }) {
                        Text(text = "Update DataStore")
                    }
                    Button(onClick = {
                        CoroutineScope(Dispatchers.IO).launch {

                            preferenceDataStore.data.collect { profile ->
                                Log.d("TAG", "onCreate $profile[PreferencesKeys.Username]")
                                Log.d("TAG", "onCreate: " + profile[PreferencesKeys.Location])
                                Log.d("TAG", "onCreate: " + profile[PreferencesKeys.Age])
                                Log.d(
                                    "TAG",
                                    "onCreate: " + profile[PreferencesKeys.IsAccountActive]
                                )
                            }
                        }
                    }) {
                        Text(text = "Get Data")
                    }
                }

            }
        }
    }
}

@Composable
fun SignUpPage(navController: NavController,dataStorePref: DataStorePref) {
    Log.e("TAG", "SignUpPage: ", )
    val emailState = remember {
        EmailState()
    }

    val passwordState = remember {
        PasswordState()
    }

    var username by remember {
        mutableStateOf("")
    }
    var name by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var isPasswordVisible by remember {
        mutableStateOf(false)
    }
    val isFormValid by derivedStateOf {
        emailState.isValid() && passwordState.isValid() && name.isNotEmpty()
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
                    Text(text = "Sign Up", fontWeight = FontWeight.Bold, fontSize = 32.sp)
                    Column(
                        Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Spacer(modifier = Modifier.weight(1f))
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = name,
                            onValueChange = { name = it },
                            label = { Text(text = "Name") },
                            singleLine = true,
                            trailingIcon = {
                                if (name.isNotBlank())
                                    IconButton(onClick = { name = "" }) {
                                        Icon(
                                            imageVector = Icons.Filled.Clear,
                                            contentDescription = ""
                                        )
                                    }
                            }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Column {
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
                                    dataStorePref.insertData(name,emailState.text,passwordState.text)
                                    navController.navigate(Screen.SignIn.route) {
                                        popUpTo(Screen.SignIn.route) {
                                            inclusive = true
                                        }
                                    }
                                }
                                Toast.makeText(context, "Account Created", Toast.LENGTH_SHORT).show()
                                Log.e("TAG", "SignUpPage: success" )
                            },
                            enabled = isFormValid,
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(30.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Teal200)
                        ) {
                            Text(
                                modifier = Modifier.padding(4.dp),
                                text = "Sign Up"
                            )
                        }
                        TextButton(onClick = {
                           navController.navigate(Screen.SignIn.route) {
                                popUpTo(Screen.SignIn.route) {
                                    inclusive = true
                                }
                            }
                        }) {
                            Text(text = "Already have an account? Sign In", color = Color.Magenta)
                        }
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

@Composable
fun ErrorField(error: String) {
    Text(
        text = error,
        modifier = Modifier.fillMaxWidth(),
        style = TextStyle(color = Color.Red)
    )
}

@Preview(showBackground = true)
@Composable
fun SignUpPagePreview() {
    JetpackAppTheme {
        //SignUpPage(rememberNavController())
    }
}
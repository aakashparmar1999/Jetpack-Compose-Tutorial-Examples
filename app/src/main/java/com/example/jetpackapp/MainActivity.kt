package com.example.jetpackapp

//import androidx.compose.runtime.remember

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackapp.ui.theme.JetpackAppTheme
import kotlin.random.Random

//import androidx.navigation.NavType
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.navArgument
//import androidx.navigation.compose.rememberNavController
//import com.example.jetpackapp.ui.theme.JetpackAppTheme
//import dagger.hilt.android.AndroidEntryPoint

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val fontFamily = FontFamily(
            Font(R.font.lexend_thin, FontWeight.Thin),
            Font(R.font.lexend_black, FontWeight.Black),
            Font(R.font.lexend_bold, FontWeight.Bold),
            Font(R.font.lexend_extrabold, FontWeight.ExtraBold),
            Font(R.font.lexend_extralight, FontWeight.ExtraLight),
            Font(R.font.lexend_light, FontWeight.Light),
            Font(R.font.lexend_medium, FontWeight.Medium),
            Font(R.font.lexend_regular, FontWeight.Normal),
            Font(R.font.lexend_semibold, FontWeight.SemiBold),


            )

        setContent {

            JetpackAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = Color.White) {
                    Column {
                        // call the function which contains all the buttons
                        GradientButton()
                    }
                }

                ColorBox()
                val color = remember {
                    mutableStateOf(Color.Black)
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()

                        .background(color.value)
                        .padding(start = 50.dp)
                        .clickable {
                            color.value = Color(
                                Random.nextFloat(),
                                Random.nextFloat(),
                                Random.nextFloat(),
                                1f
                            )
                        }
                )
                {
                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = Color.Green,
                                    fontSize = 40.sp
                                )
                            ) {
                                append("J")
                            }
                            append("etpack ")
                            withStyle(
                                style = SpanStyle(
                                    color = Color.Green,
                                    fontSize = 40.sp
                                )
                            ) {
                                append("C")
                            }
                            append("ompos")
                        },
                        color = Color.White,
                        fontSize = 30.sp,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )

                }
                ColorBox()

                val painter = painterResource(id = R.drawable.img)
                val description = "Beautiful Nature"
                val title = "Beautiful Nature"
                Row(
                    Modifier
                        .padding(top = 80.dp)
                ) {


                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .padding(16.dp),
                        contentAlignment = Alignment.BottomStart
                    ) {
                        ImageCard(
                            painter = painter,
                            contentDescription = description,
                            title = title
                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.BottomStart
                    ) {
                        ImageCard(
                            painter = painterResource(id = R.drawable.img_1),
                            contentDescription = description,
                            title = title
                        )
                    }
                }


                //Styling Text

/*
            JetpackAppTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "pokemon_list_screen") {
                    composable("pokemon_list_screen") {

                    }
                    composable(
                        "pokemon_detail_screen/{dominantColor}/{pokemonName}",
                        arguments = listOf(navArgument("dominantColor") {
                            type = NavType.IntType
                        },
                            navArgument("pokemonName") {

                                type = NavType.StringType
                            }
                        )
                    ) {
                        val dominantColor = remember {
                            val color = it.arguments?.getInt("dominantColor")
                            color?.let { Color(it) } ?: Color.White
                        }
                        val pokemonName = remember {
                            it.arguments?.getString("pokemonName")
                        }
                    }
                }
            } */
            }
        }
    }
}

@Composable
fun ImageCard(
    painter: Painter,
    contentDescription: String,
    title: String,
    modifier: Modifier = Modifier
) {
    Card(

        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        elevation = 10.dp
    ) {
        Box(modifier = Modifier.height(200.dp)) {
            Image(
                painter = painter,
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop,
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ), startY = 300f

                        )
                    )
            )



            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Text(title, style = TextStyle(color = Color.White, fontSize = 16.sp))
            }

        }

    }
}

@Composable
fun ColorBox() {
    val color = remember {
        mutableStateOf(Color.Yellow)
    }

    Box(
        Modifier
            .background(color.value)
            .clickable {
                color.value = Color(
                    Random.nextFloat(),
                    Random.nextFloat(),
                    Random.nextFloat(),
                    1f
                )
            }
    )
}

@Composable
fun GradientButton() {
    Row {
        val horizontalGradient = Brush.horizontalGradient(
            colors = listOf(MaterialTheme.colors.error, MaterialTheme.colors.primaryVariant),
            0f,
            250f
        )
        Text(
            text = "Horizontal gradient",
            style = typography.body2.copy(color = Color.White),
            modifier = Modifier
                .padding(12.dp)
                .clickable(onClick = {})
                .clip(RoundedCornerShape(4.dp))
                .background(brush = horizontalGradient)
                .padding(12.dp)
        )

        val verticalGradient = Brush.verticalGradient(
            colors = listOf(MaterialTheme.colors.error, MaterialTheme.colors.primaryVariant),
            startY = 0f,
            endY = 100f
        )
        Text(
            text = "Vertical gradient",
            style = typography.body1.copy(color = Color.White),
            modifier = Modifier
                .padding(12.dp)
                .clickable(onClick = {})
                .clip(RoundedCornerShape(4.dp))
                .background(brush = verticalGradient)
                .padding(12.dp)
        )
    }
    Row(modifier = Modifier.padding(top = 100.dp).padding(20.dp)) {
        Button(
            onClick = {},

            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(Brush.verticalGradient(listOf(Color.White, Color.Blue))),
        ) {
            Text(text = "Button", color = Color.White)
        }
    }

}

@Composable
fun MyScreenContentDemo(names: List<String> = listOf("Android", "there")) {
    val CounterDemoState = remember { mutableStateOf(0) }

    Column {
        for (name in names) {
            GreetingDemo(name = name)
            Divider(color = Color.Black)
        }
        Divider(color = Color.Transparent, thickness = 32.dp)
        CounterDemo(
            count = CounterDemoState.value,
            updateCount = { newCount ->
                CounterDemoState.value = newCount
            }
        )
    }
}

@Composable
fun CounterDemo(count: Int, updateCount: (Int) -> Unit) {
    Button(onClick = { updateCount(count+1) }) {
        Text("I've been clicked $count times")
    }
}
@Composable
fun GreetingDemo(name: String) {
    Text(name)
}
@Preview(showSystemUi = true)
@Composable
fun DefaultPreviewMain() {
    JetpackAppTheme {
        GradientButton()
    }
}
@Preview(showSystemUi = true)
@Composable
fun DefaultPreviewMainImageCardView() {
    JetpackAppTheme {
        val description = "Beautiful Nature"
        val title = "Beautiful Nature"
        Box(
            modifier = Modifier
                .width(200.dp)
                .padding(16.dp),
            contentAlignment = Alignment.BottomStart
        ) {
            ImageCard(
                painter = painterResource(id = R.drawable.img_1),
                contentDescription = description,
                title = title
            )
        }
    }
}

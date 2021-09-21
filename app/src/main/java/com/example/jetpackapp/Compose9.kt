package com.example.jetpackapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.example.jetpackapp.ui.theme.JetpackAppTheme

class Compose9 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackAppTheme {
                // A surface container using the 'background' color from the theme

            }
        }
    }
}

@Composable
fun ConstraintExample() {

    val constraints = ConstraintSet {
        val greenbox = createRefFor("greenbox")
        val redbox = createRefFor("redbox")
        val guideline = createGuidelineFromTop(0.5f)
        constrain(greenbox) {

            top.linkTo(parent.top)
            start.linkTo(parent.start)
            bottom.linkTo(guideline)
            width = Dimension.value(100.dp)
            height = Dimension.value(100.dp)
        }
        constrain(redbox) {
            top.linkTo(parent.top)
            end.linkTo(parent.end)
            width = Dimension.value(100.dp)
            height = Dimension.value(100.dp)
        }
        createVerticalChain(greenbox, redbox, chainStyle = ChainStyle.SpreadInside)
    }
    ConstraintLayout(constraints, modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .background(Color.Green)
                .layoutId("greenbox")
        )
        Box(
            modifier = Modifier
                .background(Color.Red)
                .layoutId("redbox")
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun DefaultPreview2() {
    JetpackAppTheme {
        ConstraintExample()
    }
}
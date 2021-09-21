package com.example.jetpackapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackapp.ui.theme.JetpackAppTheme

class Compose8 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackAppTheme {
                Surface(color = MaterialTheme.colors.background) {
                }
            }
        }
    }
}

@Composable
fun LazyColumnExample() {
    LazyColumn {
        items(5000) {
            Text(
                text = "Item $it",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp)
            )
        }
    }
}

@Composable
fun LazyColumnExample2() {
    LazyColumn {
        itemsIndexed(
            listOf("this", "is", "jetpack", "compose")
        ) { index, string ->

            Text(
                text = "$string",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreviewLazyColumnExample() {
    JetpackAppTheme {
        LazyColumnExample()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreviewLazyColumnExample2() {
    JetpackAppTheme {
        LazyColumnExample2()
    }
}
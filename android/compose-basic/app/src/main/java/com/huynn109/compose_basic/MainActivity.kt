package com.huynn109.compose_basic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.huynn109.compose_basic.ui.theme.ComposebasicTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposebasicTheme {
                MyApp {
                    MyScreenContent()
                }
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit): Unit {
    ComposebasicTheme {
        Surface(color = MaterialTheme.colors.background) {
            content()
        }
    }
}

@Composable
fun MyScreenContent(names: List<String> = listOf("Android", "there")) {
    Column {
        for (name in names) {
            Greeting(name = name)
            Divider(color = Color.Red)
        }
        Divider(color = Color.Transparent, thickness = 32.dp)
        Counter()
        Counter()
    }
}

@Composable
fun Counter() {
    val count = remember {
        mutableStateOf(0)
    }
    Button(onClick = { count.value++ }) {
        Text("Clicked ${count.value}")
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!", modifier = Modifier.padding(24.dp))
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposebasicTheme {
        Greeting("Android")
    }
}
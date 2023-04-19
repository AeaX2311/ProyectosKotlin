package com.aeax.testingdev

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import com.aeax.testingdev.ui.theme.TestingDevTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestingDevTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = name)
}

@Composable
fun TextResult(text: String) {
    Text(text = text)
}

@Composable
fun OperationButtons(onClick: (Int) -> Unit) {
    Row(
        modifier = Modifier.fillMaxSize()
    ) {
        Button(modifier = Modifier.testTag("btnPlus"), onClick = { onClick(1) }) {
            Text(text = "+")
        }
        Button(modifier = Modifier.testTag("btnMinus"), onClick = { onClick(-1) }) {
            Text(text = "-")
        }
    }
}
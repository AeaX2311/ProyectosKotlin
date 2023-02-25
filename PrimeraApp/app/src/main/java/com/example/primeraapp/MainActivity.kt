package com.example.primeraapp

import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.primeraapp.ui.theme.PrimeraAppTheme
import com.example.primeraapp.figures.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var currentView by remember { mutableStateOf(0) }
            val mContext = applicationContext

            PrimeraAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Column (
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.verticalScroll(rememberScrollState())
                        ) {
                            when (currentView) {
                                0 -> Pig(mContext)
                                1 -> Skeleton(mContext)
                                2 -> Creeper(mContext)
                            }
                        }

                        Row (
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ){
                            Button(
                                onClick = { currentView -= 1 },
                                enabled = currentView > 0,
                            ){
                                Text(text = "<")
                            }

                            Button(
                                onClick = { currentView += 1 },
                                enabled = currentView < 2,
                            ){
                                Text(text = ">")
                            }
                        }
                    }
                }

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PrimeraAppTheme (darkTheme = true) {
//        Skeleton () Pig()
//            Creeper(application)
    }
}
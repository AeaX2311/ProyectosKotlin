package com.aeax.gato

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aeax.gato.components.GatoViewModel
import com.aeax.gato.ui.theme.GatoTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           GatoBoard()
        }
    }
}

//Create a 3x3 matrix composable

@Composable
fun GatoBoard(gatoViewModel: GatoViewModel = GatoViewModel()) {
    val gatoState = gatoViewModel.gato.collectAsState()
    var textoWinner by remember {  mutableStateOf("") }

    GatoTheme {
        Surface(
            modifier = Modifier.fillMaxSize(), color = Color.Black
        ) {

            LaunchedEffect(gatoState.value) {
                //check if there is a winner
                val resultado = gatoViewModel.determinarStatusJuego()
                if (resultado != "") {
                    textoWinner = resultado
                    delay(3000)
                    gatoViewModel.resetGame()
                    textoWinner = ""
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .fillMaxWidth()
                    .background(Color.Black),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Board3(gatoViewModel = gatoViewModel)
                }



                //Spacer
                Spacer(modifier = Modifier.height(50.dp))
                //Texto de a quien le toca jugar si a X o a O
                Text(
                    text = "Turno de: ${gatoState.value.player}",
                    modifier = Modifier.background(Color.White)
                )

                Spacer(modifier = Modifier.height(50.dp))

                //Winner text
                Text(text = textoWinner, modifier = Modifier.background(Color.White))

            }
        }
    }
}

@Composable
fun Board3(gatoViewModel: GatoViewModel = GatoViewModel()) {
    val gatoState = gatoViewModel.gato.collectAsState()

    Row(
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth()
            .background(Color.Black)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .border(1.dp, Color.Black)
                    .size(100.dp)
                    .background(
                        if (gatoState.value.gatoArray[0][0] == "X") {
                            Color.Red
                        } else if (gatoState.value.gatoArray[0][0] == "O") {
                            Color.Blue
                        } else {
                            Color.White
                        }
                    )
                    .clickable(onClick = {
                        if (gatoState.value.gatoArray[0][0].isBlank())
                            gatoViewModel.colocarValorJugadorAGatoArray(0, 0)
                    }),
                contentAlignment = Alignment.Center
            ) {
                Text(text = gatoState.value.gatoArray[0][0], color = Color.White, style = MaterialTheme.typography.h2)
            }
        }
        Column {
            Box(
                modifier = Modifier
                    .border(1.dp, Color.Black)
                    .size(100.dp)
                    .background(
                        if (gatoState.value.gatoArray[0][1] == "X") {
                            Color.Red
                        } else if (gatoState.value.gatoArray[0][1] == "O") {
                            Color.Blue
                        } else {
                            Color.White
                        }
                    )
                    .clickable(onClick = {
                        if (gatoState.value.gatoArray[0][1].isBlank())
                            gatoViewModel.colocarValorJugadorAGatoArray(0, 1)
                    }),
                contentAlignment = Alignment.Center
            ) {
                Text(text = gatoState.value.gatoArray[0][1], color = Color.White, style = MaterialTheme.typography.h2)
            }
        }
        Column {
            Box(
                modifier = Modifier
                    .border(1.dp, Color.Black)
                    .size(100.dp)
                    .background(
                        if (gatoState.value.gatoArray[0][2] == "X") {
                            Color.Red
                        } else if (gatoState.value.gatoArray[0][2] == "O") {
                            Color.Blue
                        } else {
                            Color.White
                        }
                    )
                    .clickable(onClick = {
                        if (gatoState.value.gatoArray[0][2].isBlank())
                            gatoViewModel.colocarValorJugadorAGatoArray(0, 2)
                    }),
                contentAlignment = Alignment.Center
            ) {
                Text(text = gatoState.value.gatoArray[0][2], color = Color.White, style = MaterialTheme.typography.h2)
            }
        }
    }

    Row(
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth()
            .background(Color.Black)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .border(1.dp, Color.Black)
                    .size(100.dp)
                    .background(
                        if (gatoState.value.gatoArray[1][0] == "X") {
                            Color.Red
                        } else if (gatoState.value.gatoArray[1][0] == "O") {
                            Color.Blue
                        } else {
                            Color.White
                        }
                    )
                    .clickable(onClick = {
                        if (gatoState.value.gatoArray[1][0].isBlank())
                            gatoViewModel.colocarValorJugadorAGatoArray(1, 0)
                    }),
                contentAlignment = Alignment.Center
            ) {
                Text(text = gatoState.value.gatoArray[1][0], color = Color.White, style = MaterialTheme.typography.h2)
            }
        }
        Column() {
            Box(
                modifier = Modifier
                    .border(1.dp, Color.Black)
                    .size(100.dp)
                    .background(
                        if (gatoState.value.gatoArray[1][1] == "X") {
                            Color.Red
                        } else if (gatoState.value.gatoArray[1][1] == "O") {
                            Color.Blue
                        } else {
                            Color.White
                        }
                    )
                    .clickable(onClick = {
                        if (gatoState.value.gatoArray[1][1].isBlank())
                            gatoViewModel.colocarValorJugadorAGatoArray(1, 1)
                    }),
                contentAlignment = Alignment.Center
            ) {
                Text(text = gatoState.value.gatoArray[1][1], color = Color.White, style = MaterialTheme.typography.h2)
            }
        }
        Column() {
            Box(
                modifier = Modifier
                    .border(1.dp, Color.Black)
                    .size(100.dp)
                    .background(
                        if (gatoState.value.gatoArray[1][2] == "X") {
                            Color.Red
                        } else if (gatoState.value.gatoArray[1][2] == "O") {
                            Color.Blue
                        } else {
                            Color.White
                        }
                    )
                    .clickable(onClick = {
                        if (gatoState.value.gatoArray[1][2].isBlank())
                            gatoViewModel.colocarValorJugadorAGatoArray(1, 2)
                    }),
                contentAlignment = Alignment.Center
            ) {
                Text(text = gatoState.value.gatoArray[1][2], color = Color.White, style = MaterialTheme.typography.h2)
            }
        }
    }

    Row(
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth()
            .background(Color.Black)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .border(1.dp, Color.Black)
                    .size(100.dp)
                    .background(
                        if (gatoState.value.gatoArray[2][0] == "X") {
                            Color.Red
                        } else if (gatoState.value.gatoArray[2][0] == "O") {
                            Color.Blue
                        } else {
                            Color.White
                        }
                    )
                    .clickable(onClick = {
                        if (gatoState.value.gatoArray[2][0].isBlank())
                            gatoViewModel.colocarValorJugadorAGatoArray(2, 0)
                    }),
                contentAlignment = Alignment.Center
            ) {
                Text(text = gatoState.value.gatoArray[2][0], color = Color.White, style = MaterialTheme.typography.h2)
            }
        }
        Column() {
            Box(
                modifier = Modifier
                    .border(1.dp, Color.Black)
                    .size(100.dp)
                    .background(
                        if (gatoState.value.gatoArray[2][1] == "X") {
                            Color.Red
                        } else if (gatoState.value.gatoArray[2][1] == "O") {
                            Color.Blue
                        } else {
                            Color.White
                        }
                    )
                    .clickable(onClick = {
                        if (gatoState.value.gatoArray[2][1].isBlank())
                            gatoViewModel.colocarValorJugadorAGatoArray(2, 1)
                    }),
                contentAlignment = Alignment.Center
            ) {
                Text(text = gatoState.value.gatoArray[2][1], color = Color.White, style = MaterialTheme.typography.h2)
            }
        }
        Column() {
            Box(
                modifier = Modifier
                    .border(1.dp, Color.Black)
                    .size(100.dp)
                    .background(
                        if (gatoState.value.gatoArray[2][2] == "X") {
                            Color.Red
                        } else if (gatoState.value.gatoArray[2][2] == "O") {
                            Color.Blue
                        } else {
                            Color.White
                        }
                    )
                    .clickable(onClick = {
                        if (gatoState.value.gatoArray[2][2].isBlank())
                            gatoViewModel.colocarValorJugadorAGatoArray(2, 2)
                    }),
                contentAlignment = Alignment.Center
            ) {
                Text(text = gatoState.value.gatoArray[2][2], color = Color.White, style = MaterialTheme.typography.h2)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GatoTheme {
        GatoBoard()
    }
}

package com.aeax.smndice.ui.screens.scoreboard

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aeax.smndice.domain.models.game.Player
import com.aeax.smndice.ui.components.MiBotonAccion
import com.aeax.smndice.ui.theme.Fondo

@Composable
fun ScoreboardScreen() {
    val scoreboardViewModel: ScoreboardViewModel = hiltViewModel()
    val currentState = scoreboardViewModel.state.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PostButton(scoreboardViewModel = scoreboardViewModel)

        Divider(startIndent = 8.dp, thickness = 20.dp, color = Fondo)

        Text(text = "Lista de resultados", modifier = Modifier.fillMaxWidth(), style = TextStyle(fontSize = 24.sp, color = Color.White))

        when (currentState) {
            is ScoreboardState.Loading -> {
                LoadingData()
            }
            is ScoreboardState.Success -> {
                SuccessData(currentState.scores)
            }
            is ScoreboardState.Error -> {
                ErrorData(currentState.message)
            }
        }
    }
}

@Composable
fun LoadingData() {
    Text(
        text = "Cargando...",
        modifier = Modifier.fillMaxWidth(),
        style = TextStyle(fontSize = 24.sp, color = Color.White)
    )
}

@Composable
fun ErrorData(message: String) {
    Text(
        text = message,
        modifier = Modifier.fillMaxWidth(),
        style = TextStyle(fontSize = 24.sp, color = Color.White)
    )
}

@Composable
fun SuccessData(scores: List<Player>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        content = {
            items(scores) { playerScore ->
                Text(
                    text = "${playerScore.name} - ${playerScore.score}",
                    modifier = Modifier.fillMaxWidth(),
                    style = TextStyle(color = Color.White)
                )
            }
        }
    )
}

@Composable
fun PostButton(scoreboardViewModel: ScoreboardViewModel) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        MiBotonAccion(
            texto = "Hacer POST",
            habilitado = true,
            onStart = {
                scoreboardViewModel.postScore(Player(null, "Alan Castro 3 19100159", 999, 999))
            }
        )
    }
}
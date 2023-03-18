package com.aeax.curpproject.ui.info.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.aeax.curpproject.ui.Routes
import com.aeax.curpproject.ui.theme.Error
import com.aeax.curpproject.ui.theme.Success

@Composable
fun InfoScreen(
    message: String,
    isError: Boolean,
    navigationController: NavHostController
) {
    ResultView(message, isError) {
        navigationController.navigate(Routes.Register.path)
    }
}
@Composable
fun ResultView(curp: String, isError: Boolean, onClick: () -> Unit){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = if (isError) Error else Success),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        if(isError)
            Icon(
                Icons.Filled.Close,
                modifier = Modifier.size(100.dp),
                contentDescription = "DONE CURP",
                tint = Color.White
            )
        else
            Icon(
                Icons.Outlined.CheckCircle,
                modifier = Modifier.size(100.dp),
                contentDescription = "DONE CURP",
                tint = Color.White
            )

        Text(
            text = curp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        )
        OutlinedButton(
            onClick = { onClick() }
        ) {
            Text(text = "Entendido")
        }
    }
}

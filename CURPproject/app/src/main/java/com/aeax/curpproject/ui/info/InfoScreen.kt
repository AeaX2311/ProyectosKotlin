package com.aeax.curpproject.ui.info

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.aeax.curpproject.models.Routes
import com.aeax.curpproject.ui.navbar.NavbarFooter
import com.aeax.curpproject.ui.navbar.NavbarTop
import com.aeax.curpproject.ui.theme.Error
import com.aeax.curpproject.ui.theme.PrimaryButton
import com.aeax.curpproject.ui.theme.Success

@Composable
fun InfoScreen(
    message: String,
    isError: Boolean,
    navigationController: NavHostController
) {
    ResultView(message, isError) {
        navigationController.navigate(Routes.SelectorMode.path)
    }
}
@Composable
fun ResultView(curp: String, isError: Boolean, onClick: () -> Unit) {
    Scaffold(
        topBar = { NavbarTop( title = "Resultado", subTitle = "El curp en base a tus datos proporcionados" ) },
        bottomBar = { NavbarFooter(false) {} },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(padding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                if(isError)
                    Icon(
                        Icons.Filled.Close,
                        modifier = Modifier.size(100.dp),
                        contentDescription = "FAILED CURP",
                        tint = Error
                    )
                else
                    Icon(
                        Icons.Outlined.CheckCircle,
                        modifier = Modifier.size(100.dp),
                        contentDescription = "DONE CURP",
                        tint = Success
                    )

                Text(
                    text = curp,
                    color = if (isError) Error else Success,
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp
                )
                Button(
                    onClick = { onClick() },
                    colors = ButtonDefaults.buttonColors(backgroundColor = PrimaryButton)
                ) {
                    Text(text = "Entendido", color = Color.White)
                }
            }
        }
    )
}

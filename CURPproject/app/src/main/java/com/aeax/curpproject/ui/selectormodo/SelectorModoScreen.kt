package com.aeax.curpproject.ui.selectormodo

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.aeax.curpproject.R
import com.aeax.curpproject.models.Routes
import com.aeax.curpproject.ui.navbar.NavbarFooter
import com.aeax.curpproject.ui.navbar.NavbarTop
import com.aeax.curpproject.ui.theme.CURPprojectTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SelectorModeScreen(navController: NavHostController) {
    CURPprojectTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Scaffold(
                topBar = { NavbarTop(title = "Generar curp", subTitle = "Modo de uso") },
                bottomBar = { NavbarFooter(false) {} }
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(text = stringResource(R.string.info_modo_uso), textAlign = TextAlign.Center, modifier = Modifier.padding(16.dp))

                    Button(
                        onClick = { navController.navigate(Routes.WizardRegister.path) },

                    ) {
                        Text(text = stringResource(R.string.modo_wizard), color = Color.White)
                    }

                    Button(
                        onClick = { navController.navigate(Routes.TraditionalRegister.path) },
                    ) {
                        Text(text = stringResource(R.string.modo_tradicional), color = Color.White)
                    }
                }
            }
        }
    }
}

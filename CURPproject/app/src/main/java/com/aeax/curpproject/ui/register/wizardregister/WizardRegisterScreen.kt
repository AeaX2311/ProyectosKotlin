package com.aeax.curpproject.ui.register.wizardregister

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import com.aeax.curpproject.ui.info.InfoScreen
import com.aeax.curpproject.ui.loading.LoadingScreen
import com.aeax.curpproject.ui.register.wizardregister.models.FormStatus

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WizardRegisterScreen(registerViewModel: WizardRegisterViewModel, navController: NavHostController) {
    when (val status = registerViewModel.uiFormStatusState.collectAsState().value) {
        is FormStatus.Loading -> LoadingScreen()
        is FormStatus.NameScreen -> NameScreen(registerViewModel)
        is FormStatus.DateScreen -> DateScreen(registerViewModel)
        is FormStatus.GenderScreen -> GenderScreen(registerViewModel)
        is FormStatus.StateScreen -> StateScreen(registerViewModel)
        is FormStatus.Error -> InfoScreen(message = status.message, isError = status.isError, navController)
        is FormStatus.Success ->  InfoScreen(message = status.message, isError = status.isError, navController)
        else -> {}
    }
}
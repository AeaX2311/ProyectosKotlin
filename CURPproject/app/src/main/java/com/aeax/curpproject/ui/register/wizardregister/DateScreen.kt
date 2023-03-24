package com.aeax.curpproject.ui.register.wizardregister

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.aeax.curpproject.R
import com.aeax.curpproject.ui.navbar.NavbarFooter
import com.aeax.curpproject.ui.navbar.NavbarTop
import com.aeax.curpproject.ui.register.components.DatePicker

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DateScreen(registerViewModel: WizardRegisterViewModel) {
    Scaffold(
        topBar = { NavbarTop( title = "Fecha de nacimiento", subTitle = "Ingresa la fecha en la que naciste." ) },
        bottomBar = { NavbarFooter(true) { registerViewModel.onSubmitForm() } },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(padding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val person = registerViewModel.uiPersonState.collectAsState().value

                DatePicker(
                    value = person.birthDate,
                    onValueChange = { registerViewModel.setBirthDate(it) },
                    label = { Text(text = stringResource(R.string.birth_date)) })
            }
        }
    )
}
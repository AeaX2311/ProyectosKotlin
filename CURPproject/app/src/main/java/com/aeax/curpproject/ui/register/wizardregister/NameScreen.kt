package com.aeax.curpproject.ui.register.wizardregister

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import com.aeax.curpproject.R
import com.aeax.curpproject.ui.navbar.NavbarFooter
import com.aeax.curpproject.ui.navbar.NavbarTop
import com.aeax.curpproject.ui.register.components.OutlinedTextFieldValidation

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NameScreen(registerViewModel: WizardRegisterViewModel) {
    Scaffold(
        topBar = { NavbarTop( title = "Nombre", subTitle = "Ingresa tu nombre." ) },
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

                OutlinedTextFieldValidation(
                    value = person.name,
                    onValueChange = { registerViewModel.setName(it) },
                    label = { Text(text = stringResource(R.string.name)) },
                    keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Characters)
                )

                OutlinedTextFieldValidation(
                    value = person.lastNameP,
                    onValueChange = { registerViewModel.setLastNameP(it) },
                    label = { Text(text = stringResource(R.string.last_name_p)) },
                    keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Characters)
                )

                OutlinedTextFieldValidation(
                    value = person.lastNameM,
                    onValueChange = { registerViewModel.setLastNameM(it) },
                    label = { Text(text = stringResource(R.string.last_name_m)) },
                    keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Characters)
                )
            }
        }
    )
}
package com.aeax.curpproject.ui.register.traditionalregister

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.aeax.curpproject.R
import com.aeax.curpproject.ui.info.InfoScreen
import com.aeax.curpproject.ui.loading.LoadingScreen
import com.aeax.curpproject.data.GENDER_LIST
import com.aeax.curpproject.data.STATES
import com.aeax.curpproject.ui.navbar.NavbarFooter
import com.aeax.curpproject.ui.navbar.NavbarTop
import com.aeax.curpproject.ui.register.wizardregister.models.FormStatus
import com.aeax.curpproject.ui.register.components.DatePicker
import com.aeax.curpproject.ui.register.components.Dropdown
import com.aeax.curpproject.ui.register.components.OutlinedTextFieldValidation
import com.aeax.curpproject.ui.register.components.RadioGroupWithSelectable
import com.aeax.curpproject.ui.theme.CURPprojectTheme

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TraditionalRegisterScreen(registerViewModel: TraditionalRegisterViewModel, navController: NavHostController) {
    when (val status = registerViewModel.uiFormStatusState.collectAsState().value) {
        is FormStatus.Loading -> LoadingScreen()
        is FormStatus.Loaded -> TraditionalRegisterForm(registerViewModel)
        is FormStatus.Error -> InfoScreen(message = status.message, isError = status.isError, navController)
        is FormStatus.Success ->  InfoScreen(message = status.message, isError = status.isError, navController)
        else -> {}
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TraditionalRegisterForm(registerViewModel: TraditionalRegisterViewModel) {
    CURPprojectTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Scaffold(
                topBar = { NavbarTop( title = "Modo tradicional", subTitle = "Rellena los datos del formulario" ) },
                bottomBar = { NavbarFooter(false) { } },
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

                        DatePicker(
                            value = person.birthDate,
                            onValueChange = { registerViewModel.setBirthDate(it) },
                            label = { Text(text = stringResource(R.string.birth_date)) })

                        Text(text = stringResource(R.string.sex))
                        RadioGroupWithSelectable(modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                            items = GENDER_LIST,
                            selection = person.gender,
                            onItemClick = { clickedItem -> registerViewModel.setGender(clickedItem) })

                        Text(text = stringResource(R.string.state))
                        Dropdown(STATES, onSelectItem = { registerViewModel.setState(it) })

                        Button(onClick = {
                            registerViewModel.generateCurp()
                        }) { Text(text = stringResource(R.string.generate)) }
                    }
                }
            )
        }
    }
}

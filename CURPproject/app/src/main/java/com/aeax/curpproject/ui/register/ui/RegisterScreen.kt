package com.aeax.curpproject.ui.register.ui

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
import com.aeax.curpproject.ui.info.ui.InfoScreen
import com.aeax.curpproject.ui.loading.LoadingScreen
import com.aeax.curpproject.ui.register.data.GENDER_LIST
import com.aeax.curpproject.ui.register.data.STATES
import com.aeax.curpproject.ui.register.models.FormStatus
import com.aeax.curpproject.ui.register.ui.components.DatePicker
import com.aeax.curpproject.ui.register.ui.components.Dropdown
import com.aeax.curpproject.ui.register.ui.components.OutlinedTextFieldValidation
import com.aeax.curpproject.ui.register.ui.components.RadioGroupWithSelectable
import com.aeax.curpproject.ui.theme.CURPprojectTheme

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RegisterScreen(registerViewModel: RegisterViewModel, navController: NavHostController) {
    when (val status = registerViewModel.uiFormStatusState.collectAsState().value) {
        is FormStatus.Loading -> LoadingScreen()
        is FormStatus.Loaded -> RegisterForm(registerViewModel)
        is FormStatus.Error -> InfoScreen(message = status.message, isError = status.isError, navController)
        is FormStatus.Success ->  InfoScreen(message = status.message, isError = status.isError, navController)
        else -> {}
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RegisterForm(registerViewModel: RegisterViewModel) {
    CURPprojectTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text("Generador de CURP")
                        }
                    )
                }
            ) {
                val person = registerViewModel.uiPersonState.collectAsState().value

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
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

                    Text(text = person.curp)
                }
            }
        }
    }
}
package com.aeax.curpproject.ui.register.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import com.aeax.curpproject.R
import com.aeax.curpproject.ui.register.data.GENDER_LIST
import com.aeax.curpproject.ui.register.data.STATES
import com.aeax.curpproject.ui.register.ui.components.DatePicker
import com.aeax.curpproject.ui.register.ui.components.Dropdown
import com.aeax.curpproject.ui.register.ui.components.RadioGroupWithSelectable
import com.aeax.curpproject.ui.theme.CURPprojectTheme

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RegisterScreen(registerViewModel: RegisterViewModel) {
    CURPprojectTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            MainForm(registerViewModel)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainForm(registerViewModel: RegisterViewModel) {
    val person = registerViewModel.uiPersonState.collectAsState().value

    Column (
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ){
        Text(text = stringResource(R.string.name))
        TextField(value = person.name, onValueChange = { registerViewModel.setName(it) }, modifier = Modifier.fillMaxWidth(), maxLines = 1, singleLine = true,
            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Characters)
        )

        Text(text = stringResource(R.string.last_name_p))
        TextField(value = person.lastNameP, onValueChange = { registerViewModel.setLastNameP(it) }, modifier = Modifier.fillMaxWidth(), maxLines = 1, singleLine = true,
            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Characters)
        )

        Text(text = stringResource(R.string.last_name_m))
        TextField(value = person.lastNameM, onValueChange = { registerViewModel.setLastNameM(it) }, modifier = Modifier.fillMaxWidth(), maxLines = 1, singleLine = true,
            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Characters)
        )

        Text(text = stringResource(R.string.birth_date))
        DatePicker(value = person.birthDate, onValueChange = { registerViewModel.setBirthDate(it) })

        Text(text = stringResource(R.string.sex))
        RadioGroupWithSelectable(modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(), items = GENDER_LIST, selection = person.gender, onItemClick = { clickedItem -> registerViewModel.setGender(clickedItem) })

        Text(text = stringResource(R.string.state))
        Dropdown(STATES, onSelectItem = { registerViewModel.setState(it) })

        Button(onClick = {
            registerViewModel.generateCurp()
        }) { Text(text = stringResource(R.string.generate)) }

        Text(text = person.curp)
    }
}
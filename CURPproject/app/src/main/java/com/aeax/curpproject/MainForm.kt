package com.aeax.curpproject

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aeax.curpproject.ui.theme.CURPprojectTheme

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CURPprojectTheme {
        Curp()
    }
}

@Composable
fun Curp() {
    CURPprojectTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            MainForm()
        }
    }
}

@Composable
fun MainForm() {
    var name by remember { mutableStateOf("") }
    var lastNameP by remember { mutableStateOf("") }
    var lastNameM by remember { mutableStateOf("") }
    var dayBirth by remember { mutableStateOf("") }
    var monthBirth by remember { mutableStateOf("") }
    var yearBirth by remember { mutableStateOf("") }
    var state by remember { mutableStateOf("") }

    val genderList = listOf("Mujer", "Hombre", "No binario")
    val selectedGender = remember { mutableStateOf(genderList.first()) }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())

    ){
        Text(text = stringResource(R.string.name))
        TextField(value = name, onValueChange = { name = it }, modifier = Modifier.fillMaxWidth(), maxLines = 1, singleLine = true)

        Text(text = dayBirth)
        Text(text = monthBirth)
        Text(text = yearBirth)
        Text(text = selectedGender.value)


        Text(text = stringResource(R.string.last_name_p))
        TextField(value = lastNameP, onValueChange = { lastNameP = it }, modifier = Modifier.fillMaxWidth(), maxLines = 1, singleLine = true)

        Text(text = stringResource(R.string.last_name_m))
        TextField(value = lastNameM, onValueChange = { lastNameM = it }, modifier = Modifier.fillMaxWidth(), maxLines = 1, singleLine = true)

        Text(text = stringResource(R.string.day_birth))
        TextField(value = dayBirth, onValueChange =  { dayBirth = it }, modifier = Modifier.fillMaxWidth(), keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number), maxLines = 1, singleLine = true)

        Text(text = stringResource(R.string.month_birth))
        TextField(value = monthBirth, onValueChange = { monthBirth = it }, modifier = Modifier.fillMaxWidth(), maxLines = 1, singleLine = true)

        Text(text = stringResource(R.string.year_birth))
        TextField(value = yearBirth, onValueChange = { yearBirth = it }, modifier = Modifier.fillMaxWidth(), maxLines = 1, singleLine = true)

        Text(text = stringResource(R.string.sex))


        RadioGroupWithSelectable(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            items = genderList,
            selection = selectedGender.value,
            onItemClick = { clickedItem ->
                selectedGender.value = clickedItem
            }
        )

        Text(text = stringResource(R.string.state))
        TextField(value = state, onValueChange = { state = it }, maxLines = 1, modifier = Modifier.fillMaxWidth(), singleLine = true)

    }

}

@Composable
fun LabelledRadioButton(
    modifier: Modifier = Modifier,
    label: String,
    selected: Boolean,
    onClick: (() -> Unit)?,
    enabled: Boolean = true,
    colors: RadioButtonColors = RadioButtonDefaults.colors()
) {

    Row(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .height(56.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick,
            enabled = enabled,
            colors = colors
        )

        Text(
            text = label,
            style = MaterialTheme.typography.body1.merge(),
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}

@Composable
fun RadioGroupWithSelectable(
    modifier: Modifier,
    items: List<String>,
    selection: String,
    onItemClick: ((String) -> Unit)
) {
    Column(modifier = modifier.selectableGroup()) { // (1)
        items.forEach { item ->
            LabelledRadioButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .selectable( // (2)
                        selected = item == selection,
                        onClick = { onItemClick(item) },
                        role = Role.RadioButton
                    ),
                label = item,
                selected = item == selection,
                onClick = null // (3)
            )
        }
    }
}

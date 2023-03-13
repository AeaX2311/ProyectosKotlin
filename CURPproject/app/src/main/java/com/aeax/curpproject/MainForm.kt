package com.aeax.curpproject

import android.app.DatePickerDialog
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aeax.curpproject.ui.theme.CURPprojectTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter

val genderList = arrayListOf(
    Pair("M", "Mujer"),
    Pair("H", "Hombre"),
    Pair("X", "No binario")
)
val states = arrayListOf(
    Pair("AS", "Aguascalientes"),
    Pair("BC", "Baja California"),
    Pair("BS", "Baja California Sur"),
    Pair("CC", "Campeche"),
    Pair("CL", "Coahuila"),
    Pair("CM", "Colima"),
    Pair("CS", "Chiapas"),
    Pair("CH", "Chihuahua"),
    Pair("DF", "Ciudad de México"),
    Pair("DG", "Durango"),
    Pair("GT", "Guanajuato"),
    Pair("GR", "Guerrero"),
    Pair("HG", "Hidalgo"),
    Pair("JC", "Jalisco"),
    Pair("MC", "Estado de México"),
    Pair("MN", "Michoacán"),
    Pair("MS", "Morelos"),
    Pair("NT", "Nayarit"),
    Pair("NL", "Nuevo León"),
    Pair("OC", "Oaxaca"),
    Pair("PL", "Puebla"),
    Pair("QT", "Querétaro"),
    Pair("QR", "Quintana Roo"),
    Pair("SP", "San Luis Potosí"),
    Pair("SL", "Sinaloa"),
    Pair("SR", "Sonora"),
    Pair("TC", "Tabasco"),
    Pair("TS", "Tamaulipas"),
    Pair("TL", "Tlaxcala"),
    Pair("VZ", "Veracruz"),
    Pair("YN", "Yucatán"),
    Pair("ZS", "Zacatecas"),
    Pair("NE", "Nacido en el extranjero")
)

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CURPprojectTheme {
        Curp()
    }
}

@RequiresApi(Build.VERSION_CODES.O)
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

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainForm() {
    var name by remember { mutableStateOf("") }
    var lastNameP by remember { mutableStateOf("") }
    var lastNameM by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }
    var state by remember { mutableStateOf("") }
    var selectedGender by remember { mutableStateOf("") }

    var curpResult by remember { mutableStateOf("") }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ){
        Text(text = stringResource(R.string.name))
        TextField(value = name, onValueChange = { name = it }, modifier = Modifier.fillMaxWidth(), maxLines = 1, singleLine = true)

        Text(text = stringResource(R.string.last_name_p))
        TextField(value = lastNameP, onValueChange = { lastNameP = it }, modifier = Modifier.fillMaxWidth(), maxLines = 1, singleLine = true)

        Text(text = stringResource(R.string.last_name_m))
        TextField(value = lastNameM, onValueChange = { lastNameM = it }, modifier = Modifier.fillMaxWidth(), maxLines = 1, singleLine = true)

        Text(text = stringResource(R.string.birth_date))
        DatePickerDate(value = birthDate, onValueChange = { birthDate = it })

        Text(text = stringResource(R.string.sex))
        RadioGroupWithSelectable(modifier = Modifier.padding(16.dp).fillMaxWidth(), items = genderList, selection = selectedGender, onItemClick = { clickedItem -> selectedGender = clickedItem })

        Text(text = stringResource(R.string.state))
        Dropdown(states, onSelectItem = { state = it })

        Button(onClick = {
            curpResult = generateCurp(name, lastNameP, lastNameM, birthDate, selectedGender, state)
        }) { Text(text = stringResource(R.string.generate)) }

        Text(text = curpResult)
    }

}

@RequiresApi(Build.VERSION_CODES.O)
fun generateCurp(name: String, lastNameP: String, lastNameM: String, birthDate: String, gender: String, state: String): String {
    var curp = lastNameP.substring(0, 1) //Primera letra del apellido paterno
    curp += findVocal(lastNameP) //Primera vocal del apellido paterno
    curp += lastNameM.substring(0, 1) //Primera letra del apellido materno
    curp += name.substring(0, 1) //Primera letra del nombre

    curp = deleteInconvenientWords(curp) //Eliminar palabras no permitidas

    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val date = LocalDate.parse(birthDate, formatter)
    curp += date.format(DateTimeFormatter.ofPattern("yy")) //Año de nacimiento
    curp += date.format(DateTimeFormatter.ofPattern("MM")) //Mes de nacimiento
    curp += date.format(DateTimeFormatter.ofPattern("dd")) //Dia de nacimiento

    curp += gender //Genero a un solo digito

    curp += state //Estado a dos digitos

    curp += findInternalConsonant(lastNameP) //Primera consonante interna del apellido paterno
    curp += findInternalConsonant(lastNameM) //Primera consonante interna del apellido materno
    curp += findInternalConsonant(name) //Primera consonante interna del nombre

    curp += "0" //Homoclave

    curp += generateVrfyDigit(curp) //Digito verificador

    return curp.uppercase() //En mayusculas to-do
}

fun findVocal(lastNameP: String): String {
    for (i in lastNameP.indices) {
        if (lastNameP[i] == 'a' || lastNameP[i] == 'e' || lastNameP[i] == 'i' || lastNameP[i] == 'o' || lastNameP[i] == 'u') {
            return lastNameP[i].toString()
        }
    }
    return "X" //No deberia llegar jamas
}

fun findInternalConsonant(lastNameP: String): String {
    for (i in lastNameP.indices) {
        if (i == 0 || i == lastNameP.length - 1) continue //No se toma en cuenta la primera y ultima letra
        if (lastNameP[i] != 'a' && lastNameP[i] != 'e' && lastNameP[i] != 'i' && lastNameP[i] != 'o' && lastNameP[i] != 'u') {
            return lastNameP[i].toString()
        }
    }
    return "X" //No deberia llegar jamas
}

fun generateVrfyDigit(curp: String): String {
    val letters = "0123456789ABCDEFGHIJKLMNÑOPQRSTUVWXYZ"
    var sum = 0
    var digit = 0
    for (i in curp.indices) {
        digit = letters.indexOf(curp[i])
        sum += digit * (18 - i)
    }
    return letters[sum % 10].toString()
}

fun deleteInconvenientWords(curp: String) :String {


    return curp
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
    items: ArrayList<Pair<String, String>>,
    selection: String,
    onItemClick: ((String) -> Unit)
) {
    Column(modifier = modifier.selectableGroup()) {
        items.forEach { item ->
            LabelledRadioButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = item.first == selection,
                        onClick = { onItemClick(item.first) },
                        role = Role.RadioButton
                    ),
                label = item.second,
                selected = item.first == selection,
                onClick = null
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DatePickerDate(
    value: String,
    onValueChange: (String) -> Unit = {},
){
    val formatter= DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val date = if(value.isNotBlank()) LocalDate.parse(value,formatter) else LocalDate.now()
    val dialog = DatePickerDialog(
        LocalContext.current,
        { _,year,month,dayOfMonth ->
            onValueChange(LocalDate.of(year,month+1, dayOfMonth).toString())
        },
        date.year,
        date.monthValue -1,
        date.dayOfMonth,
    )
    TextField(
        value = value,
        onValueChange = onValueChange,
        enabled = false,
        readOnly = true,
        modifier = Modifier
            .clickable { dialog.show() }
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions.Default,
        keyboardActions = KeyboardActions.Default,
    )
}

@Composable
fun Dropdown(items: ArrayList<Pair<String, String>>, onSelectItem: (String) -> Unit = {}) {
    var expanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(0) }

    Box(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.TopStart)) {
        Text(items[selectedIndex].second,modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { expanded = true }))
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            items.forEachIndexed { index, item ->
                DropdownMenuItem(onClick = {
                    selectedIndex = index
                    expanded = false
                    onSelectItem(item.first)
                }) {
                    Text(text = item.second)
                }
            }
        }
    }
}

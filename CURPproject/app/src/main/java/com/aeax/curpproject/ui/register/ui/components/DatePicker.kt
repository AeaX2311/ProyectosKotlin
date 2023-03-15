package com.aeax.curpproject.ui.register.ui.components

import android.app.DatePickerDialog
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DatePicker(
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
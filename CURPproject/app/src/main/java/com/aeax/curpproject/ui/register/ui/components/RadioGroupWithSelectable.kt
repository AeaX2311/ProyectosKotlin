package com.aeax.curpproject.ui.register.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role

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
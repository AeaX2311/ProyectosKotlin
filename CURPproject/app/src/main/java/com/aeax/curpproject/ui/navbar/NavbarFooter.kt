package com.aeax.curpproject.ui.navbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.aeax.curpproject.R
import com.aeax.curpproject.ui.theme.PrimaryButton

@Composable
fun NavbarFooter(containsButton : Boolean, toNextStep : () -> Unit) {
    val footer : Painter = painterResource(id = R.drawable.footer)
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.End
    ) {
        if(containsButton) {
            Button(
                onClick = { toNextStep() },
                colors = ButtonDefaults.buttonColors(backgroundColor = PrimaryButton)
            ) {
                Text(text = "Siguiente", color = Color.White)
            }
        }
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = footer,
            contentDescription = stringResource(R.string.footer)
        )
    }
}

@Preview
@Composable
fun NavbarFooterPreview() {
    NavbarFooter(true) {}
}

package com.aeax.curpproject.ui.navbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aeax.curpproject.R
import com.aeax.curpproject.ui.theme.PrimaryBackground

@Composable
fun NavbarTop(
    title: String,
    subTitle: String
) {

    val logo : Painter = painterResource(id = R.drawable.logoheader)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(color = PrimaryBackground),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = title,
                fontSize = 20.sp,
                color = Color.White
            )
            Text(
                text = subTitle,
                fontSize = 8.sp,
                color = Color.White
            )
        }

        Image(
            painter = logo,
            contentDescription = stringResource(R.string.logo),
            modifier = Modifier
                .padding(16.dp)
                .size(70.dp)
        )
    }
}

@Preview
@Composable
fun PreviewNavbarTop() {
    NavbarTop(
        title = "CURP",
        subTitle = "Registro"
    )
}
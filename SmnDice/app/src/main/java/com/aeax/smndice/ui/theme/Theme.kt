package com.aeax.smndice.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = lightColors(
    primary = FondoSecundarioD,
    primaryVariant = Fondo2D,
    secondary = FondoD
)

private val LightColorPalette = lightColors(
    primary = FondoSecundario,
    primaryVariant = Fondo2,
    secondary = Fondo
)

@Composable
fun SmnDiceTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val lightColors = LightColorPalette
    val darkColors = DarkColorPalette

    MaterialTheme(
        colors = if (darkTheme) darkColors else lightColors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
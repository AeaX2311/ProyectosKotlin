package com.aeax.curpproject.ui.loading

import androidx.compose.runtime.Composable
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.aeax.curpproject.ui.theme.CURPprojectTheme
import com.aeax.curpproject.ui.theme.PrimaryBackground
import kotlinx.coroutines.delay

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier,
    circleSize: Dp = 25.dp,
    circleColor: Color = PrimaryBackground,
    spaceBetween: Dp = 10.dp,
    travelDistance: Dp = 20.dp
) {
    val circles = listOf(
        remember { Animatable(initialValue = 0f) },
        remember { Animatable(initialValue = 0f) },
        remember { Animatable(initialValue = 0f) }
    )

    circles.forEachIndexed { index, animatable ->
        LaunchedEffect(key1 = animatable) {
            delay(index * 100L)
            animatable.animateTo(
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    animation = keyframes {
                        durationMillis = 600
                        0.0f at 0 with LinearOutSlowInEasing
                        1.0f at 300 with LinearOutSlowInEasing
                        0.0f at 600 with LinearOutSlowInEasing
                        0.0f at 1200 with LinearOutSlowInEasing
                    },
                    repeatMode = RepeatMode.Restart
                )
            )
        }
    }

    val circleValues = circles.map { it.value }
    val distance = with(LocalDensity.current) { travelDistance.toPx() }

    CURPprojectTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = modifier,
                    horizontalArrangement = Arrangement.spacedBy(spaceBetween),
                ) {
                    circleValues.forEach { value ->
                        Box(
                            modifier = Modifier
                                .size(circleSize)
                                .graphicsLayer {
                                    translationY = -value * distance
                                }
                                .background(
                                    color = circleColor,
                                    shape = CircleShape
                                )
                        )
                    }
                }
            }
        }
    }
}
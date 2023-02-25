package com.example.primeraapp.figures

import android.content.Context
import android.media.MediaPlayer
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.primeraapp.ui.theme.black
import com.example.primeraapp.ui.theme.creeperBase
import com.example.primeraapp.ui.theme.creeperMouth
import com.example.primeraapp.R

@Composable
fun Creeper(context: Context) {
    Column(modifier = Modifier
        .fillMaxSize()
        .clickable { MediaPlayer.create(context, R.raw.creeper).start() }
        .wrapContentSize(Alignment.Center)) {
        Box(modifier = Modifier
            .background(creeperBase)
            .size(400.dp)) {
            CreeperFace()
        }
    }
}

@Composable
fun CreeperFace() {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val(eyes, mouth, effect) = createRefs()

        CreeperEyes(modifier = Modifier.constrainAs(eyes) {
            top.linkTo(parent.top, 100.dp)
            start.linkTo(parent.start, 50.dp)
        })

        CreeperM3(modifier = Modifier.constrainAs(effect) {
            start.linkTo(eyes.start, 50.dp)
            top.linkTo(eyes.top, 50.dp)
        })

        CreeperMouth(modifier = Modifier.constrainAs(mouth) {
            top.linkTo(parent.top, 200.dp)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })
    }
}

@Composable
fun CreeperEyes(modifier: Modifier) {
    Row (
        modifier = modifier.width(300.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CreeperEye()
        CreeperEye()
    }
}

@Composable
fun CreeperEye() {
    Box(
        modifier = Modifier
            .background(black)
            .width(100.dp)
            .height(100.dp)
    )
}

@Composable
fun CreeperMouth(modifier: Modifier) {
    ConstraintLayout (
        modifier =
        modifier.size(200.dp)
    ){
        val(p1, p2, p3) = createRefs()

        CreeperM1(modifier = Modifier.constrainAs(p1) {
            bottom.linkTo(p2.top)
            start.linkTo(p2.start, 50.dp)
        })

        CreeperM2(modifier = Modifier.constrainAs(p2) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })

        CreeperM3(modifier = Modifier.constrainAs(p3) {
            top.linkTo(p2.bottom)
            start.linkTo(p2.start)
        })
    }
}

@Composable
fun CreeperM1(modifier: Modifier) {
    Box(
        modifier = modifier
            .background(creeperMouth)
            .width(100.dp)
            .height(50.dp)
    ){}
}

@Composable
fun CreeperM2(modifier: Modifier) {
    Box(
        modifier = modifier
            .background(black)
            .width(200.dp)
            .height(100.dp)
    ){
        CreeperM3(modifier)
    }
}

@Composable
fun CreeperM3(modifier: Modifier) {
    Row (
        modifier = modifier.width(200.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CreeperPoint()
        CreeperPoint()
    }
}

@Composable
fun CreeperPoint() {
    Box(
        modifier = Modifier
            .background(creeperMouth)
            .size(50.dp)
    ){}
}
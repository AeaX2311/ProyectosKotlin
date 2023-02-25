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
import com.example.primeraapp.ui.theme.*
import com.example.primeraapp.R

@Composable
fun Pig(context: Context) {
    Column(modifier = Modifier
        .fillMaxSize()
        .clickable { MediaPlayer.create(context, R.raw.pig).start() }
        .wrapContentSize(Alignment.Center)) {
        Box(modifier = Modifier
            .background(pigBase)
            .size(400.dp)) {
            PigFace()
        }
    }
}

@Composable
fun PigFace() {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val(eyes, nose) = createRefs()
        PigEyes(modifier = Modifier.constrainAs(eyes) {
            bottom.linkTo(nose.top)
        })
        PigNose(modifier = Modifier.constrainAs(nose){
            top.linkTo(parent.top, 150.dp)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })
    }
}

@Composable
fun PigEyes(modifier: Modifier) {
    Row (
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        PigEye(false)
        PigEye(true)

    }
}

@Composable
fun PigEye(right: Boolean) {
    Row {
        Box(modifier = Modifier
            .size(50.dp)
            .background(if (right) white else black))
        Box(modifier = Modifier
            .size(50.dp)
            .background(if (right) black else white))
    }
}

@Composable
fun PigNose(modifier: Modifier) {
    Row (
        modifier = modifier
            .background(pigNoseBase)
            .width(192.dp)
            .height(150.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        PigNosePoint()
        PigNosePoint()
    }
}

@Composable
fun PigNosePoint() {
    Box(
        modifier = Modifier
            .background(pigNostrils)
            .size(50.dp)
    ){}
}
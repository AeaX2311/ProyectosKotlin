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
import com.example.primeraapp.ui.theme.skeletonBase
import com.example.primeraapp.ui.theme.skeletonComplement
import com.example.primeraapp.ui.theme.skeletonNose
import com.example.primeraapp.R

@Composable
fun Skeleton(context: Context) {
    Column(modifier = Modifier
        .fillMaxSize()
        .clickable { MediaPlayer.create(context, R.raw.skeleton).start() }
        .wrapContentSize(Alignment.Center)) {
        Box(modifier = Modifier
            .background(skeletonBase)
            .size(400.dp)) {
            SkeletonFace()
        }
    }
}

@Composable
fun SkeletonFace() {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val(eyes, nose, mouth) = createRefs()
        SkeletonEyes(modifier = Modifier.constrainAs(eyes) {
            bottom.linkTo(nose.top)
            start.linkTo(parent.start, 50.dp)
            end.linkTo(parent.end, 50.dp)
        })
        SkeletonNose(modifier = Modifier.constrainAs(nose){
            top.linkTo(parent.top, 150.dp)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })
        SkeletonMouth(modifier = Modifier.constrainAs(mouth) {
            top.linkTo(parent.top, 250.dp)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })
    }
}

@Composable
fun SkeletonEyes(modifier: Modifier) {
    Row (
        modifier = modifier.width(300.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        SkeletonEye()
        SkeletonEye()
    }
}

@Composable
fun SkeletonEye() {
    Box(
        modifier = Modifier
            .background(skeletonComplement)
            .width(100.dp)
            .height(50.dp)
    )
}

@Composable
fun SkeletonNose(modifier: Modifier) {
    Row (
        modifier = modifier
            .background(skeletonNose)
            .width(100.dp)
            .height(50.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {}
}

@Composable
fun SkeletonMouth(modifier: Modifier) {
    Box(
        modifier = modifier
            .background(skeletonComplement)
            .width(300.dp)
            .height(50.dp)
    )
}
package com.example.delta_task2

import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset


import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp

import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun GetLimit(){
    val animatedXc by animateFloatAsState(
        targetValue = xc.value,
        animationSpec = tween(durationMillis = 100)
    )
    val limitViewModel:ApiInteraction= viewModel()
    val viewState1 by  limitViewModel.obstacleCount
    val imageState by limitViewModel.imageTom
    if(viewState1.error==null){
        obstacle(Obstacle(viewState1.limit))
    }
    if(imageState.bitmap!=null){
        check.value=1
        Column(){
            Image(bitmap = imageState.bitmap!!.asImageBitmap(), contentDescription ="",
                modifier=Modifier.offset(animatedXc.dp, centreTom.value.dp-300.dp))
        }

    }

}
fun obstacle(obstacle: Obstacle){
    maxCollision.value=obstacle.obstacleLimit
}


package com.example.delta_task2

import android.util.Log
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text


import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
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
    val imageTomState by limitViewModel.imageTom
    val imageJerryState by limitViewModel.imageJerry
    val imageObstacleState by limitViewModel.imageObstacle
    val rewardPunish by limitViewModel.rewardPunish
    val targetSize = if (count.value == 1) 180.dp else 120.dp
    val animatedSize by animateDpAsState(
        targetValue = targetSize,
        animationSpec = tween(durationMillis = 400)
    )
    if(viewState1.error==null){
        obstacle(Obstacle(viewState1.limit))
    }
    when{
        imageObstacleState.loading->{
            check.value=1
            Text("Loading")

        }
        !imageObstacleState.loading->{
            check.value=0
        }

    }
    if( imageTomState.bitmap!=null) {
        Image(
            bitmap = imageTomState.bitmap!!.asImageBitmap(), contentDescription = "",
            modifier = Modifier.offset(animatedXc.dp, centreTom.value.dp)
        )
    }

    if(imageJerryState.bitmap!=null){

            Image(bitmap = imageJerryState.bitmap!!.asImageBitmap(), contentDescription ="",
                modifier= Modifier
                    .offset(animatedXc.dp, centreJerry.value.dp)
                    .size(animatedSize)
                    .aspectRatio(1f))

    }

    if(imageObstacleState.bitmap!=null && imageObstacleState.error==null){

            for (i in 1..100) {
                if(i%2==0||i%3==0 ) {
                   Image(bitmap = imageObstacleState.bitmap!!.asImageBitmap(), contentDescription = null,
                       modifier = Modifier
                           .offset(0.dp, (centreObstale.value - (350 * (2 * i + 1))).dp)
                           .size(100.dp)
                   )
                }
                if (i % 2 == 0) {
                    Image(bitmap = imageObstacleState.bitmap!!.asImageBitmap(), contentDescription = null,
                        modifier = Modifier
                            .offset(-125.dp, (centreObstale.value - (700 * i)).dp)
                            .size(100.dp)
                    )
                }
                if(i%2==0) {
                    Image(bitmap = imageObstacleState.bitmap!!.asImageBitmap(), contentDescription = null,
                        modifier = Modifier
                            .offset(120.dp, (centreObstale.value - (700 * i)).dp)
                            .size(100.dp)
                    )
                }

            }
        }
        if(rewardPunish.error==null){
            hitHindrance(hindrance =HitHindrance(rewardPunish.type,rewardPunish.amount,rewardPunish.process))
        }
    }


fun obstacle(obstacle: Obstacle){
    maxCollision.value=obstacle.obstacleLimit
}

fun hitHindrance(hindrance: HitHindrance){
    type.value=hindrance.type
    amount.value=hindrance.amount
}


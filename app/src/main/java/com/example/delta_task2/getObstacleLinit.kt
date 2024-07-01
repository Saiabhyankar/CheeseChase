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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun GetLimit(){
    extent.value= 100
    val trapNew= painterResource(id = R.drawable.newtrap)
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
    val course by limitViewModel.course
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
            var j = 0
            for (i in path) {
                when (i.lowercase()) {
                    "m" -> {
                        Image(
                            bitmap = imageObstacleState.bitmap!!.asImageBitmap(),
                            contentDescription = null,
                            modifier = Modifier
                                .offset(0.dp, (centreObstale.value - (150 * (2 * j + 1))).dp)
                                .size(100.dp)
                        )
                    }
                    "l" -> {
                        Image(
                            bitmap = imageObstacleState.bitmap!!.asImageBitmap(),
                            contentDescription = null,
                            modifier = Modifier
                                .offset(-125.dp, (centreObstale.value - (400 * j)).dp)
                                .size(100.dp)
                        )
                    }
                    "r" -> {
                        Image(
                            bitmap = imageObstacleState.bitmap!!.asImageBitmap(),
                            contentDescription = null,
                            modifier = Modifier
                                .offset(120.dp, (centreObstale.value - (300 * j)).dp)
                                .size(100.dp)
                        )
                    }
                    "b"->{
                        Image(painter = trapNew, contentDescription =null,
                            modifier = Modifier
                                .size(60.dp)
                                .offset(x = 0.dp, y = trapYCoord.value.dp))

                    }
                }
                j += 1
            }
        }
    if(rewardPunish.error==null){
            hitHindrance(hindrance =HitHindrance(rewardPunish.type,rewardPunish.amount,rewardPunish.process))
        }

    if(course.error==null){
        obstacleCourse(obstacleCourseResponse = ObstacleCourseResponse(course.course))
    }
    }


fun obstacle(obstacle: Obstacle){
    maxCollision.value=obstacle.obstacleLimit
}

fun hitHindrance(hindrance: HitHindrance){
    type.value=hindrance.type
    amount.value=hindrance.amount
}

fun obstacleCourse(obstacleCourseResponse: ObstacleCourseResponse){
    for (i in 0..<extent.value){
        path[i]= obstacleCourseResponse.obstacleCourse?.get(i).toString()
    }

}


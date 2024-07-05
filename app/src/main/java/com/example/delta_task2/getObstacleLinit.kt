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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.util.rangeTo

import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay

@Composable
fun GetLimit(){
    extent.value= 10
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
    val randomWord by limitViewModel.word
    val theme by limitViewModel.theme
    val targetSize = if (count.value == 1) 180.dp else 120.dp
    val animatedSize by animateDpAsState(
        targetValue = targetSize,
        animationSpec = tween(durationMillis = 400)
    )
    LaunchedEffect(fetchAgain.value, fetchCount.value) {
        delay(8000L)
        if (((fetchAgain.value && fetchCount.value ==1 && gameContinue.value) || fetchCount.value == 0) ) {
            reFetch.value=1
            centreObstale.value=300f
            limitViewModel.getObstacleCourseAgain()

        }

    }
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
    if(course.loading){
        check.value=1
    }
    else{
        check.value=0
    }

    if(imageObstacleState.bitmap!=null && imageObstacleState.error==null){
        var j=0
            for (i in path) {
                when (i.lowercase()) {
                    "m" -> {
                        Image(
                            bitmap = imageObstacleState.bitmap!!.asImageBitmap(),
                            contentDescription = null,
                            modifier = Modifier
                                .offset(obstacleX[0], (centreObstale.value - (150 * (2 * j+ 1))).dp)
                                .size(100.dp)
                        )
                        xCord.value= obstacleX[0]
                    }
                    "l" -> {
                        Image(
                            bitmap = imageObstacleState.bitmap!!.asImageBitmap(),
                            contentDescription = null,
                            modifier = Modifier
                                .offset(obstacleX[1], (centreObstale.value - (400 *j)).dp)
                                .size(100.dp)
                        )
                        xCord.value= obstacleX[1]
                    }
                    "r" -> {
                        Image(
                            bitmap = imageObstacleState.bitmap!!.asImageBitmap(),
                            contentDescription = null,
                            modifier = Modifier
                                .offset(obstacleX[2], (centreObstale.value - (300 * j)).dp)
                                .size(100.dp)
                        )
                        xCord.value= obstacleX[2]
                    }
                    "b"->{
                        Image(painter = trapNew, contentDescription =null,
                            modifier = Modifier
                                .size(60.dp)
                                .offset(x = 0.dp, y = trapYCoord.value.dp))

                    }
                    ""->{
                        if (gameContinue.value){
                            fetchCount.value=1
                            fetchAgain.value=true

                        }
                    }

                }
                if(j<=100){
                j++}
            }

        }
    if(rewardPunish.error==null){
            hitHindrance(hindrance =HitHindrance(rewardPunish.type,rewardPunish.amount,rewardPunish.process))
        }

    if(course.error==null){
        obstacleCourse(obstacleCourseResponse = ObstacleCourseResponse(course.course))
    }
    if(theme.error==null){
        theme(themeResponse = ThemeResponse(theme.theme))
    }

}


fun obstacle(obstacle: Obstacle){
    maxCollision.value=obstacle.obstacleLimit
}

fun hitHindrance(hindrance: HitHindrance){
    type.value=hindrance.type
    amount.value=hindrance.amount
    description.value=hindrance.description
}

fun obstacleCourse(obstacleCourseResponse: ObstacleCourseResponse){
    for(i in 0..<100){
            if(i< extent.value) {
                path[i] = (obstacleCourseResponse.obstacleCourse?.get(i).toString())
            }

    }
}


fun randomWord(randomWordResponse: RandomWordResponse){
    RandomWord.value=randomWordResponse.word.toString()
}

fun theme(themeResponse: ThemeResponse){
    Theme.value=themeResponse.theme
}


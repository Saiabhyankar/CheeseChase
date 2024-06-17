package com.example.delta_task2

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.delay

@Composable
fun ObstacleMove(){
    var delayTime=10L
    var moveStepTom=10f
    var targetObstacle=550f
    LaunchedEffect(gameContinue.value) {
        if (gameContinue.value ) {
            delay(500L)
            while (centreObstale.value <= targetObstacle && gameContinue.value) {
                centreObstale.value += moveStepTom
                delay(delayTime)
                checkCollision()
                if (centreObstale.value == targetObstacle) {
                    targetObstacle *= 2
                    if (delayTime > 6) {
                        delayTime -= 1
                    }
                }
            }
        }
    }
}
package com.example.delta_task2

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.delay

@Composable
fun ObstacleMove(){

    var moveStepTom=10f

    LaunchedEffect(gameContinue.value) {
        delay(550L)

            while (centreObstale.value <= targetObstacle.value && gameContinue.value) {
                centreObstale.value += moveStepTom
                delay(delayObstacle.value)
                checkCollision()
                if (centreObstale.value == targetObstacle.value) {
                    targetObstacle.value *= 2
                    if (delayObstacle.value >=5) {
                        delayObstacle.value -= 1
                    }
                }
            }
        }
    }

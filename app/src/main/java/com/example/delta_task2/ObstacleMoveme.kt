package com.example.delta_task2

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun ObstacleMove(){

    var moveStepTom=10f

    keyCollectionPower()
    LaunchedEffect(gameContinue.value) {
        delay(550L)
            while (centreObstale.value <= targetObstacle.value && gameContinue.value) {
                centreObstale.value += moveStepTom
                trapYCoord.value += 5f
                delay(delayObstacle.value)
                checkCollision()

                if (centreObstale.value == targetObstacle.value) {
                    targetObstacle.value *= 2
                    if (delayObstacle.value >= 2) {
                        delayObstacle.value -= 1
                    }
                }
            }
        }
    }

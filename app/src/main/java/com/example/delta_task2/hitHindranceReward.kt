package com.example.delta_task2

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun rewardPunish() {
    val coroutineScope = rememberCoroutineScope()
    if (hitHindranceCheck.value) {
        if (type.value == 1) {
            delayObstacle.value /= ((amount.value) / 2)
        }
        else if (type.value == 2) {
            if(centreJerry.value- centreObstale.value<50f &&((track.value==0 && xCord.value==-125.dp)||(track.value==1 && xCord.value==0.dp)||(track.value==2 && xCord.value==120.dp))) {
                LaunchedEffect(type.value) {
                    coroutineScope.launch {
                        for (i in 0 until amount.value) {
                            count.value = 1
                            Jump()
                            delay(1000L)
                            count.value = 0
                        }
                    }
                }
            }
        }
        else if (type.value == 3) {
            if (amount.value != 0) {
                if (!changed.value) {
                    dist.value = centreTom.value - centreJerry.value
                    val stepDistance = dist.value / amount.value
                    centreTom.value -= stepDistance
                    if (centreTom.value <= centreJerry.value+50f) {
                        centreTom.value = centreJerry.value+50f
                        counter.value = maxCollision.value
                    }
                    changed.value=true
                }
            }
        }
    }
}

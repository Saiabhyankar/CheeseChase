package com.example.delta_task2

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
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
            LaunchedEffect(true) {
                coroutineScope.launch {
                    count.value = 0
                    for (i in amount.value until 0) {
                        Jump()
                        delay(1000L)
                        count.value = 1
                    }
                }
            }
        } else if (type.value == 3) {
            // Ensure amount.value is not zero to avoid division by zero
            if (amount.value != 0) {
                if (!changed.value) {
                    dist.value = centreTom.value - centreJerry.value
                    // Calculate the distance to reduce in each step
                    val stepDistance = dist.value / amount.value

                    // Move Tom towards Jerry step by step

                    centreTom.value -= stepDistance
                    // Check if Tom has reached or crossed Jerry
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

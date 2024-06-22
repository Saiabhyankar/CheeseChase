package com.example.delta_task2

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay


@Composable
fun ShieldMove(){
    var shield= painterResource(id = R.drawable.shield)
    var shieldProb= listOf(1,2,3,4,5).random()
    var shieldX = remember{
        mutableStateOf(0f)
    }
    fun checkShield() {

        if (shieldY.value+60f== centreJerry.value) {
            shieldY.value = centreJerry.value -60f
            shieldCollided.value = true
        }

    }
    if (track.value == 0 && initialTrack.value == 1) {
        shieldX.value = -130f

    } else if (track.value == 2 && initialTrack.value == 1) {
        shieldX.value = 130f
    } else if (track.value == 1) {
        shieldX.value = 0f
    }
 val animatedXc by animateFloatAsState(
        targetValue = shieldX.value,
        animationSpec = tween(durationMillis = 100)
    )
    LaunchedEffect(gameContinue.value) {
        delay(1000)
        while (gameContinue.value && shieldY.value <= shieldTargetY.value && !shieldCollided.value) {
            shieldY.value += 10f
            delay(10)

            checkShield()
        }
    }
    Column {
            if (!shieldDisappear.value) {
                    Image(
                        painter = shield, contentDescription = null,
                        modifier = Modifier.size(60.dp)
                            .offset(
                                x=if(shieldCollided.value)
                                    animatedXc.dp
                                else
                                    0.dp,
                                y = (shieldY.value).dp)
                    )

            }
        }
    }


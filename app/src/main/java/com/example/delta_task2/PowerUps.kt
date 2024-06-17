package com.example.delta_task2

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun PowerUps(){
    var powerUps= painterResource(id = R.drawable.powerups)
    var targetPowerUp by remember {
        mutableStateOf(1440f)
    }
    LaunchedEffect(gameContinue.value, gamePause.value) {
        delay(1000)
        while(powerUp.value<=targetPowerUp && !gamePause.value){
            powerUp.value+=10f
            delay(50L)

        }
    }
    Image(painter = powerUps, contentDescription = "PowerUp",
        modifier = Modifier
            .offset(y=powerUp.value.dp)
            .size(55.dp))
    if(powerUp.value== centreJerry.value && track.value==1){
        gamePause.value=true
    }
}
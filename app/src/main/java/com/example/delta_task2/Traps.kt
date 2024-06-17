package com.example.delta_task2

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

import kotlinx.coroutines.delay

@Composable
fun Trap(){
    fun checkTrapCollision(){
        if (((centreJerry.value == trapYCoord.value+50f && track.value==1)||(centreJerry.value == trapYCoord.value+150f && track.value==0)) && conditionCheck.value==0) {
            gamePause.value = true
        }

        if (gamePause.value) {
            conditionCheck.value=1
            centreTom.value=150f
            gamePause.value=false
        }
    }
    var trap= painterResource(id = R.drawable.traps)
    var delayTime=45L

    LaunchedEffect(gameContinue.value) {
            delay(500L)
            while(trapYCoord.value<=targetTrap.value&& gameContinue.value){
                trapYCoord.value+=10f
                if(targetTrap.value== trapYCoord.value){
                    targetTrap.value*=2
                    delayTime-=1
                }
                delay(delayTime)
                checkTrapCollision()
            }
    }
    Column(){
        Image(painter = trap, contentDescription ="Trap",
            modifier= Modifier
                .size(60.dp)
                .offset(y = trapYCoord.value.dp))
        Image(painter = trap, contentDescription = "Trap",
            modifier = Modifier.size(60.dp)
                .offset (-130.dp, (trapYCoord.value+100).dp))
    }

}


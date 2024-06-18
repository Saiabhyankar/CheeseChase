package com.example.delta_task2

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

import kotlinx.coroutines.delay

@Composable
fun Trap(){
    fun checkTrapCollision(){
        for( i in 0..2) {
            if ((((centreJerry.value == (trapYCoord.value - 650f - (500 * 4* i).toFloat()) && track.value == 1) || (centreJerry.value == trapYCoord.value + 10f - (400 * 3* i).toFloat()) && track.value == 0)) && conditionCheck.value == 0) {
                gamePause.value = true
                counter.value += 1
            }
            if((trapYCoord.value - 650f - (500 * 4* i).toFloat()== centreObstale.value+100f)||(trapYCoord.value + 30f - (400 * 3* i).toFloat()== centreObstale.value)){
                trapYCoord.value+=50f
            }
        }

        if (gamePause.value) {
            conditionCheck.value=1
            centreTom.value=150f
            gamePause.value=false
        }
    }
    var trap= painterResource(id = R.drawable.traps)
    var delayTime=30L

    LaunchedEffect(gameContinue.value) {
                delay(300L)
            while(trapYCoord.value<=targetTrap.value&& gameContinue.value){
                delay(0L)
                trapYCoord.value+=10f
                checkTrapCollision()
                delay(delayTime)
                if(targetTrap.value== trapYCoord.value){
                    targetTrap.value*=2
                }
                checkTrapCollision()
            }
    }
    Column(){
        for(i in 0..2) {
            Image(
                painter = trap, contentDescription = "Trap",
                modifier = Modifier
                    .size(60.dp)
                    .offset(y = (trapYCoord.value - 500 - (500 * 4* i)).dp)
            )
            Image(
                painter = trap, contentDescription = "Trap",
                modifier = Modifier
                    .size(60.dp)
                    .offset(-130.dp, (trapYCoord.value + 100 - (400 * 3* i)).dp)
            )
            Image(
                painter = trap, contentDescription = "Trap",
                modifier = Modifier
                    .size(60.dp)
                    .offset(130.dp, y = (trapYCoord.value - 500 - (600 *2*i)).dp)
            )
        }
    }

}


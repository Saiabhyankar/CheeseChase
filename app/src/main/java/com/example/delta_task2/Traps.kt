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
fun Trap(){
    fun checkTrapCollision(){
        val numRandom= listOf(1,2,3).random()
        for( i in 0..1) {
            if( ((((centreJerry.value == (trapYCoord.value - (550-(250*i)).toFloat()- (500 * 4* i).toFloat()) && track.value == 1) ||
                        (centreJerry.value == trapYCoord.value + ((100)*(i+1.5)).toFloat() - (400 * 3* i).toFloat()) && track.value == 0)||
                        (centreJerry.value==trapYCoord.value - (400-(i*200)) - (600 *2*i)) && track.value==2)) && conditionCheck.value==0 && !shieldCollided.value) {
                gamePause.value = true
                if(numRandom==2 || numRandom==3) {
                    counter.value += 1
                }
                else{
                    counter.value=2
                }

            }
        }

        if (gamePause.value) {
            conditionCheck.value=1
            centreTom.value=150f
            gamePause.value=false
        }
    }
    var trap= painterResource(id = R.drawable.traps)
    LaunchedEffect(gameContinue.value) {
        while (gameContinue.value){

            checkTrapCollision()

            delay(10L)
        }
    }
    checkTrapCollision()
    Column(){
        for(i in 0..1) {
            Image(
                painter = trap, contentDescription = "Trap",
                modifier = Modifier
                    .size(60.dp)
                    .offset(y = (trapYCoord.value - 500 - (500 * 4 * i)).dp)
            )
            Image(
                painter = trap, contentDescription = "Trap",
                modifier = Modifier
                    .size(60.dp)
                    .offset(-130.dp, (trapYCoord.value + 100 - (400 * 3 * i)).dp)
            )
            Image(
                painter = trap, contentDescription = "Trap",
                modifier = Modifier
                    .size(60.dp)
                    .offset(130.dp, y = (trapYCoord.value - 500 - (600 * 2 * i)).dp)
            )
        }
    }

}
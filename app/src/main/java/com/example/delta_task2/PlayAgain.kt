package com.example.delta_task2

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PlayAgain(){
    Button( onClick = {
        gameContinue.value=true
        counter.value=0
        centreTom.value=300f
        centreObstale.value=120f
        centreJerry.value=120f
        track.value=1
        initialTrack.value=1
        count.value=0
        isJump.value=true
        counterUpdated.value=false
        xc.value=0f
        jumpTrack.value=false
        gameScore.value=0
        jumpCounter.value=0
    },
        modifier = Modifier
            .size(height=60.dp,width=180.dp)){
        Text("Play Again",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
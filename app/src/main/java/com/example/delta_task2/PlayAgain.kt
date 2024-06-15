package com.example.delta_task2

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

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

    }){
        Text("Play Again")
    }
}
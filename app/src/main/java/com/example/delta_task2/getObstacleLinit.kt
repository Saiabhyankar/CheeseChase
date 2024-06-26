package com.example.delta_task2

import android.util.Log


import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue

import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun GetLimit(){
    val limitViewModel:ApiInteraction= viewModel()
    val viewState1 by  limitViewModel.obstacleCount
    val viewState2 by  limitViewModel.imageTomAddress
    if(viewState1.error==null){
        obstacle(Obstacle(viewState1.limit))
    }
    if(viewState2.error==null){
        imageT(image = ImageTom(viewState2.address))
    }
}
fun obstacle(obstacle: Obstacle){
    maxCollision.value=obstacle.obstacleLimit
}
fun imageT(image:ImageTom){
    tomImageAddress.value=image.tom
}
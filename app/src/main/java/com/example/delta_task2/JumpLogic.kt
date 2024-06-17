package com.example.delta_task2

fun Jump(){
    for(i in 1..100){
        if (track.value == 1 && centreObstale.value - (350 * (2 * i + 1)).toFloat()+100f - centreJerry.value > 40f && count.value == 1) {
            count.value = 1

        } else if (track.value == 1 && centreObstale.value - (350 * (2 * i + 1)).toFloat()+100f - centreJerry.value > -30f && count.value == 1) {
            count.value = 0
            isJump.value=false
        }
        if (track.value == 0 && centreObstale.value - (700 * i).toFloat()+100f - centreJerry.value > 40f && count.value == 1) {
            count.value = 1

        } else if (track.value == 0 && centreObstale.value - (700 * i).toFloat()+100f - centreJerry.value > -30f && count.value == 1) {
            count.value = 0
            isJump.value = false
        }
        if (track.value == 2 && centreObstale.value - (700 * i).toFloat()+100f - centreJerry.value > 40f && count.value == 1) {
            count.value = 1

        } else if (track.value == 2 && centreObstale.value - (700 * i).toFloat()+100f - centreJerry.value > -30f && count.value == 1) {
            count.value = 0
            isJump.value = false
        }
    }
}
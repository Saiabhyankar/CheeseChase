package com.example.delta_task2

fun checkCollision(){
    for(i in 1..100) {
        if (track.value == 0 && (centreJerry.value == centreObstale.value - (700 * i).toFloat()+100f) && i % 2 == 0 && !counterUpdated.value && gameContinue.value) {
            counter.value += 1
            counterUpdated.value = true
        } else if (track.value == 1 && (centreJerry.value == centreObstale.value - (350 * (2 * i + 1)).toFloat()+100f) && (i%2==0||i % 3 == 0) && !counterUpdated.value && gameContinue.value) {
            counter.value += 1
            counterUpdated.value = true
        } else if (track.value == 2 && (centreJerry.value == centreObstale.value - (700 * i).toFloat()+100f)&& i%2==0 && !counterUpdated.value && gameContinue.value) {
            counter.value += 1
            counterUpdated.value = true
        }
    }
}
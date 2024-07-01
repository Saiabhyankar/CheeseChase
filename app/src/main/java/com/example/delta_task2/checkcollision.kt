package com.example.delta_task2

fun checkCollision(){
    var j=0
    for(i in path) {
        if ((i.lowercase()=="l"&& track.value==0) && (centreJerry.value == centreObstale.value - (400 * j).toFloat()+100f)  && !counterUpdated.value && gameContinue.value &&!shieldCollided.value) {
            counter.value += 1
            counterUpdated.value = true
        } else if ((i.lowercase()=="m"&& track.value==1) && (centreJerry.value == centreObstale.value - (150 * (2 * j + 1)).toFloat()+100f) &&  !counterUpdated.value && gameContinue.value && !shieldCollided.value) {
            counter.value += 1
            counterUpdated.value = true
        } else if ((i.lowercase()=="r" && track.value == 2 )&& (centreJerry.value == centreObstale.value - (300 * j).toFloat()+100f) && !counterUpdated.value && gameContinue.value && !shieldCollided.value) {
            counter.value += 1
            counterUpdated.value = true
        }
        j+=1
    }
}
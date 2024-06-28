package com.example.delta_task2

fun rewardPunish(){
    if (hitHindranceCheck.value){
        if(type.value==1){

        }
        else if(type.value==2){
            count.value=1
            for(i in 0..<amount.value){
                Jump()
            }
        }
    }
}
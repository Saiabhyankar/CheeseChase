package com.example.delta_task2

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun keyCollectionPower(){
    if(track.value==1 && (centreJerry.value== centreObstale.value-1000 )){
        keyCount.value+=1
    }
    else if (track.value==0 && (centreJerry.value== centreObstale.value-500)){
        keyCount.value+=1
    }
    else if (track.value==2 && (centreJerry.value== centreObstale.value-1200)){
        keyCount.value+=1
    }
    writeToSharedPref(LocalContext.current,"keycount", keyCount.value.toString(),"PowerUp")
    if(keyCount.value>=1){
        powerUpUse.value=1
        continueButton.value=true
    }
}



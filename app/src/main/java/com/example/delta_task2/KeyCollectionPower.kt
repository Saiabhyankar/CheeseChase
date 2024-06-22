package com.example.delta_task2

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.delay


@Composable
fun keyCollectionPower() {
    if (track.value == 1 && (centreJerry.value == centreObstale.value - 1000)) {
        keyCount.value += 1
        //keyCollected[1] = true
        shieldY.value+=10f
    } else if (track.value == 0 && (centreJerry.value == centreObstale.value - 500)) {
        keyCount.value += 1
        //keyCollected[0] = true
    } else if (track.value == 2 && (centreJerry.value == centreObstale.value - 1500)) {
        keyCount.value += 1
        //keyCollected[2] = true
    }
    writeToSharedPref(LocalContext.current, "keycount", keyCount.value.toString(), "PowerUp")
    if (keyCount.value >= 1) {
        continueButton.value = true
    }
}




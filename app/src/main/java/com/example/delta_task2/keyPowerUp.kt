package com.example.delta_task2

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun KeyPowerUp(){
    var key= painterResource(id = R.drawable.img_2)
    Column(){
        Image(painter = key, contentDescription = "PowerKey",
            modifier = Modifier.size(60.dp)
                .offset(y= (centreObstale.value-1000f).dp))
        Image(painter = key, contentDescription = "PowerKey",
            modifier = Modifier.size(60.dp)
                .offset(x=-130.dp,y= (centreObstale.value-500f).dp))
        Image(painter = key, contentDescription = "PowerKey",
            modifier = Modifier.size(60.dp)
                .offset(x=130.dp,y= (centreObstale.value-1200f).dp))
    }
}
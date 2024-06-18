package com.example.delta_task2

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun Help(){
    var painter= painterResource(id = R.drawable.help_icon)
    Column (modifier = Modifier.fillMaxSize()){
        Button(onClick={
                       helpClick.value=true
        },
            modifier = Modifier
                .size(height = 60.dp,width=60.dp)
                .offset (x=250.dp,y=-500.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White)){

        }
        Image(painter = painter, contentDescription = "Help",
            modifier = Modifier
                .size(40.dp)
                .offset(259.dp, -550.dp))
    }
}
package com.example.delta_task2

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun writeHighScore(){
    var score= readFromSharedPreferences(LocalContext.current,"HighScore","Cheese Chase")
    Button(onClick = { highScoreAlert.value=true},
        modifier = Modifier
            .offset(65.dp,140.dp)
            .size(height=60.dp,width=150.dp)
            .border(8.dp, color = Color(29, 212, 94), shape = RoundedCornerShape(100))) {
        Text("High Score",
            fontSize = 20.sp,
            color = Color.Black)
    }
    if(highScoreAlert.value){
        AlertDialog(onDismissRequest = { highScoreAlert.value=false }, confirmButton = { /*TODO*/ },
            modifier = Modifier.size(200.dp),
            text = {
                Column (){
                    Row(){
                        Text("Winner")
                        Spacer(modifier =Modifier.padding(16.dp) )
                        Text("Score")
                    }
                    Spacer(modifier = Modifier.padding(32.dp))
                    Row(){
                        Text("JERRY")
                        Spacer(modifier = Modifier.padding(16.dp))
                        Text(score)
                    }
                }
            })
    }
}

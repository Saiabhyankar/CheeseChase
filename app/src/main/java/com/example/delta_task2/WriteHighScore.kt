package com.example.delta_task2

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun writeHighScore(){
    var score= readFromSharedPreferences(LocalContext.current,"HighScore","Cheese Chase")
    Button(onClick = { highScoreAlert.value=true}) {
        Text("High Score")
    }
    if(highScoreAlert.value){
        AlertDialog(onDismissRequest = { highScoreAlert.value=false }, confirmButton = { /*TODO*/ },
            modifier = Modifier.size(400.dp),
            text = {
                Column (){
                    Row(){
                        Text("Winner ")
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

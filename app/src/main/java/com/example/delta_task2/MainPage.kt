package com.example.delta_task2

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainPage(navigateToGame:()->Unit){
    var image1 = painterResource(id = R.drawable.main1)
    Surface(
        color = Color(54,173,207,255),
        modifier = Modifier.fillMaxWidth()
    ){}
    Box (modifier = Modifier
        .padding(50.dp)
        .size(350.dp)
        .offset(10.dp, 200.dp)
    ){
        Column(Modifier.fillMaxSize()) {

            Image(painter = image1, contentDescription = "GameTheme",
                modifier = Modifier
                    .size(250.dp))
            writeHighScore()
        }
    }
    Box(contentAlignment = Alignment.Center,
        modifier= Modifier
            .padding(100.dp)
            .size(500.dp)){
        Column (){
            Text("Cheese Chase",
                fontStyle = FontStyle.Italic,
                fontSize = 24.sp,
                modifier = Modifier
                    .offset(0.dp,-250.dp)
            )
            Button(onClick = { navigateToGame() },
                modifier = Modifier
                    .offset(-5.dp, 300.dp)
                    .size(height = 60.dp, width = 160.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Magenta) ) {
                Text("Start",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold)
            }
        }
    }

}
package com.example.delta_task2

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


import kotlinx.coroutines.delay

@Composable
fun mainGame(navigate:()->Unit){
    val customFontFamily = FontFamily(
        Font(R.font.cavet, FontWeight.Normal),
        Font(R.font.cavet, FontWeight.Bold)
    )
    var painter1= painterResource(id = R.drawable.tomwin)
    var painter2= painterResource(id = R.drawable.jerrywin)
    var initialRadius=50f

    if(track.value==0 && initialTrack.value==1 ){
        xc.value=-375f

    }
    else if(track.value==2 && initialTrack.value==1 ){
        xc.value=375f
    }
    else if(track.value==1){
        xc.value=0f
    }
    val delayTime = 10L
    var moveStepJerry=100f
    var targetJerry =-50f
    LaunchedEffect(gameContinue.value) {
        delay(1000L)
        while (centreJerry.value >= targetJerry && gameContinue.value ) {
            centreJerry.value -= moveStepJerry
            delay(delayTime)
        }
    }
    val moveStepTom=10f
    var targetTom =750f
    var targetObstacle =550f
    LaunchedEffect(gameContinue.value) {
        delay(1000L)
        while (centreTom.value <= targetTom && gameContinue.value&& counter.value<=1) {
            centreTom.value+= moveStepTom
            delay(delayTime)
            if(centreTom.value==targetTom && gameContinue.value && counter.value<=1){
                targetTom*=2
            }
        }
    }
    LaunchedEffect(gameContinue.value) {
        delay(1000L)
        while (centreObstale.value<= targetObstacle && gameContinue.value) {
            centreObstale.value+= moveStepTom
            delay(delayTime)
            if(centreObstale.value==targetObstacle){
                targetObstacle*=2
            }
        }

    }
    val targetRadius = if (count.value == 1) 100f else initialRadius
    val animatedRadius by animateFloatAsState(
        targetValue = targetRadius,
        animationSpec = tween(durationMillis = 200)
    )
    Surface(
        color = Color(54,173,207,255),
        modifier = Modifier.fillMaxWidth()
    ){}

    Box(modifier = Modifier
        .fillMaxSize()){

        LazyVerticalGrid(columns =GridCells.Fixed(3) ,
            modifier = Modifier.fillMaxSize()) {
            items(3) {
                Box(modifier = Modifier
                    .fillMaxHeight()
                    .height(1000.dp))
                {
                    Button(onClick = {
                        initialTrack.value=track.value
                        track.value=it
                        if(initialTrack.value== track.value){
                            count.value=1
                        }
                        else{
                            count.value=0
                        }
                    },
                        modifier = Modifier
                            .height(5000.dp)
                            .width(100.dp)
                            .offset(x = 10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(238,244,241,255)),
                        shape = RectangleShape) {
                    }

                }
            }
        }
    }
    Box (modifier = Modifier
        .fillMaxHeight()
        .padding(50.dp)
        .offset(y = 200.dp),
        contentAlignment = Alignment.Center){

        Canvas(modifier = Modifier
            .padding(1.dp)) {
            drawCircle(
                color = Color.Red,
                radius = animatedRadius,
                center = Offset(xc.value,centreJerry.value)
            )
            drawCircle(
                color = Color.Black,
                radius = 90f,
                center = Offset(xc.value,centreTom.value
                )
            )
            for(i in 1..100) {
                drawRect(
                    color = Color.Blue,
                    topLeft = Offset(-50f, centreObstale.value- (350*(2*i +1)).toFloat()),
                    size = Size(100f, 100f),
                )
                if(i%2==0) {
                    drawRect(
                        color = Color.Blue,
                        topLeft = Offset(-425f, centreObstale.value - (700 * i).toFloat()),
                        size = Size(100f, 100f),
                    )
                }
                drawRect(
                    color = Color.Blue,
                    topLeft = Offset(320f, centreObstale.value - (700*i).toFloat()),
                    size = Size(100f, 100f),
                )
                if(track.value==0 && (centreJerry.value == centreObstale.value - (700*i).toFloat()) && i%2==0 && !counterUpdated.value && gameContinue.value ){
                    counter.value+=1
                    counterUpdated.value=true
                }
                else if(track.value==1 && (centreJerry.value== centreObstale.value - (350*(2*i +1)).toFloat()) && !counterUpdated.value && gameContinue.value){
                    counter.value+=1
                    counterUpdated.value=true
                }
                else if(track.value==2 && (centreJerry.value==centreObstale.value - (700*i).toFloat()) && !counterUpdated.value && gameContinue.value ){
                    counter.value+=1
                    counterUpdated.value=true
                }
            }
        }
        if(counter.value==1 && counterUpdated.value){
            centreTom.value=300f
            gameContinue.value=true
            if(counterUpdated.value==true){
                counterUpdated.value=false
            }
        }
        else if(counter.value==2){
            centreTom.value=centreJerry.value+50f
            gameContinue.value=false
            AlertDialog(onDismissRequest = { /*TODO*/ }, confirmButton = { /*TODO*/ },
                text = {
                    Column(){
                        Text(text = "GameOver\nTom wins")
                        Image(painter = painter1, contentDescription = "tomwin",
                            modifier= Modifier
                                .size(height = 80.dp, width = 100.dp)
                                .offset(80.dp, 0.dp))
                        Box {
                            PlayAgain()
                        }
                    }


                })
        }
        else if(centreTom.value-centreJerry.value>4000f){
            gameContinue.value=false
            AlertDialog(onDismissRequest = { /*TODO*/ }, confirmButton = { /*TODO*/ },
                text = {
                    Column(){
                        Text(text = "GameOver\nJerry wins",
                            fontFamily = customFontFamily
                        )
                        Image(painter = painter2, contentDescription = "jerryWin",
                            modifier= Modifier
                                .size(height = 80.dp, width = 100.dp)
                                .offset(80.dp, 0.dp))
                        Box {
                            PlayAgain()
                        }
                    }

                })
        }

        Column(modifier = Modifier
            .offset(250.dp, -250.dp)
            .size(height = 80.dp, width = 180.dp)){
            Card(shape = RoundedCornerShape(10.dp)){
                Text(text = (counter.value).toString())
            }
        }
    }
}

package com.example.delta_task2

import android.hardware.SensorManager
import android.media.MediaPlayer

import androidx.compose.animation.core.animateFloatAsState

import androidx.compose.animation.core.tween

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button

import androidx.compose.material3.Card

import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf

import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.graphics.drawscope.drawIntoCanvas

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter


import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun mainGame(navigate:()->Unit) {

    val customFontFamily = FontFamily(
        Font(R.font.cavet, FontWeight.Normal),
        Font(R.font.cavet, FontWeight.Bold)
    )
    val coroutineScope= rememberCoroutineScope()

    val keys= readFromSharedPreferences(LocalContext.current,"keycount","PowerUp")
    val painter1 = painterResource(id = R.drawable.tomwin)
    val painter2 = painterResource(id = R.drawable.jerrywin)
    val initialRadius = 50f
    val context1 = LocalContext.current
    val context2 = LocalContext.current
    // var context3= LocalContext.current
    val jumpSound = remember { MediaPlayer.create(context1, R.raw.jump_jerry) }
    val hitSound = remember {
        MediaPlayer.create(context2, R.raw.obstacle_hit)
    }
    if (track.value == 0 && initialTrack.value == 1) {
        xc.value = -375f

    } else if (track.value == 2 && initialTrack.value == 1) {
        xc.value = 375f
    } else if (track.value == 1) {
        xc.value = 0f
    }



    LaunchedEffect(gameContinue.value) {
        while (gameContinue.value && !gamePause.value) {
            delay(1000L)
            gameScore.value += 15
        }
    }
    LaunchedEffect(gameContinue.value) {
        delay(400L)
        while (centreJerry.value<=80f){
            centreJerry.value-=10f
        }
    }
    var delayTime = 10L
    val moveStepTom = 10f
    var targetTom = 750f

    LaunchedEffect(gameContinue.value) {
        delay(1000L)
        while (centreTom.value <= targetTom && gameContinue.value && counter.value <= 1 ) {
            centreTom.value += moveStepTom
            delay(delayTime)
            if (centreTom.value == targetTom && gameContinue.value && counter.value <= 1) {
                targetTom *= 2
            }
        }
    }
    val context = LocalContext.current
    val gyroscope = remember { AndroidGyroscope(context) }
    var gyroscopeData by remember { mutableStateOf(GyroscopeData(0f, 0f, 0f)) }
    val targetRadius = if (count.value == 1) 100f else initialRadius
    val animatedRadius by animateFloatAsState(
        targetValue = targetRadius,
        animationSpec = tween(durationMillis = 400)
    )
    val animatedXc by animateFloatAsState(
        targetValue = xc.value,
        animationSpec = tween(durationMillis = 100)
    )

    Surface(
        color = Color(54, 173, 207, 255),
        modifier = Modifier.fillMaxWidth()
    ) {}

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.fillMaxSize()
        ) {
            items(3) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .height(1000.dp)
                )
                {
                    Canvas(
                        modifier = Modifier
                            .height(5000.dp)
                            .width(100.dp)
                            .offset(x = 10.dp)
                            .background(Color.White, shape = RoundedCornerShape(8.dp))
                            .clickable {
                                initialTrack.value = track.value
                                track.value = it
                                if (initialTrack.value == track.value) {
                                    count.value = 1
                                } else {
                                    count.value = 0
                                }
                            }
                    ) {
                        drawIntoCanvas {
                            drawRect(
                                color = Color.White ,
                                size = size
                            )
                        }
                    }
                }
            }
        }
    }
    Jump()
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .padding(50.dp)
            .offset(y = 200.dp),
        contentAlignment = Alignment.Center
    ) {
        DisposableEffect(gameContinue.value) {
            val listener: (GyroscopeData) -> Unit = { data ->
                gyroscopeData = data
                val threshold = 5f
                val scaledX = gyroscopeData.x * 10

                when {
                    scaledX < -threshold && track.value == 1 -> {

                        track.value = 2
                        initialTrack.value=1
                    }
                    scaledX > threshold && track.value == 1 -> {

                        track.value = 0
                        initialTrack.value=1
                    }
                    (scaledX < -threshold && track.value==0)||(scaledX>threshold && track.value==2) -> {

                        track.value = 1
                        if(scaledX<-threshold){
                            initialTrack.value=0
                        }
                        else if(scaledX > threshold){
                            initialTrack.value=2
                        }

                    }
                    else -> {
                        when (track.value) {
                            0 -> xc.value = -375f
                            2 -> xc.value = 375f
                            else -> xc.value = 0f
                        }
                    }
                }
            }

            gyroscope.startListening(listener)
            onDispose {
                gyroscope.stopListening()
            }
        }
        ObstacleMove()
        KeyPowerUp()
        Image(painter = rememberAsyncImagePainter(tomImageAddress), contentDescription = null,
            modifier = Modifier.size(60.dp)
                .aspectRatio(1f))
        if(conditionCheck.value==0) {
            Trap()
        }
        Canvas(
            modifier = Modifier
                .padding(1.dp)
        ) {

            for (i in 1..100) {
                if(i%2==0||i%3==0 ) {
                    drawRect(
                        color = Color.Blue,
                        topLeft = Offset(-50f, centreObstale.value - (350 * (2 * i + 1)).toFloat()),
                        size = Size(100f, 100f),
                    )
                }
                if (i % 2 == 0) {
                    drawRect(
                        color = Color.Blue,
                        topLeft = Offset(-425f, centreObstale.value - (700 * i).toFloat()),
                        size = Size(100f, 100f),
                    )
                }
                if(i%2==0) {
                    drawRect(
                        color = Color.Blue,
                        topLeft = Offset(320f, centreObstale.value - (700 * i).toFloat()),
                        size = Size(100f, 100f),
                    )
                }

            }

            drawCircle(
                color = Color.Red,
                radius = animatedRadius,
                center = Offset(animatedXc, centreJerry.value)
            )
//            drawCircle(
//                color = Color.Black,
//                radius = 90f,
//                center = Offset(
//                    animatedXc, centreTom.value
//                )
//            )

        }
        GetLimit()
        ShieldMove()
        if(!isJump.value){
            jumpCounter.value=1
        }
        jumpTrack.value= !isJump.value
        if(!isJump.value){
            if(counter.value>=1 && jumpTrack.value) {
                counter.value -= 1
                jumpTrack.value = false
                isJump.value = true
                counterUpdated.value=false
            }
        }
        if (shieldCollided.value) {
            shieldTimeRemaining.value = 5
            LaunchedEffect(gameContinue.value) {
                coroutineScope.launch {
                    for (i in 5 downTo 1) {
                        delay(1000)
                        shieldTimeRemaining.value = i - 1
                    }
                    if(counter.value>=1){
                        counter.value-=1
                    }
                    shieldCollided.value = false
                    shieldDisappear.value=true
                }
            }
            Box(){
                Text(text = "Shield Active")
            }
        }
        if (count.value == 1) {
            jumpSound.start()
        }

        if (counter.value >= 1 && counter.value< maxCollision.value && counterUpdated.value ) {
            if(jumpCounter.value==0){
                hitSound.start()
            }
            if(alreadyCounted.value==0) {
                centreTom.value = 300f
            }
            gameContinue.value = true
            if (counterUpdated.value) {
                counterUpdated.value = false
            }
            alreadyCounted.value=1
        } else if (counter.value == maxCollision.value) {
            hitSound.start()
            centreTom.value = centreJerry.value + 50f
            gameContinue.value = false
            writeToSharedPref(LocalContext.current,"HighScore",gameScore.value.toString(),"Cheese Chase")
            if(!gameContinue.value) {
                AlertDialog(onDismissRequest = { /*TODO*/ }, confirmButton = { /*TODO*/ },
                    modifier = Modifier
                        .size(800.dp),
                    text = {
                        Column {
                            Text(
                                text = "GameOver\n\nTom wins",
                                fontFamily = customFontFamily,
                                modifier = Modifier
                                    .offset(70.dp, 30.dp),
                                fontSize = 32.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(
                                modifier = Modifier
                                    .padding(16.dp)
                            )
                            Image(
                                painter = painter1, contentDescription = "tomWin",
                                modifier = Modifier
                                    .size(height = 120.dp, width = 120.dp)
                                    .offset(80.dp, 0.dp)
                            )
                            Spacer(modifier = Modifier.padding(16.dp))
                            Box(
                                modifier = Modifier
                                    .offset(50.dp, 120.dp)
                                    .size(height = 700.dp, width = 250.dp)
                            ) {
                                Column(Modifier.fillMaxSize()) {
                                    PlayAgain()
                                    Spacer(modifier = Modifier.padding(16.dp))
                                    if((keyCount.value>0 && keyUsed.value==0)||(keyCount.value>1 && keyUsed.value==1)){
                                        if(continueButton.value) {
                                            AlertDialog(
                                                onDismissRequest = { continueButton.value = false },
                                                confirmButton = { /*TODO*/ },
                                                text = {
                                                    Text(
                                                        "You Have $keys Key Left",
                                                        color = Color.Black,
                                                        fontWeight = FontWeight.ExtraBold,
                                                        fontSize = 24.sp
                                                    )
                                                })
                                        }
                                        Button(onClick = {
                                            gameContinue.value=true
                                            counter.value=0
                                            count.value=0
                                            isJump.value=true
                                            counterUpdated.value=false
                                            alreadyCounted.value=0
                                            delayObstacle.value=5L
                                            powerUpUse.value+=1
                                            if(keyUsed.value==0) {
                                                keyCount.value -= 1
                                            }
                                            else{
                                                keyCount.value-=2
                                            }
                                            centreTom.value+=50f
                                            centreJerry.value+=10f
                                            keyUsed.value=1
                                            shieldCollided.value=false
                                            shieldTimeRemaining.value=0
                                        },
                                            modifier = Modifier
                                                .size(height=80.dp,width=180.dp)) {
                                            Text(text = "Continue \n Playing",
                                                fontSize = 20.sp,
                                                fontWeight = FontWeight.Bold)
                                        }
                                    }
                                    Spacer(modifier = Modifier.padding(16.dp))
                                    Button(
                                        onClick = { navigate()
                                            gameContinue.value=true
                                            counter.value=0
                                            centreTom.value=300f
                                            centreObstale.value=120f
                                            centreJerry.value=120f
                                            track.value=1
                                            initialTrack.value=1
                                            count.value=0
                                            isJump.value=true
                                            counterUpdated.value=false
                                            xc.value=0f
                                            jumpTrack.value=false
                                            gameScore.value=0
                                            jumpCounter.value=0
                                            trapYCoord.value=-700f
                                            gamePause.value=false
                                            powerUp.value=-40f
                                            targetTrap.value=1440f
                                            conditionCheck.value=0
                                            alreadyCounted.value=0
                                            targetObstacle.value=1050f
                                            delayObstacle.value=10L
                                            powerUpUse.value=0
                                            keyUsed.value=0
                                            shieldY.value=-1000f
                                            shieldCollided.value=false
                                            shieldTimeRemaining.value=5
                                            shieldDisappear.value=false
                                            for(i in 0..2){
                                                keyCollected[i]=false
                                            }},

                                        modifier = Modifier
                                            .size(height = 60.dp, width = 180.dp)
                                    ) {
                                        Text(
                                            "Home",
                                            fontSize = 24.sp,
                                            fontWeight = FontWeight.Bold
                                        )

                                    }
                                }

                            }
                        }


                    })
            }
        } else if (centreTom.value - centreJerry.value > 10000f) {
            gameContinue.value = false
            writeToSharedPref(LocalContext.current,"HighScore",gameScore.value.toString(),"Cheese Chase")
            if(!gameContinue.value) {
                AlertDialog(onDismissRequest = { /*TODO*/ }, confirmButton = { /*TODO*/ },
                    modifier = Modifier
                        .size(500.dp),
                    text = {
                        Column {
                            Text(
                                text = "GameOver\n\nJerry wins",
                                fontFamily = customFontFamily,
                                modifier = Modifier
                                    .offset(70.dp, -10.dp),
                                fontSize = 40.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(
                                modifier = Modifier
                                    .padding(16.dp)
                            )
                            Image(
                                painter = painter2, contentDescription = "jerryWin",
                                modifier = Modifier
                                    .size(height = 120.dp, width = 120.dp)
                                    .offset(80.dp, -40.dp)
                            )
                            Spacer(modifier = Modifier.padding(16.dp))
                            Box(
                                modifier = Modifier
                                    .offset(50.dp, -20.dp)
                                    .size(height = 500.dp, width = 250.dp)
                            ) {
                                Column(Modifier.fillMaxSize()) {
                                    PlayAgain()
                                    Spacer(modifier = Modifier.padding(16.dp))
                                    Button(
                                        onClick = {
                                            navigate()
                                            gameContinue.value=true
                                            counter.value=0
                                            centreTom.value=300f
                                            centreObstale.value=120f
                                            centreJerry.value=120f
                                            track.value=1
                                            initialTrack.value=1
                                            count.value=0
                                            isJump.value=true
                                            counterUpdated.value=false
                                            xc.value=0f
                                            jumpTrack.value=false
                                            gameScore.value=0
                                            jumpCounter.value=0
                                            trapYCoord.value=-700f
                                            gamePause.value=false
                                            powerUp.value=-40f
                                            targetTrap.value=1440f
                                            conditionCheck.value=0
                                            alreadyCounted.value=0
                                            targetObstacle.value=1050f
                                            delayObstacle.value=10L
                                            powerUpUse.value=0
                                            keyUsed.value=0
                                            shieldY.value=-1000f
                                            shieldCollided.value=false
                                            shieldTimeRemaining.value=5
                                            shieldDisappear.value=false
                                            for(i in 0..2){
                                                keyCollected[i]=false
                                            }
                                        },
                                        modifier = Modifier
                                            .size(height = 60.dp, width = 180.dp)
                                    ) {
                                        Text(
                                            "Home",
                                            fontSize = 24.sp,
                                            fontWeight = FontWeight.Bold
                                        )

                                    }
                                }

                            }
                        }

                    })
            }
        }

        Column(
            modifier = Modifier
                .offset(250.dp, -200.dp)
                .fillMaxSize()
        ) {
            Card(shape = CircleShape,
                modifier = Modifier
                    .size(height = 60.dp, width = 60.dp)
                    .border(3.dp, color = Color(255, 195, 0), shape = CircleShape),

                colors = CardDefaults.cardColors(Color.Black)) {
                Text(text = (check.value).toString(),
                    fontSize = 20.sp,
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold,
                    modifier=Modifier
                        .offset(20.dp,15.dp))

            }
        }
    }
}


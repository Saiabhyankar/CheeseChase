package com.example.delta_task2

import android.media.MediaPlayer
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
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


import kotlinx.coroutines.delay

@Composable
fun mainGame(navigate:()->Unit) {
    val customFontFamily = FontFamily(
        Font(R.font.cavet, FontWeight.Normal),
        Font(R.font.cavet, FontWeight.Bold)
    )
    var painter1 = painterResource(id = R.drawable.tomwin)
    var painter2 = painterResource(id = R.drawable.jerrywin)
    var initialRadius = 50f
    var context1 = LocalContext.current
    var context2 = LocalContext.current
    var jumpSound = remember { MediaPlayer.create(context1, R.raw.jump_jerry) }
    var hitSound = remember {
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
        while (gameContinue.value) {
            delay(1000L)
            gameScore.value += 15
        }
    }
    val delayTime = 10L
    var moveStepJerry = 100f
    var targetJerry = -50f
//    LaunchedEffect(gameContinue.value) {
//        delay(1000L)
//        while (centreJerry.value >= targetJerry && gameContinue.value) {
//            centreJerry.value -= moveStepJerry
//            delay(delayTime)
//        }
//    }
    val moveStepTom = 10f
    var targetTom = 750f
    var targetObstacle = 550f
    LaunchedEffect(gameContinue.value) {
        delay(1000L)
        while (centreTom.value <= targetTom && gameContinue.value && counter.value <= 1) {
            centreTom.value += moveStepTom
            delay(delayTime)
            if (centreTom.value == targetTom && gameContinue.value && counter.value <= 1) {
                targetTom *= 2
            }
        }
    }
    LaunchedEffect(gameContinue.value) {
        delay(1000L)
        while (centreObstale.value <= targetObstacle && gameContinue.value) {
            centreObstale.value += moveStepTom
            delay(delayTime)
            if (centreObstale.value == targetObstacle) {
                targetObstacle *= 2
            }
        }

    }
    val targetRadius = if (count.value == 1) 100f else initialRadius
    val animatedRadius by animateFloatAsState(
        targetValue = targetRadius,
        animationSpec = tween(durationMillis = 400)
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
                    Button(
                        onClick = {
                            initialTrack.value = track.value
                            track.value = it
                            if (initialTrack.value == track.value) {
                                count.value = 1
                            } else {
                                count.value = 0
                            }
                        },
                        modifier = Modifier
                            .height(5000.dp)
                            .width(100.dp)
                            .offset(x = 10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(
                                238,
                                244,
                                241,
                                255
                            )
                        ),
                        shape = RectangleShape
                    ) {
                    }

                }
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .padding(50.dp)
            .offset(y = 200.dp),
        contentAlignment = Alignment.Center
    ) {

        Canvas(
            modifier = Modifier
                .padding(1.dp)
        ) {

            for (i in 1..100) {
                drawRect(
                    color = Color.Blue,
                    topLeft = Offset(-50f, centreObstale.value - (350 * (2 * i + 1)).toFloat()),
                    size = Size(100f, 100f),
                )
                if (i % 2 == 0) {
                    drawRect(
                        color = Color.Blue,
                        topLeft = Offset(-425f, centreObstale.value - (700 * i).toFloat()),
                        size = Size(100f, 100f),
                    )
                }
                drawRect(
                    color = Color.Blue,
                    topLeft = Offset(320f, centreObstale.value - (700 * i).toFloat()),
                    size = Size(100f, 100f),
                )
                if (track.value == 1 && centreObstale.value - (350 * (2 * i + 1)).toFloat() - centreJerry.value > 40f && count.value == 1) {
                    count.value = 1

                } else if (track.value == 1 && centreObstale.value - (350 * (2 * i + 1)).toFloat() - centreJerry.value > -30f && count.value == 1) {
                    count.value = 0
                    isJump.value=false
                }
                if (track.value == 0 && centreObstale.value - (700 * i).toFloat() - centreJerry.value > 40f && count.value == 1) {
                    count.value = 1

                } else if (track.value == 0 && centreObstale.value - (700 * i).toFloat() - centreJerry.value > -30f && count.value == 1) {
                    count.value = 0
                    isJump.value = false
                }
                if (track.value == 2 && centreObstale.value - (700 * i).toFloat() - centreJerry.value > 40f && count.value == 1) {
                    count.value = 1

                } else if (track.value == 2 && centreObstale.value - (700 * i).toFloat() - centreJerry.value > -30f && count.value == 1) {
                    count.value = 0
                    isJump.value = false
                }
                if (track.value == 0 && (centreJerry.value == centreObstale.value - (700 * i).toFloat()) && i % 2 == 0 && !counterUpdated.value && gameContinue.value ) {
                    counter.value += 1
                    counterUpdated.value = true
                } else if (track.value == 1 && (centreJerry.value == centreObstale.value - (350 * (2 * i + 1)).toFloat()) && !counterUpdated.value && gameContinue.value ) {
                    counter.value += 1
                    counterUpdated.value = true
                } else if (track.value == 2 && (centreJerry.value == centreObstale.value - (700 * i).toFloat()) && !counterUpdated.value && gameContinue.value   ) {
                    counter.value += 1
                    counterUpdated.value = true
                }

            }

            drawCircle(
                color = Color.Red,
                radius = animatedRadius,
                center = Offset(xc.value, centreJerry.value)
            )
            drawCircle(
                color = Color.Black,
                radius = 90f,
                center = Offset(
                    xc.value, centreTom.value
                )
            )
        }
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
            else if(counter.value==0  && jumpTrack.value) {
                counter.value = 0
                jumpTrack.value = false
                isJump.value = true
                counterUpdated.value=false
            }
        }
        if (count.value == 1) {
                jumpSound.start()
        }
            if (counter.value == 1 && counterUpdated.value) {
                if(jumpCounter.value==0){
                    hitSound.start()
                }
                centreTom.value = 300f
                gameContinue.value = true
                if (counterUpdated.value) {
                    counterUpdated.value = false
                }
            } else if (counter.value == 2) {
                hitSound.start()
                centreTom.value = centreJerry.value + 50f
                gameContinue.value = false
                writeToSharedPref(LocalContext.current,"HighScore",gameScore.value.toString(),"Cheese Chase")
                AlertDialog(onDismissRequest = { /*TODO*/ }, confirmButton = { /*TODO*/ },
                    modifier = Modifier
                        .size(500.dp),
                    text = {
                        Column {
                            Text(
                                text = "GameOver\n\nTom wins",
                                fontFamily = customFontFamily,
                                modifier = Modifier
                                    .offset(70.dp,-10.dp),
                                fontSize = 32.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier
                                .padding(16.dp))
                            Image(
                                painter = painter1, contentDescription = "tomWin",
                                modifier = Modifier
                                    .size(height = 120.dp, width = 120.dp)
                                    .offset(80.dp, -40.dp)
                            )
                            Spacer(modifier = Modifier.padding(16.dp))
                            Box(modifier = Modifier
                                .offset(50.dp, -20.dp)
                                .size(height = 500.dp, width = 250.dp)) {
                                Column(Modifier.fillMaxSize()) {
                                    PlayAgain()
                                    Spacer(modifier = Modifier.padding(16.dp))
                                    Button(onClick = {navigate ()},
                                        modifier = Modifier
                                            .size(height=60.dp,width=180.dp)) {
                                        Text("Home",
                                            fontSize = 24.sp,
                                            fontWeight = FontWeight.Bold
                                        )

                                    }
                                }

                            }
                        }


                    })
            } else if (centreTom.value - centreJerry.value > 4000f) {
                gameContinue.value = false
                writeToSharedPref(LocalContext.current,"HighScore",gameScore.value.toString(),"Cheese Chase")
                AlertDialog(onDismissRequest = { /*TODO*/ }, confirmButton = { /*TODO*/ },
                    modifier = Modifier
                        .size(500.dp),
                    text = {
                        Column {
                            Text(
                                text = "GameOver\n\nJerry wins",
                                fontFamily = customFontFamily,
                                modifier = Modifier
                                    .offset(70.dp,-10.dp),
                                fontSize = 40.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier
                                .padding(16.dp))
                            Image(
                                painter = painter2, contentDescription = "jerryWin",
                                modifier = Modifier
                                    .size(height = 120.dp, width = 120.dp)
                                    .offset(80.dp, -40.dp)
                            )
                            Spacer(modifier = Modifier.padding(16.dp))
                            Box(modifier = Modifier
                                .offset(50.dp, -20.dp)
                                .size(height = 500.dp, width = 250.dp)) {
                                Column(Modifier.fillMaxSize()) {
                                    PlayAgain()
                                    Spacer(modifier = Modifier.padding(16.dp))
                                    Button(onClick = { navigate()},
                                        modifier = Modifier
                                            .size(height=60.dp,width=180.dp)) {
                                        Text("Home",
                                            fontSize = 24.sp,
                                            fontWeight = FontWeight.Bold
                                        )

                                    }
                                }

                            }
                        }

                    })
            }

            Column(
                modifier = Modifier
                    .offset(250.dp, -250.dp)
                    .fillMaxSize()
            ) {
                Card(shape = CircleShape,
                    modifier = Modifier
                        .size(height = 60.dp, width = 60.dp)
                        .border(3.dp, color = Color(255, 195, 0), shape = CircleShape),
                    colors = CardDefaults.cardColors(Color.White)) {
                    Text(text = (gameScore.value).toString(),
                        fontSize = 20.sp,
                        modifier=Modifier
                            .offset(20.dp,15.dp))
                }
            }
        }
    }



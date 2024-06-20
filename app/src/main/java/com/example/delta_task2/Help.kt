package com.example.delta_task2

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Help(){
    var painter= painterResource(id = R.drawable.help_icon)
    Column (modifier = Modifier.fillMaxSize()){
        Button(onClick={
                       helpAlert.value=true
        },
            modifier = Modifier
                .size(height = 60.dp, width = 60.dp)
                .offset(x = 250.dp, y = -500.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White)){

        }
        Image(painter = painter, contentDescription = "Help",
            modifier = Modifier
                .size(40.dp)
                .offset(259.dp, -550.dp))
    }
    if(helpAlert.value){

            AlertDialog(onDismissRequest = { helpAlert.value=false
                                           rulePage.value=true}, confirmButton = { /*TODO*/ },
                modifier = Modifier
                    .size(1000.dp),
                text={
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(state = rememberScrollState(), true)) {
                        repeat(1) {
                            Text(
                                "Game Rules & Logic\n" +
                                        "\n" +
                                        "\n" +
                                        "Tom is chasing Jerry, but it can’t just run away as it’s faced with obstacles and traps in front of it. \n" +
                                        "\n" +
                                        "It has to tackle by moving left and right so that it doesn’t slow down by hitting an obstacle or possibly \n" +
                                        "die by getting caught in the trap. \n" +
                                        "\n" +
                                        "Tom nears Jerry every time it hits an obstacle, paving way for it to catch it in the next opportunity.\n" +
                                        "\n" +
                                        " \n" +
                                        "The Blue Boxes Out There Are the Obstacle Which Jerry Has Got Dodge \n" +
                                        "He can achieve it either by changing lanes or tapping on the same lane to jump \n" +
                                        "But be WATCHFULL speed of jerry increases with time\n" +
                                        "\n" +
                                        " \n" +
                                        "The Red Circle is our very own Jerry who tries to escape Tom\n" +
                                        "Initially Jerry Runs away from Tom But as soon as Jerry hits an Obstacle Once Tom Closes upon him \n" +
                                        "But the second time though he catches him.\n" +
                                        "\n" +
                                        " \n" +
                                        "The Black Circle Right Behind Jerry is Tom who constantly chases Jerry.\n" +
                                        " \n" +
                                        "\n" +
                                        "As a Power Up during the initial phase of run Jerry is provided with 3 keys which gets added up even during the next set of games\n" +
                                        "When tom catches jerry you can click on this button \n" +
                                        " \n" +
                                        "so that you continue Jerry’s escape without losing the score earned \n" +
                                        "For the first use of the power up one key is required and for later use in the same run 2 keys are required\n" +
                                        "If the required number of Keys are not available the button will not the displayed\n" +
                                        "And There are traps initially placed which constantly move towards you \n" +
                                        "You can escape it only if you change the lane and jumping doesn't save you from the trap \n" +
                                        "And hitting the trap is considered to be a collision and tom closes upon jerry \n" +
                                        "There are some traps in the track when collided the game ends even if you collide once (PROBABILITY 1/3)"

                            )
                            Button(onClick = { rulePage.value=true
                                helpAlert.value=false},
                                shape = RectangleShape){
                                Text(text = "Ok",
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.ExtraBold
                                )
                            }
                        }
                    }

                })

        }


    }

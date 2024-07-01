package com.example.delta_task2

import android.graphics.Bitmap
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

var count =
    mutableStateOf(0)
var gameContinue =
    mutableStateOf(true)
var gameScore =
    mutableStateOf(0)
var track =
    mutableStateOf(1)
var initialTrack =
    mutableStateOf(3)
var xc =
    mutableStateOf(0f)
var counter =
    mutableStateOf(0)
var centreTom =
    mutableStateOf(150f)
var centreObstale =
    mutableStateOf(300f)
var counterUpdated=
    mutableStateOf(false)
var centreJerry=
    mutableStateOf(100f)
var isJump =
    mutableStateOf(true)
var jumpTrack=
    mutableStateOf(false)
var jumpCounter=
    mutableStateOf(0)
var highScoreAlert=
    mutableStateOf(false)
var trapYCoord =
    mutableStateOf(-700f)
var gamePause=
    mutableStateOf(false)
var conditionCheck=
    mutableStateOf(0 )
var powerUp=
    mutableStateOf(-40f)
var targetTrap =
    mutableStateOf(1440f)
var alreadyCounted=
    mutableStateOf(0)
var delayObstacle=
    mutableStateOf(5L)
var targetObstacle=
    mutableStateOf(1050f)
var keyCount=
    mutableStateOf(0)
var powerUpUse=
    mutableStateOf(0)
var continueButton=
    mutableStateOf(false)
var keyUsed=
    mutableStateOf(0)
var helpAlert=
    mutableStateOf(false)
var rulePage=
    mutableStateOf(false)
var shieldY=
    mutableStateOf(-1000f)
var shieldTargetY=
    mutableStateOf(500f)
var shieldCollided=
    mutableStateOf(false)
var shieldTimeRemaining =
    mutableStateOf(0)
var shieldDisappear=
    mutableStateOf(false)
val keyCollected =
    mutableStateListOf(false,false,false)
val maxCollision= mutableStateOf(0)
var check= mutableStateOf(2)
var type= mutableStateOf(5)
var amount= mutableStateOf(5)
var hitHindranceCheck = mutableStateOf(false)
var dist= mutableStateOf(0f)
var changed = mutableStateOf(false)

var extent= mutableStateOf(0)
var path=mutableStateListOf("","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",
    "","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",
    "","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",
    "","","","","","","","","","")



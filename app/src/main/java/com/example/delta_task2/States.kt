package com.example.delta_task2

import androidx.compose.runtime.getValue
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
    mutableStateOf(300f)
var centreObstale =
    mutableStateOf(120f)
var counterUpdated=
    mutableStateOf(false)
var centreJerry=
    mutableStateOf(120f)
var ExitConfirm=
    mutableStateOf(false)
var isJump =
    mutableStateOf(false)
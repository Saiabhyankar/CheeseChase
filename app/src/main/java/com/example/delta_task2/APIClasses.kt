package com.example.delta_task2
import androidx.compose.runtime.remember
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

data class Obstacle(
    val obstacleLimit:Int
)

data class ObstacleCourseRequest(
        var extent:Int
        )
data class HitHindrance(
        var type:Int,
        var amount:Int,
        var description:String
        )

data class ObstacleCourseResponse(
    var obstacleCourse:List<String>?
)

data class RandomWordRequest(
    var length:Int
)

data class RandomWordResponse(
    var word:String?=null
)

data class ThemeRequest(
    val date: String,
    val time: String
)

data class ThemeResponse(
    val theme:String
)


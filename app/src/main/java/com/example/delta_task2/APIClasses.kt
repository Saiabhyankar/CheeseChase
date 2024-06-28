package com.example.delta_task2

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
    var obstacleCourse:List<String>
)

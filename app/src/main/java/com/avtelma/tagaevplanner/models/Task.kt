package com.avtelma.tagaevplanner.models

data class Task(
    var progress: Float,
    var taskDescription: String,
    val type: SphereType,
    var idSphere: String
)

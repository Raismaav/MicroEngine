package Modules

import kotlin.math.roundToLong

class Time() {
    var deltaTime: Double = 0.0
    var fpsCounter: Long = 0
    var previousTime: Long = 0

    init {
        previousTime = System.currentTimeMillis()
    }
    fun updateDeltaTime() {
        deltaTime = (System.currentTimeMillis() - previousTime).toDouble() / 1000
        println(deltaTime)
        fpsCounter = (1 / deltaTime).roundToLong()
    }
}
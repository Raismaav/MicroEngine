package Modules

import java.lang.Double.parseDouble

class Time() {
    var deltaTime: Double = 0.0
    var fpsCounter: Int = 0
    private var firstTime: Long

    init {
        firstTime = System.currentTimeMillis()
    }
    fun updateDeltaTime() {
        deltaTime = (System.currentTimeMillis() - firstTime).toDouble()
        fpsCounter = (1000 / deltaTime).toInt()
        firstTime = System.currentTimeMillis()
    }
}
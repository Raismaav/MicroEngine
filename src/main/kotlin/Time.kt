import java.lang.Double.parseDouble

class Time() {
    var deltaTime: Double = 0.0
    var firstTime: Long

    init {
        firstTime = System.currentTimeMillis()
    }
    fun updateDeltaTime() {
        deltaTime = (System.currentTimeMillis() - firstTime).toDouble()
        firstTime = System.currentTimeMillis()
    }
}
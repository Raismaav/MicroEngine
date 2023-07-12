import Modules.GameLoop
import Modules.Graphics.Graphics
import java.awt.Color

class Game(widht: Int, height: Int) : GameLoop(widht, height) {
    val graphics: Graphics

    init {
        debugMode = true
        graphics = Graphics(renderer.buffer)
    }

    override fun update() {
        renderer.draw() {
            graphics.drawLine(10, 10, 100, 100)
            graphics.drawLine(10, 100, 100, 10, Color.orange)
        }
    }
}
import Modules.Figures.Sprite
import Modules.GameLoop
import Modules.Graphics.Graphics
import java.awt.Color
import kotlin.math.PI

class Game(widht: Int, height: Int) : GameLoop(widht, height) {
    val graphics: Graphics
    val sprite: Sprite

    init {
        debugMode = true
        graphics = Graphics(buffer)
        sprite = Sprite(10,10, buffer)
        sprite.drawFigures{
            val spriteGraphics = Graphics(sprite.spriteBuffer)
            spriteGraphics.drawRect(0, 0, 9, 9, Color.orange)
        }
    }

    override fun update() {
        /*graphics.drawPoint(200, 200, Color.red)
        graphics.drawLine(10, 10, 100, 100)
        graphics.drawLine(10, 100, 100, 10, Color.orange)
        graphics.fillTriangle(100, 200, 100, 100)
        graphics.fillRect(200, 200, 400, 600, Color.red, Color.red)
        graphics.fillCircle(300, 300, 100)
        graphics.fillOval(600, 300, 100, 50, Color.blue, Color.blue)
        graphics.drawMesh(500, 0, 10, 10, 10, 10)*/

        sprite.drawSprite(10, 10)
        sprite.translation(10.0 * time.deltaTime, 10.0 * time.deltaTime)
        sprite.rotation(180.0 * time.deltaTime)
    }
}
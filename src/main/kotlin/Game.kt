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
        graphics.drawLine(10, 10, 100, 100)
        graphics.drawLine(10, 100, 100, 10, Color.orange)

        sprite.drawSprite(200, 200)
        sprite.translation(10.0 * time.deltaTime, 10.0 * time.deltaTime)
        sprite.rotation(45.0 * time.deltaTime)
    }
}
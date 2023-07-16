import Modules.Figures.Sprite
import Modules.GameLoop
import Modules.Graphics.Graphics
import java.awt.Color

class Game(widht: Int, height: Int) : GameLoop(widht, height) {
    val graphics: Graphics
    val sprite: Sprite
    val sprite2: Sprite
    init {
        debugMode = true
        graphics = Graphics(buffer)
        sprite = Sprite(10,10, buffer)
        sprite2 = Sprite(10,10, buffer)
        sprite.drawFigures{
            val spriteGraphics = Graphics(sprite.spriteBuffer)
            spriteGraphics.drawRect(0, 0, 9, 9)
        }
        sprite2.drawFigures{
            val spriteGraphics = Graphics(sprite2.spriteBuffer)
            spriteGraphics.drawRect(0, 0, 9, 9, Color.orange)
        }
    }

    override fun update() {
        graphics.drawLine(10, 10, 100, 100)
        graphics.drawLine(10, 100, 100, 10, Color.orange)
        sprite.drawSprite(200, 200)
        sprite2.drawSprite(200, 200)
        sprite.translation(100.0 * time.deltaTime, 0.0 * time.deltaTime)
        sprite2.translation(100.0 * time.deltaTime, 100.0 * time.deltaTime)
    }
}
import Modules.Figures.Sprites
import Modules.GameLoop
import Modules.Graphics.Graphics
import java.awt.Color

class Game(widht: Int, height: Int) : GameLoop(widht, height) {
    val graphics: Graphics
    val sprite: Sprites
    init {
        debugMode = true
        graphics = Graphics(renderer.buffer)
        sprite = Sprites(10,10, renderer.buffer)
        sprite.drawFigures{
            val spriteGraphics = Graphics(sprite.spriteBuffer)
            spriteGraphics.drawRect(0, 0, 9, 9)
        }
    }

    override fun update() {
        renderer.drawFigures() {
            graphics.drawLine(10, 10, 100, 100)
            graphics.drawLine(10, 100, 100, 10, Color.orange)
            sprite.drawSprite(200, 200)
        }
        sprite.translation(1.0, 0.0)
    }
}
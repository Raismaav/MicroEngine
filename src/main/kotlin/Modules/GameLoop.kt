package Modules

import Modules.Graphics.Renderer
import java.awt.image.BufferedImage

open class GameLoop(widht: Int, height: Int): Thread() {
    protected val buffer: BufferedImage
    protected var debugMode = false
    protected val time: Time

    private val window: Window
    private val renderer: Renderer

    init {
        window = Window(widht, height)
        renderer = Renderer(window.canvas)
        buffer = renderer.buffer
        time = Time()
    }

    protected open fun update() {

    }

    private fun debugMode() {
        window.fpsCounter.text = "${time.fpsCounter} fps"
        window.fpsCounter.isVisible = debugMode
    }

    override fun run() {
        var ticks = 0
        while (true) {
            time.previousTime = System.currentTimeMillis()
            renderer.drawFigures {
                update()
            }
            if (ticks % 10 == 0) debugMode()
            time.updateDeltaTime()
            ticks++
        }
    }
}
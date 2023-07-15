package Modules

import Modules.Graphics.Renderer

open class GameLoop(widht: Int, height: Int): Thread() {
    protected val window: Window
    protected val renderer: Renderer
    protected var debugMode = false
    protected val time: Time

    init {
        window = Window(widht, height)
        renderer = Renderer(window.canvas)
        renderer.drawFigures()
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

        debugMode()

        while (true) {
            ticks++
            time.previousTime = System.currentTimeMillis()
            update()
            if (ticks % 5 == 0) {
                debugMode()
                ticks = 0
            }
            sleep(1)
            time.updateDeltaTime()
        }
    }
}
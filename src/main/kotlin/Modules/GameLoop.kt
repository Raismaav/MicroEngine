package Modules

import Modules.Graphics.Renderer

open class GameLoop(widht: Int, height: Int): Thread() {
    private val time: Time

    protected val window: Window
    protected val renderer: Renderer
    protected var debugMode = false

    init {
        window = Window(widht, height)
        renderer = Renderer(window.canvas)
        renderer.draw(){

        }
        time = Time()
    }

     protected open fun update() {

    }

    private fun debugMode() {
        window.fpsCounter.text = "${time.fpsCounter} fps"
        window.fpsCounter.isVisible = debugMode
    }

    override fun run() {
        while (true) {
            time.updateDeltaTime()
            update()
            debugMode()
            sleep(5)
        }
    }
}
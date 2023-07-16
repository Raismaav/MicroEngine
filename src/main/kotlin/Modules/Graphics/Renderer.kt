package Modules.Graphics

import java.awt.Canvas
import java.awt.Color
import java.awt.image.BufferedImage

class Renderer(val canvas: Canvas, val colorBackground: Color = Color(238, 238, 238)) {
    val buffer: BufferedImage

    init {
        buffer = BufferedImage(canvas.width, canvas.height, BufferedImage.TYPE_INT_ARGB)
        for (j in 0 until buffer.height) {
            for (i in 0 until buffer.width) {
                buffer.setRGB(i, j, colorBackground.rgb)
            }
        }
        drawFigures()
    }

    fun drawFigures(figures: () -> Unit) {
        repaintBackground()
        figures()
        canvas.graphics.drawImage(buffer, 0, 0, canvas)
    }

    private fun drawFigures() {
        canvas.graphics.drawImage(buffer, 0, 0, canvas)
    }

    private fun repaintBackground() {
        for (j in 0 until buffer.height) {
            for (i in 0 until buffer.width) {
                buffer.setRGB(i, j, colorBackground.getRGB())
            }
        }
    }
}
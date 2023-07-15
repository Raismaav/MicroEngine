package Modules.Graphics

import java.awt.Color
import java.awt.image.BufferedImage
import kotlin.math.roundToInt

open class Graphics(val buffer: BufferedImage) {

    open fun drawPoint(x: Int, y: Int, color: Color = Color.black) {
        drawPixel(x, y, color)
    }

    open fun drawLine(startX: Int, startY: Int, endX: Int, endY: Int, color: Color = Color.black) {
        var startX = startX
        var startY = startY
        var endX = endX
        var endY = endY
        var x: Double
        var y: Double

        if (startX < endX) {
            x = startX.toDouble()
            y = startY.toDouble()
        } else {
            val bufferX = endX
            val bufferY = endY
            x = bufferX.toDouble()
            y = bufferY.toDouble()
            endX = startX
            endY = startY
            startX = bufferX
            startY = bufferY
        }

        val m = (endY - y) / (endX - x)

        if (m >= 1) {
            for (j in startY..endY) {
                drawPixel(x.roundToInt(), j, color)
                x += 1 / m
            }
        } else if (m >= -1) {
            for (i in startX..endX) {
                drawPixel(i, y.roundToInt(), color)
                y += m
            }
        } else {
            for (j in startY downTo endY) {
                drawPixel(x.roundToInt(), j, color)
                x -= 1 / m
            }
        }
    }

    open fun drawRect(startX: Int, startY: Int, endX: Int, endY: Int, color: Color = Color.black) {
        drawLine(startX, startY, endX, startY, color)
        drawLine(endX, startY, endX, endY, color)
        drawLine(startX, endY, endX, endY, color)
        drawLine(startX, startY, startX, endY, color)
    }

    protected fun drawPixel(x: Int, y: Int, color: Color) {
        if (x >= 0 && x < buffer.width && y >= 0 && y < buffer.height) {
            buffer.setRGB(x, y, color.rgb)
        }
    }
}
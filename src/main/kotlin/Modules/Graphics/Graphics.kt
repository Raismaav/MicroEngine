package Modules.Graphics

import java.awt.Color
import java.awt.image.BufferedImage
import kotlin.math.abs
import kotlin.math.roundToInt

open class Graphics(val buffer: BufferedImage) {

    fun drawPoint(x: Int, y: Int, color: Color = Color.black) {
        drawPixel(x, y, color)
    }

    fun drawLine(startX: Int, startY: Int, endX: Int, endY: Int, color: Color = Color.black) {
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

    open fun drawTriangle(startX: Int, endX: Int, y: Int, height: Int, color: Color = Color.black) {
        drawLine(startX, y + height, endX, y + height, color)
        drawLine((endX - startX) / 2 + startX, y, startX, y + height, color)
        drawLine((endX - startX) / 2 + startX, y, endX, y + height, color)
    }

    fun drawRect(startX: Int, startY: Int, endX: Int, endY: Int, color: Color = Color.black) {
        drawLine(startX, startY, endX, startY, color)
        drawLine(endX, startY, endX, endY, color)
        drawLine(startX, endY, endX, endY, color)
        drawLine(startX, startY, startX, endY, color)
    }

    fun drawCircle(centerX: Int, centerY: Int, radius: Int, color: Color = Color.black) {
        var x = 0
        var y = radius
        var pk = 1 - radius
        drawPixel(centerX + x, centerY + y, color)
        drawPixel(centerX - x, centerY + y, color)
        drawPixel(centerX + x, centerY - y, color)
        drawPixel(centerX - x, centerY - y, color)
        drawPixel(centerX + y, centerY + x, color)
        drawPixel(centerX - y, centerY + x, color)
        drawPixel(centerX + y, centerY - x, color)
        drawPixel(centerX - y, centerY - x, color)
        while (x < y) {
            x++
            if (pk < 0) {
                pk += 2 * x + 3
            } else {
                y = y - 1
                pk += 2 * (x - y) + 5
            }
            drawPixel(centerX + x, centerY + y, color)
            drawPixel(centerX - x, centerY + y, color)
            drawPixel(centerX + x, centerY - y, color)
            drawPixel(centerX - x, centerY - y, color)
            drawPixel(centerX + y, centerY + x, color)
            drawPixel(centerX - y, centerY + x, color)
            drawPixel(centerX + y, centerY - x, color)
            drawPixel(centerX - y, centerY - x, color)
        }
    }

    fun drawOval(centerX: Int, centerY: Int, radiusX: Int, radiusY: Int, color: Color = Color.black) {
        var x = 0
        var y = radiusY
        var pk = (radiusY * radiusY - radiusX * radiusX * radiusY + 0.25 * radiusX * radiusX).toInt()
        drawPixel(centerX + x, centerY + y, color)
        drawPixel(centerX - x, centerY + y, color)
        drawPixel(centerX + x, centerY - y, color)
        drawPixel(centerX - x, centerY - y, color)
        while (radiusX * radiusX * (y - 0.5) > radiusY * radiusY * (x + 1)) {
            if (pk < 0) {
                pk += radiusY * radiusY * (2 * x + 3)
            } else {
                pk += radiusY * radiusY * (2 * x + 3) - radiusX * radiusX * (2 * y - 2)
                y--
            }
            x++
            drawPixel(centerX + x, centerY + y, color)
            drawPixel(centerX - x, centerY + y, color)
            drawPixel(centerX + x, centerY - y, color)
            drawPixel(centerX - x, centerY - y, color)
        }
        pk = (radiusY * radiusY * (x + 0.5) * (x + 0.5) + radiusX * radiusX * (y - 1) * (y - 1) - radiusX * radiusX * radiusY * radiusY).toInt()
        while (y > 0) {
            if (pk < 0) {
                pk += radiusY * radiusY * (2 * x + 2) - radiusX * radiusX * (2 * y - 3)
                x++
            } else {
                pk += radiusX * radiusX * (3 - 2 * y)
            }
            y--
            drawPixel(centerX + x, centerY + y, color)
            drawPixel(centerX - x, centerY + y, color)
            drawPixel(centerX + x, centerY - y, color)
            drawPixel(centerX - x, centerY - y, color)
        }
    }

    fun drawMesh(startX: Int, startY: Int, xPoints: Int, yPoints: Int, dx: Int, dy: Int, color: Color = Color.black) {
        val x = IntArray(xPoints)
        val y = IntArray(yPoints)
        var point = 0
        for (i in x.indices) {
            x[i] = startX + point
            point += dx
        }
        point = 0
        for (i in y.indices) {
            y[i] = startY + point
            point += dy
        }
        for (i in x.indices) {
            drawLine(x[i], y[0], x[i], y[y.size - 1], color)
        }
        for (j in y.indices) {
            drawLine(x[0], y[j], x[x.size - 1], y[j], color)
        }
    }

    fun fillTriangle(startX: Int, endX: Int, y: Int, height: Int, color: Color = Color.black, colorFill: Color = Color.black) {
        drawLine(startX, y + height, endX, y + height, color)
        fillTriagleLine(startX, y + height, (endX - startX) / 2 + startX, y, color, colorFill)
        fillTriagleLine(endX, y + height, (endX - startX) / 2 + startX, y, color, colorFill)
    }

    fun fillRect(startX: Int, startY: Int, endX: Int, endY: Int, color: Color = Color.black, colorFill: Color = Color.black) {
        drawLine(startX, startY, endX, startY, color)
        drawLine(endX, startY, endX, endY, color)
        drawLine(startX, endY, endX, endY, color)
        drawLine(startX, startY, startX, endY, color)
        for (i in startX + 1 until endX) {
            drawLine(i, startY + 1, i, endY - 1, colorFill)
        }
    }

    open fun fillCircle(centerX: Int, centerY: Int, radius: Int, color: Color = Color.black, colorFill: Color = Color.black) {
        var x = 0
        var y = radius
        var pk = 1 - radius
        drawPixel(centerX + x, centerY + y, color)
        drawPixel(centerX - x, centerY + y, color)
        drawPixel(centerX + x, centerY - y, color)
        drawPixel(centerX - x, centerY - y, color)
        drawLine(centerX + x, centerY + y - 1, centerX + x, centerY - y + 1, colorFill)
        drawLine(centerX - x, centerY + y - 1, centerX - x, centerY - y + 1, colorFill)
        drawPixel(centerX + y, centerY + x, color)
        drawPixel(centerX + y, centerY - x, color)
        drawPixel(centerX - y, centerY + x, color)
        drawPixel(centerX - y, centerY - x, color)
        drawLine(centerX + y - 1, centerY + x, centerX - y + 1, centerY + x, colorFill)
        drawLine(centerX + y - 1, centerY - x, centerX - y + 1, centerY - x, colorFill)
        while (x < y) {
            x++
            if (pk < 0) {
                pk += 2 * x + 3
            } else {
                y = y - 1
                pk += 2 * (x - y) + 5
            }
            drawPixel(centerX + x, centerY + y, color)
            drawPixel(centerX - x, centerY + y, color)
            drawPixel(centerX + x, centerY - y, color)
            drawPixel(centerX - x, centerY - y, color)
            drawLine(centerX + x, centerY + y - 1, centerX + x, centerY - y + 1, colorFill)
            drawLine(centerX - x, centerY + y - 1, centerX - x, centerY - y + 1, colorFill)
            drawPixel(centerX + y, centerY + x, color)
            drawPixel(centerX + y, centerY - x, color)
            drawPixel(centerX - y, centerY + x, color)
            drawPixel(centerX - y, centerY - x, color)
            drawLine(centerX + y - 1, centerY + x, centerX - y + 1, centerY + x, colorFill)
            drawLine(centerX + y - 1, centerY - x, centerX - y + 1, centerY - x, colorFill)
        }
    }

    open fun fillOval(centerX: Int, centerY: Int, radiusX: Int, radiusY: Int, color: Color = Color.black, colorFill: Color = Color.black) {
        var x = 0
        var y = radiusY
        var pk = (radiusY * radiusY - radiusX * radiusX * radiusY + 0.25 * radiusX * radiusX).toInt()
        drawPixel(centerX + x, centerY + y, color)
        drawPixel(centerX - x, centerY + y, color)
        drawPixel(centerX + x, centerY - y, color)
        drawPixel(centerX - x, centerY - y, color)
        if (!(centerX + radiusX == centerX + x && centerX - radiusX == centerX - x)) {
            drawLine(centerX + x, centerY + y - 1, centerX + x, centerY - y + 1, colorFill)
            drawLine(centerX - x, centerY + y - 1, centerX - x, centerY - y + 1, colorFill)
        }
        while (radiusX * radiusX * (y - 0.5) > radiusY * radiusY * (x + 1)) {
            if (pk < 0) {
                pk += radiusY * radiusY * (2 * x + 3)
            } else {
                pk += radiusY * radiusY * (2 * x + 3) - radiusX * radiusX * (2 * y - 2)
                y--
            }
            x++
            drawPixel(centerX + x, centerY + y, color)
            drawPixel(centerX - x, centerY + y, color)
            drawPixel(centerX + x, centerY - y, color)
            drawPixel(centerX - x, centerY - y, color)
            if (!(centerX + radiusX == centerX + x && centerX - radiusX == centerX - x)) {
                drawLine(centerX + x, centerY + y - 1, centerX + x, centerY - y + 1, colorFill)
                drawLine(centerX - x, centerY + y - 1, centerX - x, centerY - y + 1, colorFill)
            }
        }
        pk =
            (radiusY * radiusY * (x + 0.5) * (x + 0.5) + radiusX * radiusX * (y - 1) * (y - 1) - radiusX * radiusX * radiusY * radiusY).toInt()
        while (y > 0) {
            if (pk < 0) {
                pk += radiusY * radiusY * (2 * x + 2) - radiusX * radiusX * (2 * y - 3)
                x++
            } else {
                pk += radiusX * radiusX * (3 - 2 * y)
            }
            y--
            drawPixel(centerX + x, centerY + y, color)
            drawPixel(centerX - x, centerY + y, color)
            drawPixel(centerX + x, centerY - y, color)
            drawPixel(centerX - x, centerY - y, color)
            if (!(centerX + radiusX == centerX + x && centerX - radiusX == centerX - x)) {
                drawLine(centerX + x, centerY + y - 1, centerX + x, centerY - y + 1, colorFill)
                drawLine(centerX - x, centerY + y - 1, centerX - x, centerY - y + 1, colorFill)
            }
        }
    }


    private fun drawPixel(x: Int, y: Int, color: Color) {
        if (x >= 0 && x < buffer.width && y >= 0 && y < buffer.height) {
            buffer.setRGB(x, y, color.rgb)
        }
    }

    private fun fillTriagleLine(startX: Int, startY: Int, endX: Int, endY: Int, color: Color, colorFill: Color) {
        var startX = startX
        var startY = startY
        var endX = endX
        var endY = endY
        var x = 0.0
        var y = 0.0
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
            val xant = x.roundToInt()
            var jant = startY
            for (j in startY..endY) {
                drawPixel(x.roundToInt(), j, color)
                if (xant != x.roundToInt()) {
                    jant = j + abs(m.roundToInt())
                }
                if (x.roundToInt() > startX && x.roundToInt() < endX && jant > startY && jant < endY) {
                    drawLine(x.roundToInt(), jant, x.roundToInt(), endY - 1, colorFill)
                }
                x += 1 / m
            }
        } else if (m >= -1) {
            for (i in startX..endX) {
                drawPixel(i, y.roundToInt(), color)
                drawLine(i, startY + 1, i, y.roundToInt(), colorFill)
                y += m
            }
        } else {
            val xant = Math.round(x)
            var jant = startY
            for (j in startY downTo endY) {
                drawPixel(x.roundToInt(), j, color)
                if (xant != Math.round(x)) {
                    jant = j + abs(m.roundToInt())
                }
                if (x.roundToInt() > startX && x.roundToInt() <= endX && jant > endY && jant < startY) {
                    drawLine(x.roundToInt(), startY - 1, x.roundToInt(), jant, colorFill)
                }
                x -= 1 / m
            }
        }
    }

}
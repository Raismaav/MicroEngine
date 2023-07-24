package Modules.Figures

import java.awt.Color
import java.awt.image.BufferedImage
import kotlin.math.roundToInt

class Sprite(val width: Int = 16, val height: Int = 16, val buffer: BufferedImage) {
    val sprite: Array<DoubleArray>
    val colors: IntArray
    val spriteBuffer: BufferedImage
    var centerFigure = DoubleArray(2)

    init {
        spriteBuffer = BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
        sprite = Array(3) {
            DoubleArray(width * height) { 1.0 }
        }
        colors = IntArray(width * height)
    }

    fun drawSprite(x: Int = 0, y: Int = 0) {
        for (i in sprite[0].indices) {
            if (colors[i] != 0) drawPixel(sprite[0][i].roundToInt() + x, sprite[1][i].roundToInt() + y, Color(colors[i]))
        }
    }

    fun drawFigures(figures: () -> Unit) {
        figures()
        var index = 0
        for (j in 0 until height) {
            for (i in 0 until width) {
                sprite[0][index] = i.toDouble()
                sprite[1][index] = j.toDouble()
                colors[index] = spriteBuffer.getRGB(i, j)
                index++
            }
        }
    }

    fun translation(dx: Double = 0.0, dy: Double = 0.0) {
        val identityMatrix = arrayOf(
            doubleArrayOf(1.0, 0.0, dx),
            doubleArrayOf(0.0, 1.0, dy),
            doubleArrayOf(0.0, 0.0, 1.0)
        )
        val finalSprite = Array(3) {
            DoubleArray(width * height)
        }
        for (i in identityMatrix.indices) {
            for (j in sprite[0].indices) {
                for (k in identityMatrix[0].indices) {
                    finalSprite[i][j] += identityMatrix[i][k] * sprite[k][j]
                }
            }
        }
        for (j in finalSprite.indices) {
            sprite[j] = finalSprite[j].clone()
        }
    }

    fun rotation(angle: Double = 0.0) {
        val centerX = (sprite[0].max() + sprite[0].min()) / 2
        val centerY = (sprite[1].max() + sprite[1].min()) / 2
        val radiansAngle = Math.toRadians(angle)
        val identityMatrix = arrayOf(
            doubleArrayOf(Math.cos(radiansAngle), -Math.sin(radiansAngle), 0.0),
            doubleArrayOf(Math.sin(radiansAngle), Math.cos(radiansAngle), 0.0),
            doubleArrayOf(0.0, 0.0, 1.0)
        )
        val finalSprite = Array(3) {
            DoubleArray(width * height)
        }

        for (i in sprite[0].indices) {
            sprite[0][i] -= centerX
            sprite[1][i] -= centerY
        }
        for (i in identityMatrix.indices) {
            for (j in sprite.get(0).indices) {
                for (k in identityMatrix[0].indices) {
                    finalSprite[i][j] += identityMatrix[i][k] * sprite[k][j]
                }
            }
        }
        for (j in finalSprite.indices) {
            sprite[j] = finalSprite[j].clone()
        }
        for (i in sprite[0].indices) {
            sprite[0][i] += centerX
            sprite[1][i] += centerY
        }
    }

    private fun drawPixel(x: Int, y: Int, color: Color) {
        if (x >= 0 && x < buffer.width && y >= 0 && y < buffer.height) {
            buffer.setRGB(x, y, color.rgb)
        }
    }
}
package Modules.Figures

import Modules.Graphics.Graphics
import java.awt.Color
import java.awt.image.BufferedImage
import java.util.*
import kotlin.math.roundToInt

class Sprites(val width: Int = 16, val height: Int = 16, buffer: BufferedImage): Graphics(buffer) {
    val sprite: Array<DoubleArray>
    val colors: IntArray
    val spriteBuffer: BufferedImage

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

    fun translation(dx: Double, dy: Double) {
        val identityMatrix = arrayOf(
            doubleArrayOf(1.0, 0.0, dx),
            doubleArrayOf(0.0, 1.0, dy),
            doubleArrayOf(0.0, 0.0, 1.0)
        )
        for (i in identityMatrix.indices) {
            for (j in sprite[0].indices) {
                for (k in identityMatrix[0].indices) {
                    sprite[i][j] += identityMatrix[i][k] * sprite[k][j]
                }
            }
        }
    }
}
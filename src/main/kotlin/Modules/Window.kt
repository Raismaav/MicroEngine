package Modules

import java.awt.BorderLayout
import java.awt.Canvas
import java.awt.Color
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JPanel

class Window (widht: Int, height: Int): JFrame() {
    val fpsCounter: JLabel
    val canvas: Canvas

    init {
        title = "MicroEngine"
        layout = null
        isResizable = false
        setSize(widht, height)
        setLocationRelativeTo(null)

        canvas = Canvas()
        canvas.setBounds(0, 0, widht, height)

        fpsCounter = JLabel("fps counter")
        fpsCounter.setBounds(0, 0, 100, 20)
        fpsCounter.background = Color(0,0,0,0)
        add(fpsCounter)


        contentPane.add(canvas, BorderLayout.CENTER)
        isVisible = true
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    }
}
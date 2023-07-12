import java.awt.BorderLayout
import javax.swing.JFrame
import javax.swing.JPanel

class Window(widht: Int, height: Int): JFrame() {
    private var canvas: JPanel

    init {
        title = "MicroEngine"
        layout = null
        isResizable = false
        setSize(widht, height)
        setLocationRelativeTo(null)

        canvas = JPanel()
        canvas.setBounds(0, 0, widht, height)

        contentPane.add(canvas, BorderLayout.CENTER)
        isVisible = true
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        GameLoop().start()
    }
}
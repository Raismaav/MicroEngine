class GameLoop: Thread() {
    val time: Time

    init {
        time = Time()
    }

    fun update() {
        println(time.deltaTime)
    }

    override fun run() {
        while (true) {
            time.updateDeltaTime()
            update()
            sleep(100)
        }
    }
}
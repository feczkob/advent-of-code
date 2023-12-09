package day8

class Instruction(
    line: String,
) {

    private var values: Array<Direction> = emptyArray()

    private var currentDirection: Int = 0

    val current: Direction
        get() = values[currentDirection]

    init {
        line.forEach {
            values += Direction.fromString(it.toString())
        }
    }

    operator fun inc(): Instruction {
        currentDirection = (currentDirection + 1) % values.size
        return this
    }


    fun reset() {
        currentDirection = 0
    }

    override fun toString() =
        values.joinToString("")
}
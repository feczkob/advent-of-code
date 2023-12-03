package day3
class Symbol(
    private val value: Char,
    private val position: Position
) {

    override fun toString() = "Symbol(value=$value, position=$position)"
    fun isNeighbor(number: Number, lineLength: Int): Boolean {
        val neighbors = position.getNeighbors(lineLength)
        return number.positions.any { it in neighbors }
    }
}


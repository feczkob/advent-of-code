package day3
class Symbol(
    val value: Char,
    private val position: Position
) {

    lateinit var neighbors : List<Number>

    override fun toString() = "Symbol(value=$value, position=$position)"

    fun isNeighbor(number: Number, lineLength: Int): Boolean {
        val neighbors = position.getNeighbors(lineLength)
        return number.positions.any { it in neighbors }
    }

    fun hasExactlyTwoNeighbors(numbers: List<Number>, lineLength: Int): Boolean {
        val neighbors = position.getNeighbors(lineLength)
        return numbers.filter { number -> number.positions.any { it in neighbors } }
            .also { this.neighbors = it }
            .size == 2
    }

}


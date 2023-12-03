package day3

class Number(
    val value: Int,
    val positions: Set<Position>
) {

    override fun toString() = "Number(value=$value, positions=$positions)"

    fun isPartNumber(symbols: List<Symbol>, lineLength: Int) =
        symbols.any { it.isNeighbor(this, lineLength) }

    companion object {
        fun createNumber(
            currentNumber: StringBuilder,
            currentColumn: Int,
            lineNumber: Int,
        ): Number {
            val numberValue = currentNumber.toString().toInt()
            val positions = (currentColumn - currentNumber.length..<currentColumn).map {
                Position(lineNumber, it)
            }.toSet()

            currentNumber.clear()
            return Number(numberValue, positions)
        }
    }
}
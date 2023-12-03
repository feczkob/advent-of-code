package day3

private const val AST = '*'

class Line(
    private val lineLength: Int,
    val numbers: List<Number>,
    val symbols: List<Symbol>
) {

    fun sumPartNumbers(upperSymbols: List<Symbol>, lowerSymbols: List<Symbol>) =
        numbers.filter { it.isPartNumber(upperSymbols + symbols + lowerSymbols, lineLength) }
            .sumOf { it.value }

    fun sumOfGearRatios(upperNumbers: List<Number>, lowerNumbers: List<Number>) =
        symbols.filter { it.value == AST }
            .filter { it.hasExactlyTwoNeighbors(upperNumbers + numbers + lowerNumbers, lineLength) }
            .sumOf {
                it.neighbors
                    .map { number -> number.value }
                    .reduce(Int::times)
            }


    override fun toString() = "Line(numbers=$numbers, symbols=$symbols)"
}
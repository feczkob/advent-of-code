package day3

class Line(
    private val lineLength: Int,
    val numbers: List<Number>,
    val symbols: List<Symbol>
) {

    fun sumPartNumbers(upperSymbols: List<Symbol>, lowerSymbols: List<Symbol>) =
        numbers.filter { it.isPartNumber(upperSymbols + symbols + lowerSymbols, lineLength) }
            .sumOf { it.value }

    fun sumOfGearRatios(upperNumbers: List<Number>, lowerNumbers: List<Number>) =
        symbols.filter { it.isAst }
            .filter { it.hasExactlyTwoNeighbors(upperNumbers + numbers + lowerNumbers, lineLength) }
            .sumOf {
                it.neighbors
                    .map { number -> number.value }
                    .reduce(Int::times)
            }


    override fun toString() = "Line(numbers=$numbers, symbols=$symbols)"
}
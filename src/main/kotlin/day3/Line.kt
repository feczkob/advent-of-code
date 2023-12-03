package day3

class Line(
    private val lineLength: Int,
    private val numbers: List<Number>,
    val symbols: List<Symbol>
) {

    fun sumPartNumbers(upperSymbols: List<Symbol>, lowerSymbols: List<Symbol>) =
        numbers.filter { it.isPartNumber(upperSymbols + symbols + lowerSymbols, lineLength) }
            .onEach { println("Part number: ${it.value}") }
            .sumOf { it.value }

    override fun toString() = "Line(numbers=$numbers, symbols=$symbols)"
}
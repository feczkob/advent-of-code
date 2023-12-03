package day3

import readFileAsLines

private const val FILENAME = "./src/main/resources/day3/day3.txt"
private const val DOT = '.'

fun main() {
    val lines = readFileAsLines(FILENAME)
        .mapIndexed { lineNumber, line ->
            val numbers = mutableListOf<Number>()
            val symbols = mutableListOf<Symbol>()
            var currentColumn = 0
            val currentNumber = StringBuilder()

            line.forEachIndexed { index, char ->
                when {
                    char.isDigit() -> {
                        currentNumber.append(char)
                        if(index == line.length - 1) {
                            numbers += Number.createNumber(currentNumber, currentColumn, lineNumber)
                        }
                    }

                    currentNumber.isNotEmpty() -> {
                        numbers += Number.createNumber(currentNumber, currentColumn, lineNumber)

                        if (char != DOT) {
                            symbols.add(Symbol(char, Position(lineNumber, currentColumn)))
                        }
                    }

                    char != DOT -> {
                        symbols.add(Symbol(char, Position(lineNumber, currentColumn)))
                    }
                }
                currentColumn++
            }
            Line(line.length, numbers, symbols)
        }

    val value = lines.mapIndexed { index, line ->
        val upperSymbols = if (index > 0) lines[index - 1].symbols else emptyList()
        val lowerSymbols = if (index < lines.size - 1) lines[index + 1].symbols else emptyList()
        val sumPart = line.sumPartNumbers(upperSymbols, lowerSymbols)
//        println("Sum for line $index: $sumPart")
        sumPart
    }.sum()

    println("Sum of part numbers: $value")
}

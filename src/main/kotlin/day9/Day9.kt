package day9

import readFileAsLines

private const val FILENAME = "./src/main/resources/real/day9.txt"

fun main() {
    val lines = readFileAsLines(FILENAME)

    // Task 1
    val value = lines.map {
        History(it)
    }.sumOf { it.calculatePrediction() }

    println("Sum of extrapolated values: $value")
    // 1916822650

    // Task 2
    val value2 = lines.map {
        History(it)
    }.sumOf { it.calculateBackwards() }
    println("Sum of extrapolated values: $value2")
    // 966
}
package day4

import readFileAsLines
import kotlin.math.pow

private const val FILENAME = "./src/main/resources/day4/day4.txt"

fun main() {
    val cards = readInCards()

    // Task 1
    val value = cards
        .map { (w, m) ->
            m.count { it in w }
        }
        .filter { it > 0 }
        .sumOf { 2.toDouble().pow(it - 1) }
        .toInt()
    println("Sum of points: $value")
    // 28750

    // Task 2
    val instances = Array(cards.size) { 1 }
    cards.mapIndexed { index, (w, m) ->
        val match = m.count { it in w }
        for (i in index + 1..index + match) {
            instances[i] += instances[index]
        }
    }
    println("Sum of scratchcards: ${instances.sum()}")
    // 10212704
}

fun readInCards(): List<Pair<List<Int>, List<Int>>> {
    val lines = readFileAsLines(FILENAME)
    val pairs = mutableListOf<Pair<List<Int>, List<Int>>>()
    for (line in lines) {
        pairs += line.substringAfter(": ").let { cards ->
            val w = cards.substringBefore("|")
                .split(" ")
                .filter { it != "" }
                .map { it.toInt() }
            val m = cards.substringAfter("| ")
                .split(" ")
                .filterNot { it == "" }
                .map { it.toInt() }
            w to m
        }
    }
    return pairs
}
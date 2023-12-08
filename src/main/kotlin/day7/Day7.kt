package day7

import readFileAsLines

const val FILENAME = "./src/main/resources/real/day7.txt"

fun main() {
    val hands = readFileAsLines(FILENAME)
        .map { line ->
            line.split(" ")
                .let {
                    Hand(
                        it[0].map { c -> CardValue.fromString(c) }.toTypedArray(),
                        it[1].toInt()
                    )
                }
        }

    // Task 1
    val value = hands.sortedDescending().reversed()
        .mapIndexed { index, hand -> (index + 1) * hand.bid }
        .reduce(Int::plus)
    println("Total winning value: $value")

    val hands2 = readFileAsLines(FILENAME)
        .map { line ->
            line.split(" ")
                .let {
                    Hand(
                        it[0].map { c -> CardValue.fromString(c) }.toTypedArray(),
                        it[1].toInt(),
                        true
                    )
                }
        }

    // Task 2
    val value2 = hands2.sortedDescending().reversed()
        .mapIndexed { index, hand -> (index + 1) * hand.bid }
        .reduce(Int::plus)
    println("Total winning value: $value2")
    // 245576185
}
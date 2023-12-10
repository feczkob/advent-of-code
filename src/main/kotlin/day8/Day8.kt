package day8

import common.readFileAsText

private const val FILENAME = "./src/main/resources/real/day8.txt"

fun main() {
    val text = readFileAsText(FILENAME)
    val mappings = MappingsPart1(text)

    // Task 1
    val pred1: String.() -> Boolean = { this != "ZZZ" }
    val value1 = mappings[pred1]
    println("Task 1: $value1")
    // 16579

    // Task 2
    val pred2: String.() -> Boolean = { this.last() != 'Z' }
    val value2 = MappingsPart2(text)[pred2]
    println("Task 2: $value2")
    // 12927600769609
}
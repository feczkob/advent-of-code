package day10

import common.readFileAsLines

private const val FILENAME = "src/main/resources/real/day10.txt"

fun main() {
    val text = readFileAsLines(FILENAME)
    val grid = Grid(text)

    // Task 1
    val numOfSteps = grid.loopSize / 2
    println("Number of steps to get the farthest from the starting point: $numOfSteps")
    // 6613

    // Task 2
    val numOfEnclosedTiles = grid.numOfEnclosedTiles
    println("Number of enclosed tiles: $numOfEnclosedTiles")
    // 511
}
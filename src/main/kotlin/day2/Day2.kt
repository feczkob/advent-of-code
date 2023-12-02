package day2

import readFileAsLinesUsingReadLines

private const val FILENAME = "./src/main/resources/day2/day2.txt"

fun main() {
    // Task 1
    val numOfCubes = Draw(mutableMapOf(Color.RED to 12, Color.GREEN to 13, Color.BLUE to 14))
    val value = readInGames()
        .filter { it.possible(numOfCubes) }
        .sumOf { it.id }
    println("Sum of Ids of all possible games: $value")
    // 2545

    // Task 2
    val value2 = readInGames()
        .map { it.power() }
        .sumOf { it }
    println("Sum of all powers: $value2")
    // 78111
}

fun readInGames(): List<Game> =
    readFileAsLinesUsingReadLines(FILENAME)
        .map { line ->
            val gameId = line.substringAfter("Game ").substringBefore(":").toInt()
            line.substringAfter(": ").split("; ").map { draw ->
                draw.split(", ").associate { cube ->
                    val count = cube.substringBefore(" ").toInt()
                    val color = cube.substringAfter(" ").let { Color.fromString(it) }
                    color to count
                }.let { cubes ->
                    Draw(cubes)
                }
            }.let { draws ->
                Game(gameId, draws)
            }
        }
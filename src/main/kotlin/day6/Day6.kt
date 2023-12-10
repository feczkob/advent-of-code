package day6

import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import common.readFileAsText


private const val FILENAME = "./src/main/resources/real/day6.txt"
fun main() = runBlocking {
    val (times, distances) = readFileAsText(FILENAME)
        .split("\n")
        .map { line ->
            line.substringAfter(":")
                .split(" ").filter { it != "" }
        }

    // Task 1
    val value = times.mapIndexed { index, time ->
        (0..time.toInt())
            .count { (time.toInt() - it) * it > distances[index].toInt() }
    }.reduce(Int::times)
    println("Number of ways to beat the record: $value")
    // 1660968

    // Task 2
    val value2 = times.reduce(String::plus).toLong()
        .let { time ->
            val distance = distances.reduce(String::plus).toLong()
            val range = (0..time)
            val last = async { range.last { (time - it) * it > distance } }
            val first = async { range.first { (time - it) * it > distance } }
            return@let last.await() - first.await() + 1
        }

    println("Number of ways to beat the record: $value2")
    // 26499773
}
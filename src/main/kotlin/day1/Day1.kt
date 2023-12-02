package day1

import readFileAsLinesUsingReadLines

private const val FILENAME = "./src/main/resources/day1/day1.txt"

fun main() {
    // Task 1
    val value = readFileAsLinesUsingReadLines(FILENAME)
        .map { "${it.firstDigitOfNumber().value}${it.lastDigitOfNumber().value}" }.sumOf { it.toInt() }
    println("Sum of all calibration numbers: $value")
    // 54390

    // Task 2
    val value2 = readFileAsLinesUsingReadLines(FILENAME)
        .map { line ->
            val firstLetterDigit = line.firstDigitOfLetters()
            val lastLetterDigit = line.lastDigitOfLetters()
            val firstDigitDigit = line.firstDigitOfNumber()
            val lastDigitDigit = line.lastDigitOfNumber()
            val first =
                if (firstLetterDigit.index < firstDigitDigit.index) firstLetterDigit.value else firstDigitDigit.value
            val last = if (lastLetterDigit.index > lastDigitDigit.index) lastLetterDigit.value else lastDigitDigit.value
            "$first$last"
        }
        .sumOf { it.toInt() }
    println("Sum of all calibration numbers: $value2")
    // 54277
}


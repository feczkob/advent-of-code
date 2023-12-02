package day1

private const val INVALID_VALUE = -1
private const val UPPER_INDEX = 999
private const val LOWER_INDEX = -1

fun String.firstDigitOfLetters(): Digit {
    var ind = UPPER_INDEX
    var value = INVALID_VALUE
    Number.entries.forEach { number ->
        val index = this.indexOf(number.name)
        if (index != -1 && index < ind) {
            ind = index
            value = number.value
        }
    }
    return Digit(value, ind)
}

fun String.firstDigitOfNumber(): Digit {
    val firstDigit = this.first { it.isDigit() }
    val index = this.indexOf(firstDigit)
    return Digit(firstDigit.toString().toInt(), index)
}

fun String.lastDigitOfLetters(): Digit {
    var ind = LOWER_INDEX
    var value = INVALID_VALUE
    Number.entries.forEach { number ->
        val index = this.lastIndexOf(number.name)
        if (index != -1 && ind < index) {
            ind = index
            value = number.value
        }
    }
    return Digit(value, ind)
}

fun String.lastDigitOfNumber(): Digit {
    val lastDigit = this.last{ it.isDigit() }
    val index = this.lastIndexOf(lastDigit)
    return Digit(lastDigit.toString().toInt(), index)
}
package common

inline fun <reified T : Number> leastCommonMultiple(a: T, b: T): T {
    a as Long
    b as Long
    val larger = if (a > b) a else b
    val maxLcm = a * b
    var lcm = larger
    while (lcm <= maxLcm) {
        if (lcm % a == 0L && lcm % b == 0L) {
            return lcm
        }
        lcm += larger
    }
    return maxLcm as T
}

fun Int.isEven() = this % 2 == 0
fun Int.isOdd() = this % 2 != 0
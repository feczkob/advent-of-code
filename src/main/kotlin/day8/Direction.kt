package day8

enum class Direction(
    val value: String
) {
    LEFT("L"),
    RIGHT("R");

    companion object {
        fun fromString(value: String) = entries.first { it.value == value }
    }
}
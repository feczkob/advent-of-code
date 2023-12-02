package day2

enum class Color(val color: String) {
    RED("red"),
    GREEN("green"),
    BLUE("blue");

    companion object {
        fun fromString(color: String) = entries.first { it.color == color }
    }

    override fun toString() = color
}
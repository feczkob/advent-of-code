package day7

enum class CardValue(val part1: Int, val part2: Int) : Comparable<CardValue> {
    A(14, 14),
    K(13, 13),
    Q(12, 12),
    J(11, 1),
    T(10, 10),
    NINE(9, 9) {
        override fun toString() = "9"
    },
    EIGHT(8, 8) {
        override fun toString() = "8"
    },
    SEVEN(7, 7){
        override fun toString() = "7"
    },
    SIX(6, 6){
        override fun toString() = "6"
    },
    FIVE(5, 5){
        override fun toString() = "5"
    },
    FOUR(4, 4){
        override fun toString() = "4"
    },
    THREE(3, 3){
        override fun toString() = "3"
    },
    TWO(2, 2){
        override fun toString() = "2"
    };

    companion object {
        fun fromString(v: Char) =
            if(v.isDigit())
                entries.first { it.part1 == v.toString().toInt() }
            else
                entries.first { it.name == v.toString() }
    }
}
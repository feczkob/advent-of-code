package day9

class History(
    line: String
) {

    constructor(
        values: List<Int>
    ) : this(values.joinToString(" "))

    private val values: List<Int>

    init {
        values = line.split(" ").map { it.toInt() }
    }

    fun calculatePrediction(): Int {
        if(values.distinct().size == 1) return values.first()

        val differences = History(values.zipWithNext { a, b -> b - a }.toList())
        return values.last() + differences.calculatePrediction()
    }

    fun calculateBackwards(): Int {
        if(values.distinct().size == 1) return values.first()

        val differences = History(values.zipWithNext { a, b -> b - a }.toList())
        return values.first() - differences.calculateBackwards()
    }

    override fun toString() =
        values.toString()
}
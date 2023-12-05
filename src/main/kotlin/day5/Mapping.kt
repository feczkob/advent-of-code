package day5

class Mapping(
    input: List<List<Long>>
) {

    // From -> To
    private val rangeMapping: MutableMap<LongRange, LongRange> = mutableMapOf()

    init {
        input.forEach {
            rangeMapping[it[1]..<it[1] + it[2]] = it[0]..<it[0] + it[2]
        }
    }

    operator fun get(input: Long) =
        rangeMapping.entries.firstOrNull { input in it.key }?.let {
            val (from, to) = it
            val offset = input - from.first
            to.first + offset
        } ?: input

    fun reversedGet(input: Long) =
        rangeMapping.entries.firstOrNull { input in it.value }?.let {
            val (from, to) = it
            val offset = input - to.first
            from.first + offset
        } ?: input
}
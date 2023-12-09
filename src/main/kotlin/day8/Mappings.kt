package day8

abstract class Mappings(
    input: String,
) {

    protected var values: Map<String, Pair<String, String>> = emptyMap()

    protected lateinit var currentNodes: MutableList<String>

    protected val instruction: Instruction

    init {
        instruction = Instruction(input.split("\n").first())

        input.split("\n").drop(2).forEach { line ->
            val regex = Regex("\\(([A-Z]+), ([A-Z]+)\\)")
            values += line.substringBefore("=").trim() to
                    regex.find(line)?.groupValues?.let { it[1] to it[2] }!!
        }
    }

    abstract fun get(predicate: String.() -> Boolean): Long

    protected fun step(nodeNumber: Int = 0) =
        values[currentNodes[nodeNumber]]?.let {
            currentNodes[nodeNumber] = if (instruction.current == Direction.LEFT) it.first else it.second
            instruction.next()
        } ?: throw Exception("No mapping found for ${currentNodes[nodeNumber]}")


    override fun toString() =
        "Mappings: ${values}, $instruction"
}

class MappingsPart1(
    input: String,
) : Mappings(input) {

    init {
        currentNodes = mutableListOf("AAA")
    }

    override fun get(predicate: String.() -> Boolean): Long {
        var counter: Long = 0
        while (currentNodes[0].predicate()) {
            step()
            counter++
        }
        return counter
    }
}

class MappingsPart2(
    input: String,
) : Mappings(input) {

    init {
        currentNodes = values.keys
            .filter { it.last() == 'A' }
            .toMutableList()
    }

    override fun get(predicate: String.() -> Boolean): Long {
        val counterArray = LongArray(currentNodes.size) { 0 }
        currentNodes.forEachIndexed { index, _ ->
            var counter: Long = 0
            while (currentNodes[index].predicate()) {
                step(index)
                counter++
            }
            counterArray[index] = counter
            instruction.reset()
        }
        return findLCMOfListOfNumbers(counterArray.toList())
    }
}
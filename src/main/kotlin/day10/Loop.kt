package day10

class Loop(
    initialPipe : Pipe,
) {

    val pipes: MutableList<Pipe> = mutableListOf(initialPipe)

    val length: Int
        get() = pipes.size

    fun findNextPipe(rows: Array<Array<Pipe>>): Pipe? {
        val currentPipe = pipes.last()
        val candidates = listOfNotNull(
            rows.getOrNull(currentPipe.row - 1)?.getOrNull(currentPipe.col),
            rows.getOrNull(currentPipe.row + 1)?.getOrNull(currentPipe.col),
            rows.getOrNull(currentPipe.row)?.getOrNull(currentPipe.col - 1),
            rows.getOrNull(currentPipe.row)?.getOrNull(currentPipe.col + 1),
        )

        return candidates.firstOrNull { it !in pipes && currentPipe.validConnection(it) }
    }

    fun addPipe(pipe: Pipe) {
        pipes.add(pipe)
    }

    operator fun get(pred: (Pipe) -> Boolean) = pipes.filter(pred)
    operator fun contains(pipe: Pipe): Boolean = pipe in pipes

}
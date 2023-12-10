package day10

import kotlin.math.absoluteValue

private const val START = "S"

class Grid(
    private val input: List<String>,
) {

    private val grid: Array<Array<Pipe>> = Array(input.size) { Array(input[0].length) { Pipe() } }

    private val loop: Loop

    val loopSize: Int
        get() = loop.length

    val numOfEnclosedTiles: Int
        get() = calculateSurface() - loopSize / 2 + 1

    private fun calculateSurface(): Int {
        val colSeq = loop.pipes.map { it.col }.asSequence()
        val rowSeq = loop.pipes.map { it.row }.asSequence()

        val mappedColSeq = colSeq.mapIndexed { index, col ->
            val newIndex = if(index < colSeq.count() - 1) index + 1 else 0
            col * rowSeq.elementAt(newIndex)
        }

        val mappedRowSeq = rowSeq.mapIndexed { index, row ->
            val newIndex = if(index < rowSeq.count() - 1) index + 1 else 0
            row * colSeq.elementAt(newIndex)
        }
        return ((mappedColSeq.sum() - mappedRowSeq.sum()) / 2).absoluteValue
    }

    init {
        input.forEachIndexed { row, line ->
            line.forEachIndexed { col, c ->
                grid[row][col] = Pipe(row, col, c.toString())
            }
        }

        loop = Loop(
            grid.first { row -> row.any { pipe -> pipe.shape == START } }
                .first { pipe -> pipe.shape == START }
        )

        buildLoop()
    }

    private fun buildLoop() {
        var nextPipe: Pipe? = loop.findNextPipe(grid)
        while(nextPipe != null) {
            loop.addPipe(nextPipe)
            nextPipe = loop.findNextPipe(grid)
        }
    }

    override fun toString() =
        grid.joinToString("\n") { row ->
            row.joinToString("") { it.toString() }
        }
}
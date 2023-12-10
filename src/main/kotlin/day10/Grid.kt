package day10

import kotlin.math.absoluteValue

private const val START = "S"

class Grid(
    private val input: List<String>,
) {

    private val grid: Array<Array<Tile>> = Array(input.size) { Array(input[0].length) { Tile() } }

    private val loop: Loop

    val numOfSteps: Int
        get() = loop.length / 2

    val numOfEnclosedTiles: Int
        get() = calculateArea() - numOfSteps + 1

    private fun calculateArea() =
        loop.tiles.zip(loop.tiles.drop(1) + loop.tiles.take(1)) { first, second ->
            first.row * second.col - first.col * second.row
        }.sum().absoluteValue / 2


//    private fun calculateSurface(): Int {
//        val colSeq = loop.pipes.map { it.col }.asSequence()
//        val rowSeq = loop.pipes.map { it.row }.asSequence()
//
//        val mappedColSeq = colSeq.mapIndexed { index, col ->
//            val newIndex = if(index < colSeq.count() - 1) index + 1 else 0
//            col * rowSeq.elementAt(newIndex)
//        }
//
//        val mappedRowSeq = rowSeq.mapIndexed { index, row ->
//            val newIndex = if(index < rowSeq.count() - 1) index + 1 else 0
//            row * colSeq.elementAt(newIndex)
//        }
//        return ((mappedColSeq.sum() - mappedRowSeq.sum()) / 2).absoluteValue
//    }

    init {
        input.forEachIndexed { row, line ->
            line.forEachIndexed { col, c ->
                grid[row][col] = Tile(row, col, c.toString())
            }
        }

        loop = Loop(
            grid.first { row -> row.any { pipe -> pipe.shape == START } }
                .first { pipe -> pipe.shape == START }
        )

        buildLoop()
    }

    private fun buildLoop() {
        var nextTile: Tile? = loop.findNextPipe(grid)
        while (nextTile != null) {
            loop.addTile(nextTile)
            nextTile = loop.findNextPipe(grid)
        }
    }

    override fun toString() =
        grid.joinToString("\n") { row ->
            row.joinToString("") { it.toString() }
        }
}
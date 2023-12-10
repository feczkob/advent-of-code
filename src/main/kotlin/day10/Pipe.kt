package day10

import common.isOdd

data class Pipe(
    val row: Int = 0,
    val col: Int = 0,
    val shape: String = ".",
) {

    fun validConnection(other: Pipe): Boolean {
        return when (shape) {
            "|" -> other.shape in listOf("|", "L", "J", "7", "F", "S") && col == other.col
            "-" -> other.shape in listOf("-", "J", "L", "F", "7", "S") && row == other.row
            "L" -> {
                if (row == other.row && col == other.col - 1) return other.shape in listOf("-", "J", "7", "S")
                if (col == other.col && row == other.row + 1) return other.shape in listOf("|", "7", "F", "S")
                false
            }

            "J" -> {
                if (row == other.row && col == other.col + 1) return other.shape in listOf("-", "L", "F", "S")
                if (col == other.col && row == other.row + 1) return other.shape in listOf("|", "7", "F", "S")
                false
            }

            "7" -> {
                if (row == other.row && col == other.col + 1) return other.shape in listOf("-", "L", "F", "S")
                if (col == other.col && row == other.row - 1) return other.shape in listOf("|", "L", "J", "S")
                false
            }

            "F" -> {
                if (row == other.row && col == other.col - 1) return other.shape in listOf("-", "7", "J", "S")
                if (col == other.col && row == other.row - 1) return other.shape in listOf("|", "J", "L", "S")
                false
            }

            "S" -> {
                if (row == other.row && col == other.col - 1) return other.shape in listOf("-", "7", "J")
                if (row == other.row && col == other.col + 1) return other.shape in listOf("-", "L", "F")
                if (col == other.col && row == other.row - 1) return other.shape in listOf("|", "J", "L")
                if (col == other.col && row == other.row + 1) return other.shape in listOf("|", "7", "F")
                false
            }

            else -> false
        }
    }

    fun isEnclosedByTheLoop(loop: Loop): Boolean {
        if(this in loop) return false

        val numOfPipesOnTheLeft = loop[{ it.row == row && it.col < col }].size
        val numOfPipesOnTheRight = loop[{ it.row == row && it.col > col }].size
        val numOfPipesOnTheTop = loop[{ it.row < row && it.col == col }].size
        val numOfPipesOnTheBottom = loop[{ it.row > row && it.col == col }].size

        return numOfPipesOnTheLeft.isOdd() &&
                numOfPipesOnTheRight.isOdd() &&
                numOfPipesOnTheTop.isOdd() &&
                numOfPipesOnTheBottom.isOdd()
    }

    override fun toString() =
        when(shape) {
            "L" -> "└"
            "J" -> "┘"
            "7" -> "┐"
            "F" -> "┌"
            "-" -> "─"
            "|" -> "│"
            "S" -> "╳"
            "." -> "□"
            else -> shape
        }
}
package day3

data class Position(
    private val line: Int,
    private val column: Int
) {

    fun getNeighbors(lineLength: Int) : Set<Position> {
        val neighbors = mutableSetOf<Position>()
        if (line > 0) {
            neighbors.add(Position(line - 1, column))
            if (column > 0) {
                neighbors.add(Position(line - 1, column - 1))
            }
            if (column < lineLength - 1) {
                neighbors.add(Position(line - 1, column + 1))
            }
        }
        if (line < lineLength - 1) {
            neighbors.add(Position(line + 1, column))
            if (column > 0) {
                neighbors.add(Position(line + 1, column - 1))
            }
            if (column < lineLength - 1) {
                neighbors.add(Position(line + 1, column + 1))
            }
        }
        if (column > 0) {
            neighbors.add(Position(line, column - 1))
        }
        if (column < lineLength - 1) {
            neighbors.add(Position(line, column + 1))
        }

        return neighbors
    }

    // If we don't want to use data class, then these methods are needed:
//    override fun equals(other: Any?): Boolean {
//        if(this === other) return true
//        if(other !is Position) return false
//
//        if(line != other.line) return false
//        if(column != other.column) return false
//
//        return true
//    }
//
//    override fun hashCode() = (line + 1) * 10 + column
//
//    override fun toString() = "Position(line=$line, column=$column)"
}
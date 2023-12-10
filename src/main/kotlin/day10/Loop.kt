package day10

class Loop(
    initialTile : Tile,
) {

    val tiles: MutableList<Tile> = mutableListOf(initialTile)

    val length: Int
        get() = tiles.size

    fun findNextPipe(rows: Array<Array<Tile>>): Tile? {
        val currentTile = tiles.last()
        val candidates = listOfNotNull(
            rows.getOrNull(currentTile.row - 1)?.getOrNull(currentTile.col),
            rows.getOrNull(currentTile.row + 1)?.getOrNull(currentTile.col),
            rows.getOrNull(currentTile.row)?.getOrNull(currentTile.col - 1),
            rows.getOrNull(currentTile.row)?.getOrNull(currentTile.col + 1),
        )

        return candidates.firstOrNull { it !in tiles && currentTile.validConnection(it) }
    }

    fun addTile(tile: Tile) {
        tiles.add(tile)
    }

    operator fun get(pred: (Tile) -> Boolean) = tiles.filter(pred)
    operator fun contains(tile: Tile): Boolean = tile in tiles

}
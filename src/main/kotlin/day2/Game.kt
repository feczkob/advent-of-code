package day2

class Game(
    val id: Int,
    private val draws: List<Draw>
) {

    fun isPossible(reference: Draw) =
        draws.all { draw -> draw.isPossible(reference) }

    /**
     * Returns the number of cubes of the given color in the game with the highest number of cubes of that color.
     */
    operator fun get(color: Color) = draws.maxOfOrNull { it[color] } ?: 0

    fun power() = Color.entries.map { get(it) }.reduce(Int::times)

    override fun toString() = "Game(id=$id, draws=$draws)"
}
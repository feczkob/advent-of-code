package day2

class Draw(
    private val cubes: Map<Color, Int>
) {
    operator fun get(color: Color): Int = cubes[color] ?: 0

    fun isPossible(reference: Draw): Boolean {
        return cubes.all { (color, count) ->
            reference[color] >= count
        }
    }

    override fun toString() =
        "Draw(cubes=$cubes)"
}
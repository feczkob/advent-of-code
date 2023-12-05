package day5

class ChainedMapping(
    private val mappings: List<Mapping>
) {

    operator fun get(value: Long) =
        mappings.fold(value) { acc, mapping -> mapping[acc] }

    // These two work together as well
//    private val chain: (Long) -> Long =
//        mappings.fold( { it } ) { acc, mapping -> { input -> mapping[acc(input)] } }
//
//    operator fun get(input: Long) = chain(input)

    fun reversedGet(value: Long) =
        mappings.reversed().fold(value) { acc, mapping -> mapping.reversedGet(acc) }

}
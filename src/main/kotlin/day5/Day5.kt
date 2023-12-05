package day5

import readFileAsText

private const val FILENAME = "./src/main/resources/day5/day5.txt"

fun main() {
    val input = readFileAsText(FILENAME)
    val seeds = input.substringAfter("seeds: ").substringBefore("\n")
        .split(" ").map { it.toLong() }
    val seedsToSoil = Mapping(readInMap(input, "seed-to-soil map:\n", "\nsoil-to-fertilizer map:"))
    val soilToFertilizer = Mapping(readInMap(input, "soil-to-fertilizer map:\n", "\nfertilizer-to-water map:"))
    val fertilizerToWater = Mapping(readInMap(input, "fertilizer-to-water map:\n", "\nwater-to-light map:"))
    val waterToLight = Mapping(readInMap(input, "water-to-light map:\n", "\nlight-to-temperature map:"))
    val lightToTemperature = Mapping(readInMap(input, "light-to-temperature map:\n", "\ntemperature-to-humidity map:"))
    val temperatureToHumidity =
        Mapping(readInMap(input, "temperature-to-humidity map:\n", "\nhumidity-to-location map:"))
    val humidityToLocation = Mapping(readInMap(input, "humidity-to-location map:\n", null))


    val chainedMapping = ChainedMapping(
        listOf(
            seedsToSoil,
            soilToFertilizer,
            fertilizerToWater,
            waterToLight,
            lightToTemperature,
            temperatureToHumidity,
            humidityToLocation
        )
    )

    // Task 1
    val value = seeds.minOfOrNull { chainedMapping[it] }
    println("Lowest location number: $value")
    // 621354867

    // Task 2
    val seedsAsRanges = input.substringAfter("seeds: ").substringBefore("\n")
        .split(" ").map { it.toLong() }.zipWithNext().filterIndexed { index, _ -> index % 2 == 0 }
        .map { it.first..<it.first + it.second }

    // Would be way too slow
//    val location = seeds2
//        .mapNotNull { range -> range.minOfOrNull { chainedMapping[it] } }
//        .min()

    var seedNumber : Long
    var location = 0L
    while(true) {
        seedNumber = chainedMapping.reversedGet(location)
        if(seedsAsRanges.any { seedNumber in it }) {
            break
        }
        location++
    }

    println("Lowest location number with ranges: $location")
    // 15880236
}

fun readInMap(input: String, after: String, before: String?): List<List<Long>> {
    return input.substringAfter(after)
        .substringBeforeWithNullCheck(before)
        .split("\n")
        .filter { it.isNotBlank() }
        .map { line -> line.split(" ").map { it.toLong() } }
}

fun String.substringBeforeWithNullCheck(delimiter: String?) =
    if (delimiter == null) this else substringBefore(delimiter)

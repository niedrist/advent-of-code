package y2023.day05

import BasicDay
import util.FileReader
import kotlin.math.min

val d = FileReader.readResource("2023/day05.txt").split("\n\n")
val seeds = d[0].split(":")[1].split(" ").filter { it != "" }.map { it.toLong() }
fun String.toMapping() = this.split("\n").filter { it.first().isDigit() }.map { it.split(" ") }
    .map { Mapping(it[1].toLong(), it[0].toLong(), it[2].toLong()) }

val mappings = List(7) {
    d[it + 1].toMapping()
}

fun main() = Day05.run()

object Day05 : BasicDay() {
    override fun part1() = seeds.minOf { it.seedToLocation() }

    override fun part2(): Long {
        val allSeedRanges = seeds.chunked(2).map { it[0] until it[0] + it[1] }
        var min = Long.MAX_VALUE
        for (range in allSeedRanges) {
            range.forEach {
                min = min(min, it.seedToLocation())
            }
        }
        return min
    }
}

fun Long.seedToLocation() = mappings.fold(this) { acc, m -> m.mapOnce(acc) }

data class Mapping(val sourceStart: Long, val destinationStart: Long, val range: Long)

fun List<Mapping>.mapOnce(n: Long): Long {
    return this.firstOrNull {
        n in it.sourceStart until (it.sourceStart + it.range)
    }?.let { it.destinationStart - it.sourceStart + n } ?: n
}
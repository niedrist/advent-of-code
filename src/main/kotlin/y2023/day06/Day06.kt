package y2023.day06

import BasicDay
import util.FileReader

val d = FileReader.asStrings("2023/day06.txt").map { it.split(':')[1].trim() }
    .map { line -> line.split(" ").filter { it != "" }.map { it.toLong() } }
val pairs = d[0].zip(d[1])
val single = Pair(d[0].joinToString("").toLong(), d[1].joinToString("").toLong())


fun main() = Day06.run()

object Day06 : BasicDay() {
    override fun part1() = pairs.fold(1L) { acc, p -> acc * p.waysToBeat() }

    override fun part2() = single.waysToBeat()
}

fun Pair<Long, Long>.waysToBeat() = (1 until first).count { it * (first - it) > second }
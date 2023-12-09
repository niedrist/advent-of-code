package y2023.day09

import BasicDay
import util.FileReader

val d = FileReader.asStrings("2023/day09.txt").map { it.split(" ").map { n -> n.toInt() } }
fun main() = Day09.run()

object Day09 : BasicDay() {
    override fun part1() = d.sumOf { it.differenceSteps().extrapolated() }

    override fun part2() = d.sumOf { it.reversed().differenceSteps().extrapolated() }
}

fun List<Int>.differenceSteps() =
    generateSequence(this) { current ->
        current.zipWithNext { a, b -> b - a }.takeIf { !it.all { x -> x == 0 } }
    }.toList()

fun List<List<Int>>.extrapolated() = this.sumOf { it.last() }
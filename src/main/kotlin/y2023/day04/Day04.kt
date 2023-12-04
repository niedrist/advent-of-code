package y2023.day04

import BasicDay
import util.FileReader

val d = FileReader.asStrings("2023/day04.txt").map { line ->
    val id = line.split(':')[0].split(" ").filter { it != "" }[1].toInt()
    val numbers = line.split(":")[1].trim()
    val winning = numbers.split("|")[0].split(" ").filter { it != "" }.map { it.trim().toInt() }.toSet()
    val yours = numbers.split("|")[1].split(" ").filter { it != "" }.map { it.trim().toInt() }.toSet()
    Card(id, winning, yours, 1)
}

fun main() = Day04.run()

object Day04 : BasicDay() {
    override fun part1() = d.sumOf { it.score() }

    override fun part2(): Int {
        d.forEach { card ->
            val matching = card.matching()
            for (i in 1..matching) {
                d.find { it.id == card.id + i }?.let { it.times += 1 * card.times }
            }
        }
        return d.sumOf { it.times }
    }
}
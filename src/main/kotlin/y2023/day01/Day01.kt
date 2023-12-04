package y2023.day01

import BasicDay
import util.FileReader

val d = FileReader.asStrings("2023/day01.txt")

fun main() = Day01.run()

object Day01 : BasicDay() {
    override fun part1() = d.sumOf { line -> "${line.first { it.isDigit() }}${line.last { it.isDigit() }}".toInt() }

    override fun part2() = d.sumOf { line ->
        val indexFirstString = numberMap.map {
            line.indexOf(it.key) to it.value
        }.filter { it.first != -1 }

        val indexLastString = numberMap.map {
            line.lastIndexOf(it.key) to it.value
        }.filter { it.first != -1 }

        val indexFirstDigit = line.indexOfFirst { it.isDigit() }
        val indexLastDigit = line.indexOfLast { it.isDigit() }

        val first = if (indexFirstString.isNotEmpty() && indexFirstDigit > indexFirstString.minOf { it.first }) {
            indexFirstString.minBy { it.first }.second
        } else {
            line[indexFirstDigit].toString()
        }

        val last = if (indexLastString.isNotEmpty() && indexLastDigit < indexLastString.maxOf { it.first }) {
            indexLastString.maxBy { it.first }.second
        } else {
            line[indexLastDigit].toString()
        }
        (first + last).toInt()
    }

    private val numberMap = mapOf(
        "one" to "1",
        "two" to "2",
        "three" to "3",
        "four" to "4",
        "five" to "5",
        "six" to "6",
        "seven" to "7",
        "eight" to "8",
        "nine" to "9"
    )
}
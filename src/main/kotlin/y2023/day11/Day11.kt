package y2023.day11

import BasicDay
import util.FileReader
import util.pairs
import kotlin.math.max
import kotlin.math.min

val d = FileReader.asStrings("2023/day11.txt").map { it.toCharArray().toList() }
fun main() = Day11.run()

object Day11 : BasicDay() {
    override fun part1() = solve(1)
    override fun part2() = solve(1000000)

    private fun solve(distanceToExpand: Int): Long {
        val galaxies = mutableListOf<Point>()
        d.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { columnIndex, galaxy ->
                if (galaxy == '#')
                    galaxies.add(Point(columnIndex, rowIndex))
            }
        }
        val allPairs = galaxies.pairs()
        val indicesX = d.indicesToExpandHorizontally()
        val indicesY = d.indicesToExpandVertically()
        return allPairs.sumOf { it.distance(indicesX, indicesY, distanceToExpand) }
    }
}

private fun List<List<Char>>.indicesToExpandVertically(): List<Int> {
    val matchingRows = mutableListOf<Int>()
    this.forEachIndexed { rowIndex, chars ->
        if (chars.all { it == '.' })
            matchingRows.add(rowIndex)
    }
    return matchingRows
}

private fun List<List<Char>>.indicesToExpandHorizontally(): List<Int> {
    val emptySpaceCounterPerColumn = mutableMapOf<Int, Int>()
    this.forEach { chars ->
        chars.forEachIndexed { columnIndex, char ->
            if (char == '.')
                emptySpaceCounterPerColumn[columnIndex] = emptySpaceCounterPerColumn[columnIndex]?.let { it + 1 }?: 1
        }
    }
    return emptySpaceCounterPerColumn.filter { it.value == this.size }.keys.toList()
}

private data class Point(val x: Int, val y: Int)
private fun Pair<Point, Point>.distance(indicesX: List<Int>, indicesY: List<Int>, distanceToExpand: Int): Long {
    var distance = 0L
    val lowerX = min(this.first.x, this.second.x)
    val higherX = max(this.first.x, this.second.x)
    val lowerY = min(this.first.y, this.second.y)
    val higherY = max(this.first.y, this.second.y)
    for (x in lowerX  until higherX)
        distance += if(x in indicesX) distanceToExpand else 1
    for (y in lowerY until higherY)
        distance += if(y in indicesY) distanceToExpand else 1
    return distance
}
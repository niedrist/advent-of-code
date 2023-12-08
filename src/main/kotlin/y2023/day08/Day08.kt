package y2023.day08

import BasicDay
import util.FileReader
import util.Math

val d = FileReader.asStrings("2023/day08.txt")
val instructions = d[0]
val navigation = d.drop(2).associate {
    it.substring(0..2) to  Pair(it.substring(7..9), it.substring(12..14))
}

fun main() = Day08.run()

object Day08 : BasicDay() {
    override fun part1() = solve("AAA", false)

    override fun part2(): Long {
        val startingNodes = navigation.keys.filter { it.endsWith('A') }
        val ways = startingNodes.map { solve(it, true) }
        return ways.fold(1L) { acc, i -> Math.lcm(acc, i) }
    }

    private fun solve(startNode: String, onlyLastZ: Boolean): Long {
        var current = startNode
        var steps = 0L
        var instructionPointer = 0
        while ((onlyLastZ && !current.endsWith('Z')) || (!onlyLastZ && current != "ZZZ")) {
            current = if (instructions[instructionPointer] == 'L')
                navigation[current]!!.first
            else
                navigation[current]!!.second
            if (instructionPointer == instructions.length - 1)
                instructionPointer = 0
            else
                instructionPointer++
            steps++
        }
        return steps
    }
}
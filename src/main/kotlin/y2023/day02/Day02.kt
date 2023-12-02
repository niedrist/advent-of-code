package y2023.day02

import BasicDay
import util.FileReader

val d = FileReader.asStrings("2023/day02.txt").map { line ->
    val gameId = line.split(":")[0].split(" ")[1].toInt()
    val cubeSets = line.split(":")[1].split(";").map { cubeSetString ->
        val cubeSet = CubeSet(0, 0, 0)
        val colorStrings = cubeSetString.split(",")
        colorStrings.forEach { colorString ->
            val split = colorString.trim().split(" ")
            val amount = split[0].toInt()
            when (split[1]) {
                "red" -> cubeSet.reds += amount
                "green" -> cubeSet.greens += amount
                "blue" -> cubeSet.blues += amount
                else -> throw Exception("Unknown color")
            }
        }
        cubeSet
    }
    Game(gameId, cubeSets)
}

fun main() = Day02.run()

object Day02 : BasicDay() {
    override fun part1() = d.sumOf {
        if (it.impossibleGame(CubeSet(12, 13, 14))) 0 else it.id
    }

    override fun part2() = d.sumOf { game ->
        val minPossible = game.minimalPossibleCubeSet()
        minPossible.reds * minPossible.greens * minPossible.blues
    }
}

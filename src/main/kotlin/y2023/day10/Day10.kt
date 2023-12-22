package y2023.day10

import BasicDay
import util.FileReader

val d = FileReader.asStrings("2023/day10.txt").map { it.toCharArray() }.toCoordinates().toMutableList()

fun main() = Day09.run()

object Day09 : BasicDay() {
    override fun part1(): Int {
        val start = d.first { it.pipe == 'S' }
        val updatedStartPipe = start.copy(pipe = getStartDirection(start))
        d.remove(start)
        d.add(updatedStartPipe)
        var currentPipe = updatedStartPipe
        val visited = mutableSetOf<Coordinate>()
        while (currentPipe != updatedStartPipe || visited.isEmpty()) {
            visited.add(currentPipe)
            val surroundings = currentPipe.surroundings()
            for (it in surroundings) {
                if (currentPipe.isConnectedWith(it, 0) && (it !in visited || it == updatedStartPipe)) {
                    currentPipe = it
                    break
                }
            }
        }
        return visited.size / 2
    }

    override fun part2() = 2 // TODO
}



private fun getStartDirection(start: Coordinate): Char {
    val surroundings = start.surroundings()
    for (tryPipe in listOf('|', '-', 'L', 'J', '7', 'F')) {
        val countConnected = surroundings.count { start.copy(pipe = tryPipe).isConnectedWith(it, 0) }
        if (countConnected == 2)
            return tryPipe
    }
    throw IllegalStateException("Start pipe is not properly connected")
}


private fun Coordinate.surroundings(): List<Coordinate> =
    buildList {
        d.forEach {
            if (this@surroundings.x - it.x in listOf(-1, 0, 1) &&
                this@surroundings.y - it.y in listOf(-1, 0, 1) &&
                this@surroundings != it
            ) {
                add(it)
            }
        }
    }

private fun Coordinate.isConnectedWith(other: Coordinate, countTries: Int): Boolean {
    if (this.x - other.x !in listOf(-1, 0, 1) || this.y - other.y !in listOf(-1, 0, 1) || countTries > 1)
        return false

    return when (this.pipe) {
        '|' -> when {
            other.pipe == '|' && this.x == other.x && this.y - other.y in listOf(-1, 1) -> true
            other.pipe == 'L' && this.x == other.x && this.y == other.y - 1 -> true
            other.pipe == 'J' && this.x == other.x && this.y == other.y - 1 -> true
            other.pipe == '7' && this.x == other.x && this.y == other.y + 1 -> true
            other.pipe == 'F' && this.x == other.x && this.y == other.y + 1 -> true
            else -> other.isConnectedWith(this, countTries + 1)
        }

        '-' -> when {
            other.pipe == '-' && this.y == other.y && this.x - other.x in listOf(-1, 1) -> true
            other.pipe == 'J' && this.y == other.y && this.x == other.x - 1 -> true
            other.pipe == '7' && this.y == other.y && this.x == other.x - 1 -> true
            other.pipe == 'L' && this.y == other.y && this.x == other.x + 1 -> true
            other.pipe == 'F' && this.y == other.y && this.x == other.x + 1 -> true
            else -> other.isConnectedWith(this, countTries + 1)
        }

        'L' -> when {
            other.pipe == 'J' && this.y == other.y && this.x == other.x - 1 -> true
            other.pipe == '7' && (
                    (this.y == other.y && this.x == other.x - 1) || (this.x == other.x && this.y == other.y + 1)
                    ) -> true

            other.pipe == 'F' && this.x == other.x && this.y == other.y + 1 -> true
            else -> other.isConnectedWith(this, countTries + 1)
        }

        'J' -> when {
            other.pipe == 'F' && (
                    (this.y == other.y && this.x == other.x + 1) || (this.x == other.x && this.y == other.y + 1)
                    ) -> true

            other.pipe == '7' && this.x == other.x && this.y == other.y + 1 -> true
            else -> other.isConnectedWith(this, countTries + 1)
        }
        'F' -> when {
            other.pipe == '7' && this.y == other.y && this.x == other.x - 1 -> true
            else -> other.isConnectedWith(this, countTries + 1)
        }

        '.' -> false
        else -> other.isConnectedWith(this, countTries + 1)
    }
}

data class Coordinate(val x: Int, val y: Int, val pipe: Char)


fun List<CharArray>.toCoordinates() = buildList {
    this@toCoordinates.forEachIndexed { y, chars ->
        chars.forEachIndexed { x, c ->
            add(Coordinate(x, y, c))
        }
    }
}

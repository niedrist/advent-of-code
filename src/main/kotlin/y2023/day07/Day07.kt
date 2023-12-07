package y2023.day07

import BasicDay
import util.FileReader

val players = FileReader.asStrings("2023/day07.txt").map {
    val splitted = it.split(' ')
    Player(splitted[0].toCharArray().toList(), splitted[1].toInt())
}

fun main() = Day07.run()

object Day07 : BasicDay() {
    override fun part1() = solve(false)

    override fun part2() = solve(true)

    private fun solve(withJoker: Boolean) = players
        .sortedWith(compareBy<Player> { it.getType(withJoker) }
            .thenBy { mapCard(it.hand[0], withJoker) }
            .thenBy { mapCard(it.hand[1], withJoker) }
            .thenBy { mapCard(it.hand[2], withJoker) }
            .thenBy { mapCard(it.hand[3], withJoker) }
            .thenBy { mapCard(it.hand[4], withJoker) }
        )
        .foldIndexed(0L) { index, acc, player ->
            acc + ((index + 1) * player.bid)
        }
}

fun mapCard(c: Char, withJoker: Boolean): Int {
    return when (c) {
        'A' -> 14
        'K' -> 13
        'Q' -> 12
        'J' -> if (withJoker) 1 else 11
        'T' -> 10
        else -> c.digitToInt()
    }
}

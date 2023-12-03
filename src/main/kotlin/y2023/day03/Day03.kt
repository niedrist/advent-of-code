package y2023.day03

import BasicDay
import util.FileReader

val d = FileReader.asStrings("2023/day03.txt").mapIndexed { lineIndex, line ->
    val numbers = mutableListOf<LineNumber>()
    val symbols = mutableListOf<Symbol>()
    val number = StringBuilder()
    var numberStartIndex = 0
    var lastOneWasDigit = false
    line.forEachIndexed { index, c ->
        if (c.isDigit()) {
            if (!lastOneWasDigit) {
                numberStartIndex = index
            }
            lastOneWasDigit = true
            number.append(c)
        } else {
            if (lastOneWasDigit) {
                numbers.add(LineNumber(number.toString().toInt(), lineIndex, numberStartIndex, index - 1))
                number.clear()
            }
            lastOneWasDigit = false

            if (c != '.') {
                symbols.add(Symbol(c, lineIndex, index))
            }
        }
    }
    if (lastOneWasDigit) {
        numbers.add(LineNumber(number.toString().toInt(), lineIndex, numberStartIndex, line.length - 1))
    }
    Pair(numbers, symbols)
}

val numbers = d.flatMap { it.first }
val symbols = d.flatMap { it.second }

fun main() = Day03.run()

object Day03 : BasicDay() {

    override fun part1() = symbols.sumOf { symbol ->
        symbol.adjacent(numbers).sumOf { it.number }
    }

    override fun part2() = symbols
        .filter { it.char == '*' }
        .map { it.adjacent(numbers) }
        .filter { it.size >= 2 }
        .sumOf {
            it.fold(1) { acc, lineNumber -> acc * lineNumber.number }.toInt()
        }
}

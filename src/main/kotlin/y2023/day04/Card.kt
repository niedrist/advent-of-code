package y2023.day04

import kotlin.math.pow

data class Card(val id: Int, val winning: Set<Int>, val yours: Set<Int>, var times: Int) {

    fun matching() = this.winning.intersect(this.yours).size
    fun score() = 2.0.pow(this.matching() - 1).let { x -> if (x >= 1) x.toInt() else 0 }

}
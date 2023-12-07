package y2023.day07

data class Player(val hand: List<Char>, val bid: Int) {

    fun getType(withJoker: Boolean): Int {

        val cardCountSorted = hand.filter {
            !withJoker || it != 'J'
        }.groupingBy { it }.eachCount().values.sortedDescending().toMutableList()

        if (withJoker) {
            if (hand == List(5) { 'J' })
                return 7
            cardCountSorted[0] += hand.count { it == 'J' }
        }

        return when (cardCountSorted[0]) {
            5 -> 7
            4 -> 6
            3 -> when (cardCountSorted[1]) {
                2 -> 5
                else -> 4
            }

            2 -> when (cardCountSorted[1]) {
                2 -> 3
                else -> 2
            }

            else -> 1
        }
    }
}
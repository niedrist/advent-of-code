package y2023.day03

data class Symbol(val char: Char, val row: Int, val index: Int) {
    fun adjacent(numbers: List<LineNumber>): List<LineNumber> {
        return numbers.filter {
            (this.row - it.row) in -1..1 &&
                    ((this.index - it.startIndex) in -1..1 || (this.index - it.endIndex) in -1..1)
        }
    }

}
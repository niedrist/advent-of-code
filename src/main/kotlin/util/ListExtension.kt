package util

fun <T> List<T>.pairs(): List<Pair<T, T>> {
    val pairs = mutableListOf<Pair<T, T>>()
    for (i in this.indices) {
        for (j in (i + 1)..<this.size) {
            pairs.add(Pair(this[i], this[j]))
        }
    }
    return pairs
}
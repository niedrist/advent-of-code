package y2023.day02

data class CubeSet(var reds: Int, var greens: Int, var blues: Int) {
    fun impossibleGame(other: CubeSet) = this.reds > other.reds ||
            this.greens > other.greens ||
            this.blues > other.blues

}
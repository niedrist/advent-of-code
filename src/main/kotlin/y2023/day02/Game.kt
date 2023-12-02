package y2023.day02

data class Game(val id: Int, val cubeSets: List<CubeSet>) {
    fun impossibleGame(other: CubeSet) = cubeSets.any { it.impossibleGame(other) }

    fun minimalPossibleCubeSet() = CubeSet(
        cubeSets.maxOf { it.reds },
        cubeSets.maxOf { it.greens },
        cubeSets.maxOf { it.blues }
    )
}
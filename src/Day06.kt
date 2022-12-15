fun main() {
    fun part1(input: List<String>): Int {
        return detector(input.first(), 4)
    }

    fun part2(input: List<String>): Int {
        return detector(input.first(), 14)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    check(part1(testInput) == 6)

    val input = readInput("Day06")
    part1(input).println()
    part2(input).println()
}


fun detector(buffer: String, distinctSize: Int): Int {
    val windowedIndex = buffer.windowed(distinctSize, 1).indexOfFirst {
        it.length == it.toSet().size
    }
    return windowedIndex + distinctSize
}
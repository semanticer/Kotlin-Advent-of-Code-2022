fun main() {
    fun part1(input: List<String>): Int {
        val totals = caloriesByElf(input)
        return totals.maxOrNull() ?: 0
    }

    fun part2(input: List<String>): Int {
        val totals = caloriesByElf(input)
        val sortedTotals = totals.sortedDescending()
        return sortedTotals.take(3).sum()
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}

private fun caloriesByElf(input: List<String>): List<Int> {
    val totals = mutableListOf<Int>()
    var current = 0
    for (line in input) {

        if (line.isEmpty()) {
            totals.add(current)
            current = 0
        } else {
            current += line.trim().toInt()
        }
    }
    totals.add(current)
    return totals
}
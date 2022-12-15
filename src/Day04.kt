import java.lang.Character.isUpperCase

fun main() {
    fun part1(input: List<String>): Int {
        return input.count {
            val (first, second) = it.toRanges()
            first.minus(second).isEmpty() || second.minus(first).isEmpty()
        }
    }

    fun part2(input: List<String>): Int {
        return input.count {
            val (first, second) = it.toRanges()
            first.intersect(second).isNotEmpty()
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}

private fun String.toRanges(): Pair<IntRange, IntRange> {
    fun String.toRange(): IntRange {
        val (from, to) = this.split("-")
        return from.toInt()..to.toInt()
    }
    val (first, second) = split(",")
    return first.toRange() to second.toRange()
}


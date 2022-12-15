import java.lang.Character.isUpperCase

fun main() {
    fun part1(input: List<String>): Int {
        return input.sumOf {
            val firstCompartment = it.subSequence(0, it.length / 2)
            val secondCompartment = it.subSequence(it.length / 2, it.length)
            val charInBoth = firstCompartment.find { secondCompartment.contains(it) }!!
            eval(charInBoth)
        }
    }

    fun part2(input: List<String>): Int {
        return input.windowed(size = 3, step = 3).sumOf {
            val result = it[0].toSet() intersect it[1].toSet() intersect it[2].toSet()
            eval(result.first())
        }
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}

private fun eval(char: Char): Int =
    (('a'..'z') + ('A'..'Z')).indexOf(char) + 1


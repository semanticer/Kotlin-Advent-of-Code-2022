fun main() {
    fun part1(input: List<String>): String {
        val crateMover9000: CrateMover = { move ->
            val from: ArrayDeque<Char> = this[move.from - 1]
            val to = this[move.to - 1]
            repeat(move.quantity) {
                val movingPiece = from.last()
                from.removeLast()
                to.addLast(movingPiece)
            }
        }
        return processWith(input, crateMover9000)
    }

    fun part2(input: List<String>): String {
        val crateMover9001: CrateMover = { move ->
            val from: ArrayDeque<Char> = this[move.from - 1]
            val to = this[move.to - 1]
            val movingPieces = from.takeLast(move.quantity)
            to.addAll(movingPieces)
            repeat(move.quantity) {
                from.removeLast()
            }
        }
        return processWith(input, crateMover9001)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == "CMZ")
    check(part2(testInput) == "MCD")

    val input = readInput("Day05")
    part1(input).println()
    part2(input).println()
}

private fun processWith(input: List<String>, crateMover: CrateMover): String {
    val stacks: List<ArrayDeque<Char>> = parseStacks(input)
    val moveProcedure: List<Move> = parseMoves(input)
    moveProcedure.forEach { move -> stacks.crateMover(move) }
    return stacks.map { it.last() }.joinToString("")
}

fun parseStacks(input: List<String>): List<ArrayDeque<Char>> {
    val numberOfStacks = input.find { it.startsWith(" 1") }!!.trim().last().toString().toInt()
    val stacks = List(numberOfStacks) { ArrayDeque<Char>() }

    input.filter { it.contains("[") }
        .map {
            it.windowed(size = 3, step = 4)
                .forEachIndexed { index, item ->
                    if (item.isNotBlank()) {
                        stacks[index].addFirst(item[1])
                    }
                }

        }
    return stacks
}

fun parseMoves(input: List<String>): List<Move> {
    return input.map { it.split(" ") }
        .filter { it[0] == "move" }
        .map { Move(it[1].toInt(), it[3].toInt(), it[5].toInt()) }
}

data class Move(val quantity: Int, val from: Int, val to: Int)

typealias CrateMover = List<ArrayDeque<Char>>.(move: Move) -> Unit


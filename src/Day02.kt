fun main() {
    fun part1(input: List<String>): Int {
        return input.sumOf {
            val roundInputs = it.split(" ")
            val opponent = roundInputs[0].toOpponentWeapon()
            val me = roundInputs[1].toMyWeapon()
            clashOfWeapons(opponent, me)
        }
    }

    fun part2(input: List<String>): Int {
        return input.sumOf {
            val roundInputs = it.split(" ")
            val opponent = roundInputs[0].toOpponentWeapon()
            val desiredOutcome = roundInputs[1].toDesiredRoundOutcome()
            clashOfWeaponsWithDesiredOutcome(opponent, desiredOutcome)
        }
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}

sealed class Weapon(val value: Int) {
    object Rock : Weapon(1)
    object Paper : Weapon(2)
    object Scissors : Weapon(3)
}

sealed class Outcome(val value: Int) {
    object Lose : Outcome(0)
    object Draw : Outcome(3)
    object Win : Outcome(6)
}

fun String.toOpponentWeapon() = when (this) {
    "A" -> Weapon.Rock
    "B" -> Weapon.Paper
    "C" -> Weapon.Scissors
    else -> throw IllegalArgumentException("Unknown opponent weapon: $this")
}

fun String.toMyWeapon() = when (this) {
    "X" -> Weapon.Rock
    "Y" -> Weapon.Paper
    "Z" -> Weapon.Scissors
    else -> throw IllegalArgumentException("Unknown opponent weapon: $this")
}

fun String.toDesiredRoundOutcome() = when (this) {
    "X" -> Outcome.Lose
    "Y" -> Outcome.Draw
    "Z" -> Outcome.Win
    else -> throw IllegalArgumentException("Unknown opponent weapon: $this")
}

fun clashOfWeapons(opponent: Weapon, me: Weapon): Int {
    val result =  when (opponent) {
        Weapon.Rock -> when (me) {
            Weapon.Rock -> Outcome.Draw
            Weapon.Paper -> Outcome.Win
            Weapon.Scissors -> Outcome.Lose
        }
        Weapon.Paper -> when (me) {
            Weapon.Rock -> Outcome.Lose
            Weapon.Paper -> Outcome.Draw
            Weapon.Scissors -> Outcome.Win
        }
        Weapon.Scissors -> when (me) {
            Weapon.Rock -> Outcome.Win
            Weapon.Paper -> Outcome.Lose
            Weapon.Scissors -> Outcome.Draw
        }
    }
    return result.value + me.value
}

fun clashOfWeaponsWithDesiredOutcome(opponent: Weapon, outcome: Outcome): Int {
    val weapon =  when (opponent) {
        Weapon.Rock -> when (outcome) {
            Outcome.Draw -> Weapon.Rock
            Outcome.Win -> Weapon.Paper
            Outcome.Lose -> Weapon.Scissors
        }
        Weapon.Paper -> when (outcome) {
            Outcome.Lose -> Weapon.Rock
            Outcome.Draw -> Weapon.Paper
            Outcome.Win -> Weapon.Scissors
        }
        Weapon.Scissors -> when (outcome) {
            Outcome.Win -> Weapon.Rock
            Outcome.Lose -> Weapon.Paper
            Outcome.Draw -> Weapon.Scissors
        }
    }
    return weapon.value + outcome.value
}
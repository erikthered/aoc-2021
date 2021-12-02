package com.github.erikthered.aoc2021

fun main() {
    val file = loadInputFile(2)
    // Part 1
    var horizontal = 0
    var vertical = 0
    file.forEachLine {
        val (direction, amount) = it.split(" ").toDirectionAndAmount()
        when(direction) {
            Direction.FORWARD -> horizontal += amount
            Direction.DOWN -> vertical += amount
            Direction.UP -> vertical -= amount
        }
    }
    println("Horizontal: $horizontal, Vertical: $vertical, Product: ${horizontal * vertical}")

    // Part 2
    horizontal = 0
    vertical = 0
    var aim = 0

    file.forEachLine {
        val (direction, amount) = it.split(" ").toDirectionAndAmount()
        when(direction) {
            Direction.FORWARD -> {
                horizontal += amount
                vertical += (aim * amount)
            }
            Direction.DOWN -> aim += amount
            Direction.UP -> aim -= amount
        }
    }
    println("Horizontal: $horizontal, Vertical: $vertical, Product: ${horizontal * vertical}")
}

enum class Direction {
    FORWARD, UP, DOWN
}

fun List<String>.toDirectionAndAmount(): Pair<Direction, Int> {
    return Direction.valueOf(this[0].uppercase()) to this[1].toInt()
}
